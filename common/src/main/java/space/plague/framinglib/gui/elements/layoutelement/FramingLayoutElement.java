package space.plague.framinglib.gui.elements.layoutelement;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.LayoutElement;
import space.plague.framinglib.api.util.AlignmentSizeOffset;
import space.plague.framinglib.api.util.Alignments;
import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.api.util.TextureInfo;
import space.plague.framinglib.api.util.ButtonState;
import space.plague.framinglib.gui.FramingLayoutConfigScreen;
import space.plague.framinglib.util.MathUtils;
import space.plague.framinglib.util.PositioningHelper;
import space.plague.framinglib.util.references.GraphicsReferences;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class FramingLayoutElement extends AbstractWidget implements LayoutElement {
    public static final int PADDING = 3;

    private final Minecraft minecraft;
    private final String name;
    public FramingLayoutConfigScreen screen;

    private final AlignmentSizeOffset value;
    @Nullable
    private final Supplier<AlignmentSizeOffset> defaultValue;

    private boolean showName;
    @NotNull
    private Alignments nameAlignment = GraphicsReferences.DEFAULT_LAYOUT_ELEMENT_NAME_ALIGNMENT;

    private boolean showIcon = false;
    @Nullable
    private TextureInfo iconInfo;
    @NotNull
    private Alignments iconAlignment = GraphicsReferences.DEFAULT_LAYOUT_ELEMENT_ICON_ALIGNMENT;

    @NotNull
    private Color color = GraphicsReferences.DEFAULT_LAYOUT_ELEMENT_COLOR;

    private boolean doesDrawBackground = true;

    @Nullable
    private BiConsumer<GuiGraphics, AlignmentSizeOffset> customRenderingFunction = null;

    private boolean snapping;

    private boolean enableResetButton = true;

    private boolean showButtons;
    @NotNull
    private Alignments buttonsAlignment = GraphicsReferences.DEFAULT_LAYOUT_ELEMENT_BUTTONS_ALIGNMENT;

    private final GenericLayoutElementTextureButton resetButton = DefaultLayoutElementButtons.createResetButton();

    private final List<GenericLayoutElementTextureButton> customButtons = new ArrayList<>();

    private final List<GenericLayoutElementTextureButton> children = new ArrayList<>();

    double draggedX, draggedY;

    private boolean isCurrentlySnappingHorizontally = false;
    private boolean isCurrentlySnappingVertically = false;

    public FramingLayoutElement(AlignmentSizeOffset originalIn, Component name, @Nullable Supplier<AlignmentSizeOffset> defaultValue) {
        super(originalIn.getActualX(), originalIn.getActualY(), originalIn.getWidth(), originalIn.getHeight(), name);

        this.minecraft = Minecraft.getInstance();

        this.name = name.getString();

        this.defaultValue = defaultValue;

        this.value = originalIn;

        this.draggedX = this.getX();
        this.draggedY = this.getY();
    }

    public void setShowName(boolean showName) {
        this.showName = showName;
    }

    public void setNameAlignment(@NotNull Alignments nameAlignment) {
        this.nameAlignment = nameAlignment;
    }

    public void setShowIcon(boolean showIcon) {
        this.showIcon = showIcon;
    }

    public void setIconInfo(@Nullable TextureInfo iconInfo) {
        this.iconInfo = iconInfo;
    }

    public void setIconAlignment(@NotNull Alignments iconAlignment) {
        this.iconAlignment = iconAlignment;
    }

    public void setColor(@NotNull Color color) {
        this.color = color;
    }
    public @NotNull Color getColor() {
        return this.color;
    }

    public void setDoesDrawBackground(boolean doesDrawBackground) {
        this.doesDrawBackground = doesDrawBackground;
    }

    public void setCustomRenderingFunction(@Nullable BiConsumer<GuiGraphics, AlignmentSizeOffset> customRenderingFunction) {
        this.customRenderingFunction = customRenderingFunction;
    }

    public void setSnapping(boolean snapping) {
        this.snapping = snapping;
    }

    public void setEnableResetButton(boolean enableResetButton) {
        this.enableResetButton = enableResetButton;
    }

    public void setShowButtons(boolean showButtons) {
        this.showButtons = showButtons;
    }

    public void setButtonsAlignment(@NotNull Alignments buttonsAlignment) {
        this.buttonsAlignment = buttonsAlignment;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (this.visible) {
            this.isHovered = mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;

            overlayBackground(guiGraphics);
            overlayName(guiGraphics);
            overlayIcon(guiGraphics);
            if (customRenderingFunction != null) {
                customRenderingFunction.accept(guiGraphics, value);
            }

            for (GenericLayoutElementTextureButton child : children) {
                child.render(guiGraphics, mouseX, mouseY, partialTick);
            }

        }

    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.HINT, this.getMessage());
        for (GenericLayoutElementTextureButton button : children) {
            if (button.isHovered()) {
                button.updateNarration(narrationElementOutput);
            }
        }
    }

    private void overlayBackground(GuiGraphics guiGraphics) {
        if (!doesDrawBackground) {
            return;
        }
        ButtonState state = ButtonState.ACTIVE;
        if (this.isHovered) {
            state = ButtonState.HOVERED;
        }
        GraphicsReferences.LAYOUT_ELEMENT_BACKGROUND_HOLDER.render(guiGraphics, this.getX(), this.getY(), width, height, state, color);
    }

    private void overlayName(GuiGraphics guiGraphics) {
        if (!showName) {
            return;
        }

        int textY = this.getY() + height - minecraft.font.lineHeight - 3;
        switch (nameAlignment.getVAlignment()) {
            case TOP:
                textY = this.getY() + 3;
                break;
            case CENTER:
                textY = this.getY() + (height - minecraft.font.lineHeight) / 2;
                break;
        }
        switch (nameAlignment.getHAlignment()) {
            case LEFT:
                guiGraphics.drawString(minecraft.font, name, this.getX() + 3, textY, 0xFFFFFF);
                break;
            case MIDDLE:
                guiGraphics.drawCenteredString(minecraft.font, name, this.getX() + width / 2, textY, 0xFFFFFF);
                break;
            default:
                guiGraphics.drawString(minecraft.font, name, this.getX() + width - 3 - minecraft.font.width(name), textY, 0xFFFFFF);
        }
    }

    private void overlayIcon(GuiGraphics guiGraphics) {
        if (!showIcon || iconInfo == null || iconInfo.getWidth() <= 0 || iconInfo.getHeight() <= 0) {
            return;
        }

        int iconX = this.getX() + width - PADDING - iconInfo.getWidth();
        switch (iconAlignment.getHAlignment()) {
            case LEFT:
                iconX = this.getX() + PADDING;
                break;
            case MIDDLE:
                iconX = (int) (this.getX() + (float) (width - iconInfo.getWidth()) / 2.0f);
                break;
        }
        int iconY = this.getY() + height - PADDING - iconInfo.getHeight();
        switch (iconAlignment.getVAlignment()) {
            case TOP:
                iconY = this.getY() + PADDING;
                break;
            case CENTER:
                iconY = (int) (this.getY() + (float) (height - iconInfo.getHeight()) / 2.0f);
                break;
        }

        iconInfo.render(guiGraphics, iconX, iconY, GraphicsReferences.WHITE);
    }

    public void init() {
        children.clear();

        value.setIsEditing(false);
        Alignments rsa = value.getScreenAlignment();
        value.setScreenAlignment(Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP));
        updatePosition(value.getActualX(), value.getActualY());
        int rx = value.getOffsetX();
        int ry = value.getOffsetY();
        Alignments ra = value.getAlignment().copy();
        value.setIsEditing(true);
        value.setOffsetX(rx);
        value.setOffsetY(ry);
        value.setAlignment(ra);
        value.setScreenAlignment(rsa);

        draggedX = this.getX();
        draggedY = this.getY();

        if (showButtons) {
            addButtons();
        }
    }

    public void addButtons() {
        boolean addResetButton = enableResetButton && defaultValue != null;

        int buttonWidths = 0;
        int maxButtonHeight = 0;
        if (addResetButton) {
            buttonWidths += resetButton.getTextureWidth() + PADDING;
            maxButtonHeight = resetButton.getTextureHeight();
        }

        for (GenericLayoutElementTextureButton customButton : customButtons) {
            buttonWidths += customButton.getTextureWidth() + PADDING;
            maxButtonHeight = Math.max(maxButtonHeight, customButton.getTextureHeight());
        }
        int buttonsOffsetX;
        switch (buttonsAlignment.getHAlignment()) {
            case LEFT:
                buttonsOffsetX = PADDING;
                break;
            case RIGHT:
                buttonsOffsetX = width - buttonWidths;
                break;
            default:
                buttonsOffsetX = (width - buttonWidths) / 2;
        }
        int buttonsOffsetY;
        switch (buttonsAlignment.getVAlignment()) {
            case TOP:
                buttonsOffsetY = PADDING;
                break;
            case BOTTOM:
                buttonsOffsetY = height - maxButtonHeight - PADDING;
                break;
            default:
                buttonsOffsetY = (height - maxButtonHeight) / 2;
        }

        if (addResetButton) {
            resetButton.init(this, buttonsOffsetX, buttonsOffsetY);
            children.add(resetButton);
            buttonsOffsetX += resetButton.getTextureWidth() + PADDING;
        }
        for (GenericLayoutElementTextureButton customButton : customButtons) {
            customButton.init(this, buttonsOffsetX, buttonsOffsetY);
            children.add(customButton);
            buttonsOffsetX += customButton.getTextureWidth() + PADDING;
        }
    }

    public void removed() {
        value.setIsEditing(false);
    }

    @Override
    public boolean isCurrentlySnappingHorizontally() {
        return isCurrentlySnappingHorizontally;
    }
    @Override
    public boolean isCurrentlySnappingVertically() {
        return isCurrentlySnappingVertically;
    }

    @Override
    public boolean isEdited() {
        return value.hasUnsavedChanges();
    }
    @Override
    public boolean isNotDefault() {
        return defaultValue != null && !defaultValue.get().isSimilar(value);
    }

    public boolean isAnyButtonsHovered() {
        for (GenericLayoutElementTextureButton button : children) {
            if (button.isHovered()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void save() {
        int sx = value.getOffsetX();
        int sy = value.getOffsetY();
        Alignments sa = value.getAlignment().copy();
        value.setIsEditing(false);
        value.setOffsetX(sx);
        value.setOffsetY(sy);
        value.setAlignment(sa);
        value.setIsEditing(true);
    }

    @Override
    public void resetValue() {
        if (defaultValue != null) {
            AlignmentSizeOffset dv = defaultValue.get();
            dv.setScreenAlignment(Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP));

            int rx = dv.getOffsetX();
            int ry = dv.getOffsetY();
            Alignments ra = dv.getAlignment().copy();

            updatePosition(dv.getActualX(), dv.getActualY());

            this.draggedX = this.getX();
            this.draggedY = this.getY();

            value.setOffsetX(rx);
            value.setOffsetY(ry);
            value.setAlignment(ra);

        }
    }

    public void setScreen(FramingLayoutConfigScreen screen) {
        this.screen = screen;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible && this.isValidClickButton(button)) {
            boolean flag = this.clicked(mouseX, mouseY);
            if (flag) {
                if (isAnyButtonsHovered()) {
                    for (GenericLayoutElementTextureButton buttonElement : children) {
                        if (buttonElement.mouseClicked(mouseX, mouseY, button)) {
                            return true;
                        }
                    }
                }
                this.onClick(mouseX, mouseY);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (this.isValidClickButton(button)) {
            this.onRelease(mouseX, mouseY);
            isCurrentlySnappingHorizontally = false;
            isCurrentlySnappingVertically = false;
            return true;
        }
        return false;
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
        super.onDrag(mouseX, mouseY, dragX, dragY);
        draggedX += dragX;
        draggedY += dragY;

        boolean localIsCurrentlySnappingHorizontally = false;
        boolean localIsCurrentlySnappingVertically = false;

        double centerX = draggedX + width / 2.0;
        double centerY = draggedY + height / 2.0;
        double screenCenterX = screen.width / 2.0;
        double screenCenterY = screen.height / 2.0;

        int newX;
        int newY;

        if (Math.abs(centerX - screenCenterX) <= FramingLayoutConfigScreen.SNAPPING_THRESHOLD) {
            if (snapping) {
                newX = (screen.width - width) / 2;
                localIsCurrentlySnappingHorizontally = true;
            }
            else {
                newX = (int) draggedX;
            }
        }
        else {
            newX = MathUtils.clamp((int) draggedX, 0, screen.width - width);
        }

        if (Math.abs(centerY - screenCenterY) <= FramingLayoutConfigScreen.SNAPPING_THRESHOLD) {
            if (snapping) {
                newY = (screen.height - height) / 2;
                localIsCurrentlySnappingVertically = true;
            }
            else {
                newY = (int) draggedY;
            }
        }
        else {
            newY = MathUtils.clamp((int) draggedY, 0, screen.height - height);
        }

        isCurrentlySnappingHorizontally = localIsCurrentlySnappingHorizontally;
        isCurrentlySnappingVertically = localIsCurrentlySnappingVertically;

        updatePosition(newX, newY);

        updateValue();
    }

    private void updatePosition(int newX, int newY) {
        this.setX(newX);
        this.setY(newY);

        for (GenericLayoutElementTextureButton button : children) {
            button.updatePosition();
        }
    }

    protected void updateValue() {

        Alignments.HAlignment newHAlign = PositioningHelper.getHAlignment(this.getX(), width);
        int newX = PositioningHelper.getOffsetX(this.getX(), width);

        Alignments.VAlignment newVAlign = PositioningHelper.getVAlignment(this.getY(), height);
        int newY = PositioningHelper.getOffsetY(this.getY(), height);

        int centerX = this.getX() + width / 2;
        int centerY = this.getY() + height / 2;

        int screenCenterX = screen.width / 2;
        int screenCenterY = screen.height / 2;

        if (snapping) {
            if (Math.abs(centerX - screenCenterX) <= FramingLayoutConfigScreen.SNAPPING_THRESHOLD) {
                newX = 0;
            }
            if (Math.abs(centerY - screenCenterY) <= FramingLayoutConfigScreen.SNAPPING_THRESHOLD) {
                newY = 0;
            }
        }

        value.setOffsetX(newX);
        value.setOffsetY(newY);
        value.setAlignment(Alignments.create(newHAlign, newVAlign));
    }

    public void addCustomButtons(List<GenericLayoutElementTextureButton> layoutElementButtons) {
        this.customButtons.addAll(layoutElementButtons);
    }
}

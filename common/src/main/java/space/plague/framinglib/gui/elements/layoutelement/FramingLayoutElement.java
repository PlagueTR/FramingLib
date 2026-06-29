package space.plague.framinglib.gui.elements.layoutelement;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.LayoutElement;
import space.plague.framinglib.api.WindowResizeNotifier;
import space.plague.framinglib.api.util.AlignmentSizeOffset;
import space.plague.framinglib.api.util.Alignments;
import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.api.util.TextureInfo;
import space.plague.framinglib.gui.FramingLayoutConfigScreen;
import space.plague.framinglib.util.ButtonTextureHolder;
import space.plague.framinglib.util.references.TextureReferences;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class FramingLayoutElement extends AbstractWidget implements LayoutElement {
    public static final int PADDING = 3;
    public static final Color WHITE = Color.create(1.0f, 1.0f, 1.0f, 1.0f);

    private final Minecraft minecraft;
    private final String name;
    public FramingLayoutConfigScreen screen;

    private AlignmentSizeOffset value;
    @NotNull
    private final Supplier<AlignmentSizeOffset> original;
    @Nullable
    private final Consumer<AlignmentSizeOffset> saveConsumer;
    @Nullable
    private final Supplier<AlignmentSizeOffset> defaultValue;

    private boolean showName;
    @NotNull
    private Alignments nameAlignment = Alignments.create(Alignments.HAlignment.MIDDLE, Alignments.VAlignment.CENTER);

    private boolean showIcon = false;
    @Nullable
    private TextureInfo iconInfo;
    @NotNull
    private Alignments iconAlignment = Alignments.create(Alignments.HAlignment.MIDDLE, Alignments.VAlignment.CENTER);

    @NotNull
    private Color color = Color.create(196, 196, 196);

    private boolean doesDrawBackground = true;

    @Nullable
    private BiConsumer<PoseStack, AlignmentSizeOffset> customRenderingFunction = null;

    private boolean snapping;

    private boolean enableResetButton = true;

    @NotNull
    private Alignments buttonsAlignment = Alignments.create(Alignments.HAlignment.RIGHT, Alignments.VAlignment.TOP);

    private final Runnable resizeListener = this::onWindowResized;

    @Nullable
    private LayoutResetButtonElement resetButton = null;

    private final List<AbstractLayoutTextureButtonElement> children = new ArrayList<>();

    private boolean wasHovered = false;
    double draggedX, draggedY;

    private boolean isCurrentlySnappingHorizontally = false;
    private boolean isCurrentlySnappingVertically = false;

    public FramingLayoutElement(AlignmentSizeOffset originalIn, Component name, @Nullable Supplier<AlignmentSizeOffset> defaultValue, @Nullable Consumer<AlignmentSizeOffset> saveConsumer) {
        super(originalIn.getActualX(), originalIn.getActualY(), originalIn.getScaledWidth(), originalIn.getScaledHeight(), name);

        this.minecraft = Minecraft.getInstance();

        this.name = name.getString();

        this.original = () -> originalIn;

        this.saveConsumer = saveConsumer;

        this.defaultValue = defaultValue;

        this.value = originalIn;

        this.draggedX = x;
        this.draggedY = y;

        WindowResizeNotifier.subscribe(this.resizeListener);
    }

    public void unsubscribe() {
        WindowResizeNotifier.unsubscribe(this.resizeListener);
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

    public void setDoesDrawBackground(boolean doesDrawBackground) {
        this.doesDrawBackground = doesDrawBackground;
    }

    public void setCustomRenderingFunction(@Nullable BiConsumer<PoseStack, AlignmentSizeOffset> customRenderingFunction) {
        this.customRenderingFunction = customRenderingFunction;
    }

    public void setSnapping(boolean snapping) {
        this.snapping = snapping;
    }

    public void setEnableResetButton(boolean enableResetButton) {
        this.enableResetButton = enableResetButton;
    }

    public void setButtonsAlignment(@NotNull Alignments buttonsAlignment) {
        this.buttonsAlignment = buttonsAlignment;
    }

    public @Nullable Supplier<AlignmentSizeOffset> getDefaultValue() {
        return defaultValue;
    }

    public void onWindowResized() {

        updatePosition(getValue().getActualX(), getValue().getActualY());

        draggedX = x;
        draggedY = y;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        if (this.visible) {
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            if (this.wasHovered != this.isHovered() || isAnyButtonsHovered()) {
                if (this.isHovered) {
                    if (this.isFocused()) {
                        this.queueNarration(200);
                    } else {
                        this.queueNarration(750);
                    }
                } else {
                    this.nextNarration = Long.MAX_VALUE;
                }
            }

            overlayBackground(poseStack);
            overlayName(poseStack);
            overlayIcon(poseStack);
            if (customRenderingFunction != null) {
                customRenderingFunction.accept(poseStack, getValue());
            }

        }

        this.narrate();
        this.wasHovered = this.isHovered();
    }

    @Override
    protected MutableComponent createNarrationMessage() {
        return new TranslatableComponent(TranslationReferences.CONFIG_LAYOUT_ELEMENT_STRING, this.getMessage());
    }

    private void overlayBackground(PoseStack poseStack) {
        if (!doesDrawBackground) {
            return;
        }
        ButtonTextureHolder.ButtonState state = ButtonTextureHolder.ButtonState.ACTIVE;
        if (this.isHovered) {
            state = ButtonTextureHolder.ButtonState.HOVERED;
        }
        TextureReferences.LAYOUT_ELEMENT_BACKGROUND_HOLDER.render(poseStack, x, y, width, height, state, color);
    }

    private void overlayName(PoseStack poseStack) {
        if (!showName) {
            return;
        }

        int textY = y + height - minecraft.font.lineHeight - 3;;
        switch (nameAlignment.getVAlignment()) {
            case TOP:
                textY = y + 3;
                break;
            case CENTER:
                textY = y + (height - minecraft.font.lineHeight) / 2;
                break;
        }
        switch (nameAlignment.getHAlignment()) {
            case LEFT:
                drawString(poseStack, minecraft.font, name, x + 3, textY, 0xFFFFFF);
                break;
            case MIDDLE:
                drawCenteredString(poseStack, minecraft.font, name, x + width / 2, textY, 0xFFFFFF);
                break;
            default:
                drawString(poseStack, minecraft.font, name, x + width - 3 - minecraft.font.width(name), textY, 0xFFFFFF);
        }
    }

    private void overlayIcon(PoseStack poseStack) {
        if (!showIcon || iconInfo == null || iconInfo.getWidth() <= 0 || iconInfo.getHeight() <= 0) {
            return;
        }

        int iconX = x + width - PADDING - iconInfo.getWidth();
        switch (iconAlignment.getHAlignment()) {
            case LEFT:
                iconX = x + PADDING;
                break;
            case MIDDLE:
                iconX = (int) (x + (float) (width - iconInfo.getWidth()) / 2.0f);
                break;
        }
        int iconY = y + height - PADDING - iconInfo.getHeight();
        switch (iconAlignment.getVAlignment()) {
            case TOP:
                iconY = y + PADDING;
                break;
            case CENTER:
                iconY = (int) (y + (float) (height - iconInfo.getHeight()) / 2.0f);
                break;
        }

        iconInfo.render(poseStack, x, y, WHITE);
    }

    public void init() {
        children.clear();

        boolean addResetButton = enableResetButton && defaultValue != null;

        int buttonWidths = 0;
        if (addResetButton) {
            buttonWidths += TextureReferences.LAYOUT_ELEMENT_RESET_BUTTON_HOLDER.getDisabled().getWidth() + PADDING;
        }
        int buttonsOffsetX = 0;
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
        int buttonsOffsetY = 0;
        switch (buttonsAlignment.getVAlignment()) {
            case TOP:
                buttonsOffsetY = PADDING;
                break;
            case BOTTOM:
                buttonsOffsetY = height - (addResetButton ? TextureReferences.RESET_BUTTON_HOLDER.getDisabled().getHeight() : 0) - PADDING;
                break;
            default:
                buttonsOffsetY = (height - (addResetButton ? TextureReferences.RESET_BUTTON_HOLDER.getDisabled().getHeight() : 0)) / 2;
        }

        if (addResetButton) {
            screen.addLayoutButton(resetButton = new LayoutResetButtonElement(this, buttonsOffsetX, buttonsOffsetY, TranslationReferences.CONFIG_LAYOUT_ELEMENT_RESET, TextureReferences.LAYOUT_ELEMENT_RESET_BUTTON_HOLDER));
            resetButton.setColor(color);
            children.add(resetButton);
        }
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
        return !original.get().equals(getValue());
    }
    @Override
    public boolean isNotDefault() {
        return defaultValue != null && !defaultValue.get().equals(getValue());
    }

    public boolean isAnyButtonsHovered() {
        for (AbstractLayoutTextureButtonElement button : children) {
            if (button.isHovered()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void save() {
        if (saveConsumer != null) {
            saveConsumer.accept(getValue());
        }
    }

    @Override
    public void resetValue() {
        if (defaultValue != null) {
            updatePosition(defaultValue.get().getActualX(), defaultValue.get().getActualY());

            this.draggedX = x;
            this.draggedY = y;

            value = defaultValue.get();
        }
    }

    public AlignmentSizeOffset getValue() {
        return value;
    }

    public void tick() {

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
                    return false;
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

    @SuppressWarnings("SameParameterValue")
    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
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

        if (Math.abs(centerX - screenCenterX) <= screen.getSnappingThreshold()) {
            if (snapping) {
                newX = (screen.width - width) / 2;
                localIsCurrentlySnappingHorizontally = true;
            }
            else {
                newX = (int) draggedX;
            }
        }
        else {
            newX = clamp((int) draggedX, 0, screen.width - width);
        }

        if (Math.abs(centerY - screenCenterY) <= screen.getSnappingThreshold()) {
            if (snapping) {
                newY = (screen.height - height) / 2;
                localIsCurrentlySnappingVertically = true;
            }
            else {
                newY = (int) draggedY;
            }
        }
        else {
            newY = clamp((int) draggedY, 0, screen.height - height);
        }

        isCurrentlySnappingHorizontally = localIsCurrentlySnappingHorizontally;
        isCurrentlySnappingVertically = localIsCurrentlySnappingVertically;

        updatePosition(newX, newY);

        updateValue();
    }

    private void updatePosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;

        for (AbstractLayoutTextureButtonElement button : children) {
            button.updatePosition();
        }
    }

    protected void updateValue() {

        Alignments.HAlignment newHAlign;
        int newX;

        Alignments.VAlignment newVAlign;
        int newY;

        int centerX = x + width / 2;
        int centerY = y + height / 2;

        int screenCenterX = screen.width / 2;
        int screenCenterY = screen.height / 2;

        if (centerX <= screen.width / 3) {
            newHAlign = Alignments.HAlignment.LEFT;
            newX = x;
        }
        else if (centerX >= (screen.width * 2) / 3)  {
            newHAlign = Alignments.HAlignment.RIGHT;
            newX = screen.width - x;
        }
        else {
            newHAlign = Alignments.HAlignment.MIDDLE;
            if (snapping && Math.abs(centerX - screenCenterX) <= screen.getSnappingThreshold()) {
                newX = 0;
            }
            else {
                newX = -((screen.width - width) / 2 - x);
            }
        }

        if (centerY <= screen.height / 3) {
            newVAlign = Alignments.VAlignment.TOP;
            newY = y;
        }
        else if (centerY >= (screen.height * 2) / 3) {
            newVAlign = Alignments.VAlignment.BOTTOM;
            newY = screen.height - y;
        }
        else {
            newVAlign = Alignments.VAlignment.CENTER;
            if (snapping && Math.abs(centerY - screenCenterY) <= screen.getSnappingThreshold()) {
                newY = 0;
            }
            else {
                newY = -((screen.height - height) / 2 - y);
            }
        }

        value = AlignmentSizeOffset.create(newX, newY, original.get().getWidth(), original.get().getHeight(), original.get().getScale(), newHAlign, newVAlign);
    }

}

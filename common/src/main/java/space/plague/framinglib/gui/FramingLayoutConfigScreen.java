package space.plague.framinglib.gui;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import org.lwjgl.glfw.GLFW;

import space.plague.framinglib.api.LayoutConfigScreen;
import space.plague.framinglib.api.LayoutElement;
import space.plague.framinglib.api.util.Alignments;
import space.plague.framinglib.gui.elements.FramingLayoutConfigBackButton;
import space.plague.framinglib.gui.elements.FramingLayoutConfigResetAllButton;
import space.plague.framinglib.gui.elements.FramingLayoutConfigSaveButton;
import space.plague.framinglib.gui.elements.layoutelement.FramingLayoutElement;
import space.plague.framinglib.util.references.GraphicsReferences;
import space.plague.framinglib.util.references.TranslationReferences;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class FramingLayoutConfigScreen extends Screen implements LayoutConfigScreen {
    public static final int PADDING = 6;
    public static final int SNAPPING_THRESHOLD = 10;

    private final Screen parent;

    @Nullable
    private Runnable savingRunnable = null;

    private boolean showButtons;

    @Nullable
    private Consumer<Screen> afterInitConsumer = null;

    private final ResourceLocation backgroundTexture;
    private boolean transparentBackground;

    private final List<LayoutElement> layoutElementList;

    private Alignments buttonsAlignments = GraphicsReferences.DEFAULT_BUTTONS_ALIGNMENT;

    private boolean showResetButton;

    @Nullable
    private FramingLayoutConfigBackButton backButton;
    @Nullable
    private FramingLayoutConfigSaveButton saveButton;
    @Nullable
    private FramingLayoutConfigResetAllButton resetAllButton;

    private boolean isCurrentlySnappingHorizontally = false;
    private boolean isCurrentlySnappingVertically = false;

    public FramingLayoutConfigScreen(Screen parent, Component title, List<LayoutElement> layoutElementList, ResourceLocation backgroundTexture) {
        super(title);
        this.parent = parent;
        this.backgroundTexture = backgroundTexture;
        for (LayoutElement layoutElement : layoutElementList) {
            if (layoutElement instanceof FramingLayoutElement) {
                ((FramingLayoutElement) layoutElement).setScreen(this);
            }
        }
        this.layoutElementList = layoutElementList;
    }

    @Override
    public void setSavingRunnable(@Nullable Runnable savingRunnable) {
        this.savingRunnable = savingRunnable;
    }

    @Override
    public void setAfterInitConsumer(@Nullable Consumer<Screen> afterInitConsumer) {
        this.afterInitConsumer = afterInitConsumer;
    }

    @Override
    public ResourceLocation getBackgroundTexture(){
        return backgroundTexture;
    }

    @Override
    public List<LayoutElement> getLayoutElementList() {
        return layoutElementList;
    }

    @Override
    public boolean isEdited() {
        for (LayoutElement layoutElement : getLayoutElementList()) {
            if (layoutElement.isEdited()){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isNotDefault() {
        for (LayoutElement layoutElement : getLayoutElementList()) {
            if (layoutElement.isNotDefault()){
                return true;
            }
        }
        return false;
    }

    public boolean isShowButtons() {
        return isAlwaysShowSavingButtons();
    }
    public boolean isAlwaysShowSavingButtons() {
        return showButtons;
    }

    public void setShowButtons(boolean showButtons) {
        this.showButtons = showButtons;
    }

    public boolean isTransparentBackground() {
        return transparentBackground && minecraft != null && minecraft.level != null;
    }

    public void setTransparentBackground(boolean transparentBackground) {
        this.transparentBackground = transparentBackground;
    }

    public void setButtonsAlignments(Alignments buttonsAlignments) {
        this.buttonsAlignments = buttonsAlignments;
    }

    @Override
    public void setTooltip(Component tooltip) {
        setTooltipForNextRenderPass(tooltip);
    }

    @Override
    public void saveAll(boolean openOtherScreens) {
        for (LayoutElement layoutElement : getLayoutElementList()) {
            layoutElement.save();
        }
        save();
        if (openOtherScreens && minecraft != null) {
            minecraft.setScreen(parent);
        }
    }

    @Override
    public void resetAll() {
        for (LayoutElement layoutElement : getLayoutElementList()) {
            layoutElement.resetValue();
        }
    }

    public void save() {
        Optional.ofNullable(this.savingRunnable).ifPresent(Runnable::run);
    }

    @Override
    public void quit() {
        if (minecraft != null) {
            minecraft.setScreen(parent);
        }
    }

    public void setShowResetButton(boolean showResetButton) {
        this.showResetButton = showResetButton;
    }

    private class QuitSaveConsumer implements BooleanConsumer {
        @Override
        public void accept(boolean value) {
            if (minecraft != null) {
                if (!value) {
                    minecraft.setScreen(FramingLayoutConfigScreen.this);
                } else {
                    minecraft.setScreen(parent);
                }
            }
        }
    }

    protected void overlayBackground(GuiGraphics guiGraphics) {
        if (isTransparentBackground() || minecraft == null) {
            return;
        }
        guiGraphics.setColor(0.25f, 0.25f, 0.25f, 1.0f);

        int tiling = 32;

        guiGraphics.blit(getBackgroundTexture(), 0, 0, 0, 0.0f, 0.0f, width, height, tiling, tiling);

        guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    protected void overlayGrid(GuiGraphics guiGraphics) {
        int dot_argb = 0xFF404040;

        float dot_size = 3.0f;

        float val = (height / 3.0f) - 1.0f;
        for (float x = 0.0f; x < width; x += dot_size * 2) {
            float dotWidth = Math.min(dot_size, width - x);
            guiGraphics.fill((int)x, (int)val, (int)(x + dotWidth), (int)(val + 1), dot_argb);
        }

        val = ((2.0f * height) / 3.0f);
        for (float x = 0.0f; x < width; x += dot_size * 2) {
            float dotWidth = Math.min(dot_size, width - x);
            guiGraphics.fill((int)x, (int)val, (int)(x + dotWidth), (int)(val + 1), dot_argb);
        }

        val = (width / 3.0f) - 1.0f;
        for (float y = 0.0f; y < height; y += dot_size * 2) {
            float dotWidth = Math.min(dot_size, height - y);
            guiGraphics.fill((int)val, (int)y, (int)(val + 1), (int)(y + dotWidth), dot_argb);
        }

        val = ((2.0f * width) / 3.0f) - 1.0f;
        for (float y = 0.0f; y < height; y += dot_size * 2) {
            float dotWidth = Math.min(dot_size, height - y);
            guiGraphics.fill((int)val, (int)y, (int)(val + 1), (int)(y + dotWidth), dot_argb);
        }
    }

    protected void overlaySnapping(GuiGraphics guiGraphics) {
        int snappingColor = 0x1AFFFFFF;
        if (isCurrentlySnappingHorizontally) {
            guiGraphics.fillGradient((this.width - SNAPPING_THRESHOLD) / 2, 0, (this.width + SNAPPING_THRESHOLD) / 2, this.height, snappingColor, snappingColor);
        }
        if (isCurrentlySnappingVertically) {
            guiGraphics.fillGradient(0, (this.height - SNAPPING_THRESHOLD) / 2, this.width, (this.height + SNAPPING_THRESHOLD) / 2, snappingColor, snappingColor);
        }
    }

    @Override
    protected void init() {
        super.init();

        if (isShowButtons()) {
            int buttonWidths = GraphicsReferences.DENY_BUTTON_HOLDER.getDisabledTextureInfo().getWidth() + GraphicsReferences.ACCEPT_BUTTON_HOLDER.getDisabledTextureInfo().getWidth() + PADDING * 2;

            if (showResetButton) {
                buttonWidths += GraphicsReferences.RESET_BUTTON_HOLDER.getDisabledTextureInfo().getWidth() + PADDING;
            }

            int buttonsX;
            switch (buttonsAlignments.getHAlignment()) {
                case LEFT:
                    buttonsX = PADDING;
                    break;
                case RIGHT:
                    buttonsX = width - buttonWidths;
                    break;
                default:
                    buttonsX = (width - buttonWidths) / 2;
            }
            int buttonsY;
            switch (buttonsAlignments.getVAlignment()) {
                case TOP:
                    buttonsY = PADDING;
                    break;
                case BOTTOM:
                    buttonsY = height - Math.max(GraphicsReferences.DENY_BUTTON_HOLDER.getDisabledTextureInfo().getHeight(), Math.max(GraphicsReferences.ACCEPT_BUTTON_HOLDER.getDisabledTextureInfo().getHeight(), (showResetButton ? GraphicsReferences.RESET_BUTTON_HOLDER.getDisabledTextureInfo().getHeight() : 0))) - PADDING;
                    break;
                default:
                    buttonsY = (height - Math.max(GraphicsReferences.DENY_BUTTON_HOLDER.getDisabledTextureInfo().getHeight(), Math.max(GraphicsReferences.ACCEPT_BUTTON_HOLDER.getDisabledTextureInfo().getHeight(), (showResetButton ? GraphicsReferences.RESET_BUTTON_HOLDER.getDisabledTextureInfo().getHeight() : 0)))) / 2;
            }

            this.addRenderableWidget(backButton = new FramingLayoutConfigBackButton(this, buttonsX, buttonsY, isEdited()? TranslationReferences.CONFIG_CANCEL_DISCARD : TranslationReferences.CONFIG_CANCEL, GraphicsReferences.DENY_BUTTON_HOLDER));
            buttonsX += GraphicsReferences.DENY_BUTTON_HOLDER.getDisabledTextureInfo().getWidth() + PADDING;
            this.addRenderableWidget(saveButton = new FramingLayoutConfigSaveButton(this, buttonsX, buttonsY, TranslationReferences.CONFIG_SAVE, GraphicsReferences.ACCEPT_BUTTON_HOLDER));
            saveButton.active = isEdited();
            buttonsX += GraphicsReferences.ACCEPT_BUTTON_HOLDER.getDisabledTextureInfo().getWidth() + PADDING;
            if (showResetButton) {
                this.addRenderableWidget(resetAllButton = new FramingLayoutConfigResetAllButton(this, buttonsX, buttonsY, TranslationReferences.CONFIG_RESET_ALL, GraphicsReferences.RESET_BUTTON_HOLDER));
            }
        }

        if (!getLayoutElementList().isEmpty()) {
            for (int i = getLayoutElementList().size() - 1; i >= 0; i--) {
                LayoutElement layoutElement = getLayoutElementList().get(i);
                if (layoutElement instanceof FramingLayoutElement) {
                    this.addWidget((FramingLayoutElement) layoutElement);
                    ((FramingLayoutElement) layoutElement).init();
                }
            }
        }

        if (afterInitConsumer != null) {
            afterInitConsumer.accept(this);
        }
    }

    @Override
    public void removed() {
        for (LayoutElement layoutElement : getLayoutElementList()) {
            if (layoutElement instanceof FramingLayoutElement) {
                ((FramingLayoutElement) layoutElement).removed();
            }
        }
        super.removed();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (resetAllButton != null && resetAllButton.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        if (keyCode == GLFW.GLFW_KEY_ESCAPE && this.shouldCloseOnEsc()) {
            if (isShowButtons()) {
                quit();
            }
            else {
                saveAll(true);
            }
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void tick() {
        super.tick();
        boolean edited = isEdited();
        Optional.ofNullable(backButton).ifPresent(
                button -> button.setMessage(edited ?
                        TranslationReferences.CONFIG_CANCEL_DISCARD :
                        TranslationReferences.CONFIG_CANCEL));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (isTransparentBackground()) {
            guiGraphics.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010 );
        }
        else {
            overlayBackground(guiGraphics);
        }
        isCurrentlySnappingHorizontally = false;
        isCurrentlySnappingVertically = false;
        for (LayoutElement child : getLayoutElementList()) {
            if (child.isCurrentlySnappingHorizontally()) {
                isCurrentlySnappingHorizontally = true;
            }
            if (child.isCurrentlySnappingVertically()) {
                isCurrentlySnappingVertically = true;
                break;
            }
        }
        overlaySnapping(guiGraphics);
        overlayGrid(guiGraphics);
        for(LayoutElement layoutElement : getLayoutElementList()) {
            if (layoutElement instanceof FramingLayoutElement) {
                ((FramingLayoutElement) layoutElement).render(guiGraphics, mouseX, mouseY, partialTick);
            }
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

}

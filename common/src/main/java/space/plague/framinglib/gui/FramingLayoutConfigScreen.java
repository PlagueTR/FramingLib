package space.plague.framinglib.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import org.joml.Matrix4f;

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
    private final Tesselator tesselator = Tesselator.getInstance();

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

    private Component tooltip = null;

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
        this.tooltip = tooltip;
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

    protected void overlayBackground(PoseStack poseStack) {
        if (isTransparentBackground() || minecraft == null) {
            return;
        }
        BufferBuilder buffer = tesselator.getBuilder();
        RenderSystem.setShaderTexture(0, getBackgroundTexture());
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);

        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        Matrix4f matrix = poseStack.last().pose();

        float tiling = 32.0f;

        buffer.vertex(matrix, 0.0f, height, 0.0f).uv(0.0f, height / tiling).color(64, 64, 64, 255).endVertex();
        buffer.vertex(matrix, width, height, 0.0f).uv(width / tiling, height / tiling).color(64, 64, 64, 255).endVertex();
        buffer.vertex(matrix, width, 0.0f, 0.0f).uv(width / tiling, 0.0f).color(64, 64, 64, 255).endVertex();
        buffer.vertex(matrix, 0.0f, 0.0f, 0.0f).uv(0.0f, 0.0f).color(64, 64, 64, 255).endVertex();

        tesselator.end();
    }

    protected void overlayGrid(PoseStack poseStack) {
        BufferBuilder buffer = tesselator.getBuilder();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        Matrix4f matrix = poseStack.last().pose();

        int dot_r = 64;
        int dot_g = 64;
        int dot_b = 64;

        float dot_size = 3.0f;

        float val = (height / 3.0f) - 1.0f;
        for (float x = 0.0f; x < width; x += dot_size * 2) {
            float dotWidth = Math.min(dot_size, width - x);
            buffer.vertex(matrix, x, val + 1, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
            buffer.vertex(matrix, x + dotWidth, val + 1, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
            buffer.vertex(matrix, x + dotWidth, val, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
            buffer.vertex(matrix, x, val, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
        }

        val = ((2.0f * height) / 3.0f);
        for (float x = 0.0f; x < width; x += dot_size * 2) {
            float dotWidth = Math.min(dot_size, width - x);
            buffer.vertex(matrix, x, val + 1, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
            buffer.vertex(matrix, x + dotWidth, val + 1, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
            buffer.vertex(matrix, x + dotWidth, val, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
            buffer.vertex(matrix, x, val, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
        }

        val = (width / 3.0f) - 1.0f;
        for (float y = 0.0f; y < height; y += dot_size * 2) {
            float dotWidth = Math.min(dot_size, height - y);
            buffer.vertex(matrix, val, y + dotWidth, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
            buffer.vertex(matrix, val + 1, y + dotWidth, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
            buffer.vertex(matrix, val + 1, y, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
            buffer.vertex(matrix, val, y, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
        }

        val = ((2.0f * width) / 3.0f) - 1.0f;
        for (float y = 0.0f; y < height; y += dot_size * 2) {
            float dotWidth = Math.min(dot_size, height - y);
            buffer.vertex(matrix, val, y + dotWidth, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
            buffer.vertex(matrix, val + 1, y + dotWidth, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
            buffer.vertex(matrix, val + 1, y, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
            buffer.vertex(matrix, val, y, 0.0f).color(dot_r, dot_g, dot_b, 255).endVertex();
        }

        tesselator.end();
    }

    protected void overlaySnapping(PoseStack poseStack) {
        int snappingColor = 0x1AFFFFFF;
        if (isCurrentlySnappingHorizontally) {
            fillGradient(poseStack, (this.width - SNAPPING_THRESHOLD) / 2, 0, (this.width + SNAPPING_THRESHOLD) / 2, this.height, snappingColor, snappingColor);
        }
        if (isCurrentlySnappingVertically) {
            fillGradient(poseStack, 0, (this.height - SNAPPING_THRESHOLD) / 2, this.width, (this.height + SNAPPING_THRESHOLD) / 2, snappingColor, snappingColor);
        }
    }

    private void renderTooltips(PoseStack poseStack, int mouseX, int mouseY) {
        if (tooltip != null) {
            renderTooltip(poseStack, tooltip, mouseX, mouseY);
        }
        tooltip = null;
    }

    @Override
    protected void init() {
        super.init();

        if (isShowButtons()) {
            int buttonWidths = GraphicsReferences.DENY_BUTTON_HOLDER.getDisabled().getWidth() + GraphicsReferences.ACCEPT_BUTTON_HOLDER.getDisabled().getWidth() + PADDING * 2;

            if (showResetButton) {
                buttonWidths += GraphicsReferences.RESET_BUTTON_HOLDER.getDisabled().getWidth() + PADDING;
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
                    buttonsY = height - Math.max(GraphicsReferences.DENY_BUTTON_HOLDER.getDisabled().getHeight(), Math.max(GraphicsReferences.ACCEPT_BUTTON_HOLDER.getDisabled().getHeight(), (showResetButton ? GraphicsReferences.RESET_BUTTON_HOLDER.getDisabled().getHeight() : 0))) - PADDING;
                    break;
                default:
                    buttonsY = (height - Math.max(GraphicsReferences.DENY_BUTTON_HOLDER.getDisabled().getHeight(), Math.max(GraphicsReferences.ACCEPT_BUTTON_HOLDER.getDisabled().getHeight(), (showResetButton ? GraphicsReferences.RESET_BUTTON_HOLDER.getDisabled().getHeight() : 0)))) / 2;
            }

            this.addRenderableWidget(backButton = new FramingLayoutConfigBackButton(this, buttonsX, buttonsY, isEdited()? TranslationReferences.CONFIG_CANCEL_DISCARD : TranslationReferences.CONFIG_CANCEL, GraphicsReferences.DENY_BUTTON_HOLDER));
            buttonsX += GraphicsReferences.DENY_BUTTON_HOLDER.getDisabled().getWidth() + PADDING;
            this.addRenderableWidget(saveButton = new FramingLayoutConfigSaveButton(this, buttonsX, buttonsY, TranslationReferences.CONFIG_SAVE, GraphicsReferences.ACCEPT_BUTTON_HOLDER));
            saveButton.active = isEdited();
            buttonsX += GraphicsReferences.ACCEPT_BUTTON_HOLDER.getDisabled().getWidth() + PADDING;
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
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        if (isTransparentBackground()) {
            fillGradient(poseStack, 0, 0, this.width, this.height, 0xC0101010, 0xD0101010 );
        }
        else {
            overlayBackground(poseStack);
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
        overlaySnapping(poseStack);
        overlayGrid(poseStack);
        for(LayoutElement layoutElement : getLayoutElementList()) {
            if (layoutElement instanceof FramingLayoutElement) {
                ((FramingLayoutElement) layoutElement).render(poseStack, mouseX, mouseY, partialTick);
            }
        }
        super.render(poseStack, mouseX, mouseY, partialTick);
        renderTooltips(poseStack, mouseX, mouseY);
    }

}

package space.plague.framinglib.gui.elements;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.LayoutConfigScreen;
import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.gui.FramingLayoutConfigScreen;
import space.plague.framinglib.util.ButtonTextureHolder;

import java.util.Optional;
import java.util.function.Supplier;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public abstract class AbstractTextureButtonElement extends AbstractButton {

    protected final LayoutConfigScreen screen;
    protected final ButtonTextureHolder buttonTextureHolder;

    @Nullable
    private Supplier<Optional<Component>> tooltipSupplier;

    private Color color;

    protected boolean wasHovered = false;

    public AbstractTextureButtonElement(FramingLayoutConfigScreen parent, int x, int y, Component name, ButtonTextureHolder buttonTextureHolder) {
        super(x, y, buttonTextureHolder.getDisabled().getWidth(), buttonTextureHolder.getDisabled().getHeight(), name);
        this.screen = parent;
        this.buttonTextureHolder = buttonTextureHolder;
        this.color = Color.create(255, 255, 255);
    }

    public void setTooltipSupplier(@Nullable Supplier<Optional<Component>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void onPress();

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.HINT, Component.translatable("gui.narrate.button", this.getMessage()));
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height &&
            buttonTextureHolder.getDisabled().isPixelSolid((int) (mouseX - this.getX()), (int) (mouseY - this.getY()));
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= (double)this.getX() && mouseY >= (double)this.getY() && mouseX < (double)(this.getX() + this.width) && mouseY < (double)(this.getY() + this.height) &&
            buttonTextureHolder.getDisabled().isPixelSolid((int) (mouseX - this.getX()), (int) (mouseY - this.getY()));
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        if (this.visible) {
            this.isHovered = mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height &&
                buttonTextureHolder.getDisabled().isPixelSolid(mouseX - this.getX(), mouseY - this.getY());

            renderTextureButton(poseStack);
            if (active && isHovered) {
                renderToolTip(poseStack, mouseX, mouseY);
            }

            this.wasHovered = this.isHovered;
        }
    }

    public void renderTextureButton(PoseStack poseStack) {
        if (!this.active) {
            buttonTextureHolder.render(poseStack, this.getX(), this.getY(), ButtonTextureHolder.ButtonState.DISABLED, color);
        }
        else if (this.isHovered) {
            buttonTextureHolder.render(poseStack, this.getX(), this.getY(), ButtonTextureHolder.ButtonState.HOVERED, color);
        }
        else {
            buttonTextureHolder.render(poseStack, this.getX(), this.getY(), ButtonTextureHolder.ButtonState.ACTIVE, color);
        }
    }

    public void renderToolTip(PoseStack poseStack, int mouseX, int mouseY) {
        if (this.tooltipSupplier != null && this.tooltipSupplier.get().isPresent()) {
            screen.setTooltip(this.tooltipSupplier.get().get());
        }
    }

}

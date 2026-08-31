package space.plague.framinglib.gui.elements;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.LayoutConfigScreen;
import space.plague.framinglib.api.util.ButtonState;
import space.plague.framinglib.api.util.ButtonTextureHolder;
import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.gui.FramingLayoutConfigScreen;
import space.plague.framinglib.util.ButtonTextureHolderImpl;

import java.util.Optional;
import java.util.function.Supplier;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public abstract class AbstractTextureButtonElement extends AbstractButton {

    protected LayoutConfigScreen screen;

    protected Supplier<ButtonTextureHolderImpl> buttonTextureSupplier;

    @Nullable
    protected Supplier<Optional<Component>> tooltipSupplier;

    private Color color;

    public AbstractTextureButtonElement(FramingLayoutConfigScreen parent, int x, int y, Component name, Supplier<ButtonTextureHolderImpl> buttonTextureSupplier) {
        super(x, y,
            buttonTextureSupplier.get() != null ? buttonTextureSupplier.get().getDisabledTextureInfo().getWidth() : 0,
            buttonTextureSupplier.get() != null ? buttonTextureSupplier.get().getDisabledTextureInfo().getHeight() : 0,
            name);
        this.screen = parent;
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
            buttonTextureSupplier.get().getDisabledTextureInfo().isPixelSolid((int) (mouseX - this.getX()), (int) (mouseY - this.getY()));
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= (double)this.getX() && mouseY >= (double)this.getY() && mouseX < (double)(this.getX() + this.width) && mouseY < (double)(this.getY() + this.height) &&
            buttonTextureSupplier.get().getDisabledTextureInfo().isPixelSolid((int) (mouseX - this.getX()), (int) (mouseY - this.getY()));
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (this.visible) {
            this.isHovered = mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height &&
                buttonTextureSupplier.get().getDisabledTextureInfo().isPixelSolid(mouseX - this.getX(), mouseY - this.getY());

            renderTextureButton(guiGraphics);
            if (active && isHovered) {
                renderToolTip();
            }
        }
    }

    public void renderTextureButton(GuiGraphics guiGraphics) {
        if (!this.active) {
            buttonTextureSupplier.get().render(guiGraphics, this.getX(), this.getY(), ButtonState.DISABLED, color);
        }
        else if (this.isHovered) {
            buttonTextureSupplier.get().render(guiGraphics, this.getX(), this.getY(), ButtonState.HOVERED, color);
        }
        else {
            buttonTextureSupplier.get().render(guiGraphics, this.getX(), this.getY(), ButtonState.ACTIVE, color);
        }
    }

    public void renderToolTip() {
        if (this.tooltipSupplier != null && this.tooltipSupplier.get().isPresent()) {
            screen.setTooltip(this.tooltipSupplier.get().get());
        }
    }

}

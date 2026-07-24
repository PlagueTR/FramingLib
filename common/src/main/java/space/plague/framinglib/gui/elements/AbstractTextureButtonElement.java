package space.plague.framinglib.gui.elements;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.util.Color;
import space.plague.framinglib.gui.FramingLayoutConfigScreen;
import space.plague.framinglib.util.ButtonTextureHolder;

import java.util.Optional;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public abstract class AbstractTextureButtonElement extends AbstractButton {

    protected final Screen screen;
    protected final ButtonTextureHolder buttonTextureHolder;

    @Nullable
    private Supplier<Optional<Component>> tooltipSupplier;

    private Color color;

    protected boolean wasHovered = false;

    public AbstractTextureButtonElement(Screen parent, int x, int y, Component name, ButtonTextureHolder buttonTextureHolder) {
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
    public boolean isMouseOver(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height &&
            buttonTextureHolder.getDisabled().isPixelSolid((int) (mouseX - x), (int) (mouseY - y));
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return this.active && this.visible && mouseX >= (double)this.x && mouseY >= (double)this.y && mouseX < (double)(this.x + this.width) && mouseY < (double)(this.y + this.height) &&
            buttonTextureHolder.getDisabled().isPixelSolid((int) (mouseX - x), (int) (mouseY - y));
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        if (this.visible) {
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height &&
                buttonTextureHolder.getDisabled().isPixelSolid(mouseX - x, mouseY - y);
            if (this.wasHovered != this.isHovered()) {
                if (this.isHovered()) {
                    if (this.isFocused()) {
                        this.queueNarration(200);
                    }
                    else {
                        this.queueNarration(750);
                    }
                }
                else {
                    this.nextNarration = Long.MAX_VALUE;
                }
            }

            renderTextureButton(poseStack);
            if (active && isHovered()) {
                renderToolTip(poseStack, mouseX, mouseY);
            }

            this.narrate();
            this.wasHovered = this.isHovered();
        }
    }

    public void renderTextureButton(PoseStack poseStack) {
        if (!this.active) {
            buttonTextureHolder.render(poseStack, x, y, ButtonTextureHolder.ButtonState.DISABLED, color);
        }
        else if (this.isHovered()) {
            buttonTextureHolder.render(poseStack, x, y, ButtonTextureHolder.ButtonState.HOVERED, color);
        }
        else {
            buttonTextureHolder.render(poseStack, x, y, ButtonTextureHolder.ButtonState.ACTIVE, color);
        }
    }

    public void renderToolTip(PoseStack poseStack, int mouseX, int mouseY) {
        if (this.tooltipSupplier != null && this.tooltipSupplier.get().isPresent()) {
            if (screen instanceof FramingLayoutConfigScreen) {
                FramingLayoutConfigScreen framingScreen = (FramingLayoutConfigScreen) screen;
                framingScreen.setTooltip(this.tooltipSupplier.get().get());
            }
        }
    }

}

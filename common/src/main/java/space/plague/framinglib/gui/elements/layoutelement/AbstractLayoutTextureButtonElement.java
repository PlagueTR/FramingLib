package space.plague.framinglib.gui.elements.layoutelement;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import space.plague.framinglib.gui.elements.AbstractTextureButtonElement;
import space.plague.framinglib.util.ButtonTextureHolder;

public abstract class AbstractLayoutTextureButtonElement extends AbstractTextureButtonElement {

    protected final FramingLayoutElement layoutElement;
    private final int offsetX;
    private final int offsetY;

    public AbstractLayoutTextureButtonElement(FramingLayoutElement layoutElement, int offsetX, int offsetY, Component name, ButtonTextureHolder buttonTextureHolder) {
        super (layoutElement.screen, layoutElement.x + offsetX,  layoutElement.y + offsetY, name, buttonTextureHolder);
        this.layoutElement = layoutElement;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height &&
                buttonTextureHolder.getDisabled().isPixelSolid(mouseX - x, mouseY - y) && !layoutElement.screen.isCurrentlyHoveringButtons();
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
            if (isHovered()) {
                renderToolTip(poseStack, mouseX, mouseY);
            }

            this.narrate();
            this.wasHovered = this.isHovered();
        }
    }

    protected boolean clicked(double mouseX, double mouseY) {
        return super.clicked(mouseX, mouseY) && !layoutElement.screen.isCurrentlyHoveringButtons();
    }

    public void updatePosition() {
        this.x = layoutElement.x + offsetX;
        this.y = layoutElement.y + offsetY;
    }

}

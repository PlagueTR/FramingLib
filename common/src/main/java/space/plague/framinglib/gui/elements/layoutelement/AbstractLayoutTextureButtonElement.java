package space.plague.framinglib.gui.elements.layoutelement;

import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.ApiStatus;

import space.plague.framinglib.gui.elements.AbstractTextureButtonElement;
import space.plague.framinglib.util.ButtonTextureHolder;

@ApiStatus.Internal
public abstract class AbstractLayoutTextureButtonElement extends AbstractTextureButtonElement {

    protected final FramingLayoutElement layoutElement;
    private final int offsetX;
    private final int offsetY;

    public AbstractLayoutTextureButtonElement(FramingLayoutElement layoutElement, int offsetX, int offsetY, Component name, ButtonTextureHolder buttonTextureHolder) {
        super (layoutElement.screen, layoutElement.getX() + offsetX,  layoutElement.getY() + offsetY, name, buttonTextureHolder);
        this.layoutElement = layoutElement;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public void updatePosition() {
        this.setX(layoutElement.getX() + offsetX);
        this.setY(layoutElement.getY() + offsetY);
    }

    public boolean isHovered() {
        return isHovered;
    }

}

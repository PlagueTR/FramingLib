package space.plague.framinglib.gui.elements.layoutelement;

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

    public void updatePosition() {
        this.x = layoutElement.x + offsetX;
        this.y = layoutElement.y + offsetY;
    }

}

package space.plague.framinglib.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface LayoutElementButton {

    LayoutElement getLayoutElement();

    boolean isDisabled();
    boolean isEnabled();

    boolean isHovered();

}

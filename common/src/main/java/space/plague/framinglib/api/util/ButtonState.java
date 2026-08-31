package space.plague.framinglib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public enum ButtonState {

    DISABLED,
    ACTIVE,
    HOVERED

}

package space.plague.framinglib.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface LayoutElement {

    boolean isCurrentlySnappingHorizontally();
    boolean isCurrentlySnappingVertically();

    boolean isEdited();
    boolean isNotDefault();

    void save();

    void resetValue();

}

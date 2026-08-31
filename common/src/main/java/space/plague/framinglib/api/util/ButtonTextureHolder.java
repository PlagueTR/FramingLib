package space.plague.framinglib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.util.ButtonTextureHolderImpl;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface ButtonTextureHolder {

    static ButtonTextureHolder create(@NotNull TextureInfo disabledTI, @Nullable TextureInfo activeTI, @Nullable TextureInfo hoveredTI){
        return new ButtonTextureHolderImpl(disabledTI, activeTI, hoveredTI);
    }

    @NotNull TextureInfo getDisabledTextureInfo();

    @Nullable TextureInfo getActiveTextureInfo();

    @Nullable TextureInfo getHoveredTextureInfo();

}

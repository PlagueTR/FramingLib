package space.plague.framinglib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.impl.TextureUVImpl;

import java.awt.Rectangle;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface TextureUV {

    static TextureUV createFromRegion(int atlasWidth, int atlasHeight, @Nullable Rectangle region) {
        return new TextureUVImpl(atlasWidth, atlasHeight, region);
    }

    static TextureUV createFromUV(float u_min, float u_max, float v_min, float v_max) {
        return new TextureUVImpl(u_min, u_max, v_min, v_max);
    }

    float getUMin();
    float getUMax();
    float getVMin();
    float getVMax();

}

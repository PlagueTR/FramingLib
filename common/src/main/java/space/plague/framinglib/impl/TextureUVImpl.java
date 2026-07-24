package space.plague.framinglib.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.util.TextureUV;

import java.awt.Rectangle;

@Environment(EnvType.CLIENT)
public class TextureUVImpl implements TextureUV {

    private final float u_min;
    private final float u_max;
    private final float v_min;
    private final float v_max;

    public TextureUVImpl(float uMin, float uMax, float vMin, float vMax) {
        this.u_min = uMin;
        this.u_max = uMax;
        this.v_min = vMin;
        this.v_max = vMax;
    }

    public TextureUVImpl(int atlasWidth, int atlasHeight, @Nullable Rectangle region) {
        if (atlasWidth == -1 || atlasHeight == -1 || region == null) {
            this.u_min = 0.0f;
            this.u_max = 1.0f;
            this.v_min = 0.0f;
            this.v_max = 1.0f;
        }
        else {
            this.u_min = (float) region.x / (float) atlasWidth;
            this.u_max = (float) (region.x + region.width) / (float) atlasWidth;
            this.v_min = (float) region.y / (float) atlasHeight;
            this.v_max = (float) (region.y + region.height) / (float) atlasHeight;
        }
    }

    @Override
    public float getUMin() {
        return u_min;
    }

    @Override
    public float getUMax() {
        return u_max;
    }

    @Override
    public float getVMin() {
        return v_min;
    }

    @Override
    public float getVMax() {
        return v_max;
    }

}

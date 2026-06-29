package space.plague.framinglib.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import space.plague.framinglib.api.util.Color;

@Environment(EnvType.CLIENT)
public class ColorImpl implements Color {
    private final float r;
    private final float g;
    private final float b;
    private final float a;

    public ColorImpl(int red, int green, int blue) {
        this.r = Math.max(0, Math.min(255, red)) / 255.0f;
        this.g = Math.max(0, Math.min(255, green)) / 255.0f;
        this.b = Math.max(0, Math.min(255, blue)) / 255.0f;
        this.a = 1.0f;
    }

    public ColorImpl(int red, int green, int blue, int alpha) {
        this.r = Math.max(0, Math.min(255, red)) / 255.0f;
        this.g = Math.max(0, Math.min(255, green)) / 255.0f;
        this.b = Math.max(0, Math.min(255, blue)) / 255.0f;
        this.a = Math.max(0, Math.min(255, alpha)) / 255.0f;
    }

    public ColorImpl(float red, float green, float blue) {
        this.r = Math.max(0.0f, Math.min(1.0f, red));
        this.g = Math.max(0.0f, Math.min(1.0f, green));
        this.b = Math.max(0.0f, Math.min(1.0f, blue));
        this.a = 1.0f;
    }

    public ColorImpl(float red, float green, float blue, float alpha) {
        this.r = Math.max(0.0f, Math.min(1.0f, red));
        this.g = Math.max(0.0f, Math.min(1.0f, green));
        this.b = Math.max(0.0f, Math.min(1.0f, blue));
        this.a = Math.max(0.0f, Math.min(1.0f, alpha));
    }

    public ColorImpl(String hex) {
        if (hex == null) {
            throw new IllegalArgumentException("Hex string cannot be null");
        }
        String cleanHex = hex.startsWith("#") ? hex.substring(1) : hex;
        if (cleanHex.length() != 6 && cleanHex.length() != 8) {
            throw new IllegalArgumentException("Hex string must be either 6 or 8 characters long");
        }
        try {
            this.r = Math.max(0, Math.min(255, Integer.parseInt(cleanHex.substring(0, 2), 16))) / 255.0f;
            this.g = Math.max(0, Math.min(255, Integer.parseInt(cleanHex.substring(2, 4), 16))) / 255.0f;
            this.b = Math.max(0, Math.min(255, Integer.parseInt(cleanHex.substring(4, 6), 16))) / 255.0f;
            if (cleanHex.length() == 6) {
                this.a = 1.0f;
            }
            else {
                this.a = Math.max(0, Math.min(255, Integer.parseInt(cleanHex.substring(6, 8), 16))) / 255.0f;
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid characters in hex string: " + hex, e);
        }
    }

    @Override
    public float getRedFloat() {
        return r;
    }

    @Override
    public float getGreenFloat() {
        return g;
    }

    @Override
    public float getBlueFloat() {
        return b;
    }

    @Override
    public float getAlphaFloat() {
        return a;
    }

    @Override
    public int getRedInt() {
        return Math.round(r * 255.0f);
    }

    @Override
    public int getGreenInt() {
        return Math.round(g * 255.0f);
    }

    @Override
    public int getBlueInt() {
        return Math.round(b * 255.0f);
    }

    @Override
    public int getAlphaInt() {
        return Math.round(a * 255.0f);
    }

    @Override
    public int getARGB() {
        return (Math.round(a * 255.0f) << 24) | (Math.round(r * 255.0f) << 16) | (Math.round(g * 255.0f) << 8) | Math.round(b * 255.0f);
    }

    @Override
    public int getRGB() {
        return 0xFF000000 | (Math.round(r * 255.0f) << 16) | (Math.round(g * 255.0f) << 8) | Math.round(b * 255.0f);
    }
}

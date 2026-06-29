package space.plague.framinglib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import space.plague.framinglib.impl.ColorImpl;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface Color {

    static Color create(int red, int green, int blue) {
        return new ColorImpl(red, green, blue);
    }
    static Color create(int red, int green, int blue, int alpha) {
        return new ColorImpl(red, green, blue, alpha);
    }

    static Color create(float red, float green, float blue) {
        return new ColorImpl(red, green, blue);
    }
    static Color create(float red, float green, float blue, float alpha) {
        return new ColorImpl(red, green, blue, alpha);
    }

    static Color create(String hex) {
        return new ColorImpl(hex);
    }

    float getRedFloat();
    float getGreenFloat();
    float getBlueFloat();
    float getAlphaFloat();

    int getRedInt();
    int getGreenInt();
    int getBlueInt();
    int getAlphaInt();

    int getARGB();
    int getRGB();

}

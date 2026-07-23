package space.plague.framinglib.util;

public class MathUtils {

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

}

package space.plague.framinglib.util;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class MathUtils {

    public static int clamp(int value, int min, int max) {
        if (min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }
        return Math.max(min, Math.min(max, value));
    }

}

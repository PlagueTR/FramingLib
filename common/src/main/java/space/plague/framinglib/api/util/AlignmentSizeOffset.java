package space.plague.framinglib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.NotNull;

import space.plague.framinglib.impl.AlignmentSizeOffsetImpl;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface AlignmentSizeOffset {

    static AlignmentSizeOffset create(int x, int y, int width, int height, float scale, Alignments.HAlignment hAlignment, Alignments.VAlignment vAlignment) {
        return new AlignmentSizeOffsetImpl(x, y, width, height, scale, Alignments.create(hAlignment, vAlignment));
    }

    static AlignmentSizeOffset create(int x, int y, int width, int height, float scale, @NotNull Alignments alignment) {
        return new AlignmentSizeOffsetImpl(x, y, width, height, scale, alignment);
    }

    int getX();
    int getY();

    int getActualX();
    int getActualY();

    int getWidth();
    int getHeight();
    float getScale();

    int getScaledWidth();
    int getScaledHeight();

    Alignments getAlignment();
    Alignments.HAlignment getHAlignment();
    Alignments.VAlignment getVAlignment();

}

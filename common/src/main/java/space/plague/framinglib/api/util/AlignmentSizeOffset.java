package space.plague.framinglib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.NotNull;

import space.plague.framinglib.impl.AlignmentSizeOffsetImpl;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface AlignmentSizeOffset {

    static AlignmentSizeOffset create(int offsetX, int offsetY, int width, int height, @NotNull Alignments alignment, @NotNull Alignments screenAlignment) {
        return new AlignmentSizeOffsetImpl(offsetX, offsetY, width, height, alignment, screenAlignment);
    }

    static AlignmentSizeOffset fromActualPosition(int actualX, int actualY, int width, int height, @NotNull Alignments screenAlignment) {
        return new AlignmentSizeOffsetImpl(actualX, actualY, width, height, screenAlignment);
    }

    int getOffsetX();
    int getOffsetY();
    void setOffsetX(int offsetX);
    void setOffsetY(int offsetY);

    int getActualX();
    int getActualY();

    int getWidth();
    int getHeight();
    void setWidth(int width);
    void setHeight(int height);

    Alignments getAlignment();
    void setAlignment(@NotNull Alignments alignment);

    Alignments getScreenAlignment();
    void setScreenAlignment(@NotNull Alignments screenAlignment);

    void markDirty();
    boolean isDirty();

    void setIsEditing(boolean editing);
    boolean isEditing();

    boolean hasUnsavedChanges();
    boolean isSimilar(AlignmentSizeOffset other);

    AlignmentSizeOffset copy();

}

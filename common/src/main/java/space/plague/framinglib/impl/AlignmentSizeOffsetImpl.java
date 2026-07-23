package space.plague.framinglib.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.util.AlignmentSizeOffset;
import space.plague.framinglib.api.util.Alignments;
import space.plague.framinglib.util.PositioningHelper;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class AlignmentSizeOffsetImpl implements AlignmentSizeOffset {
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;
    private final float scale;
    @NotNull
    private final Alignments alignment;

    public AlignmentSizeOffsetImpl(int offsetX, int offsetY, int width, int height, float scale, @NotNull Alignments alignment) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.alignment = alignment;
    }

    @Override
    public int getOffsetX() {
        return this.offsetX;
    }

    @Override
    public int getOffsetY() {
        return this.offsetY;
    }

    @Override
    public int getActualX() {
        return PositioningHelper.getActualX(this);
    }

    @Override
    public int getActualY() {
        return PositioningHelper.getActualY(this);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public float getScale() {
        return this.scale;
    }

    @Override
    public int getScaledWidth() {
        return (int) (this.width * this.scale);
    }

    @Override
    public int getScaledHeight() {
        return (int) (this.height * this.scale);
    }

    @Override
    public @NotNull Alignments getAlignment() {
        return this.alignment;
    }

    @Override
    public Alignments.HAlignment getHAlignment() {
        return this.alignment.getHAlignment();
    }

    @Override
    public Alignments.VAlignment getVAlignment() {
        return this.alignment.getVAlignment();
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AlignmentSizeOffsetImpl other = (AlignmentSizeOffsetImpl)o;
        return offsetX == other.offsetX && offsetY == other.offsetY && width == other.width && height == other.height && Float.compare(scale, other.scale) == 0 && alignment.equals(other.alignment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offsetX, offsetY, width, height, scale, alignment);
    }

}

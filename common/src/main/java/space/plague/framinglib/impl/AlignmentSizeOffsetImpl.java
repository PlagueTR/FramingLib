package space.plague.framinglib.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.util.AlignmentSizeOffset;
import space.plague.framinglib.api.util.Alignments;
import space.plague.framinglib.util.PositioningHelper;
import space.plague.framinglib.util.DisplayResizeNotifier;

import java.util.Objects;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class AlignmentSizeOffsetImpl implements AlignmentSizeOffset {
    private int offsetX = 0;
    private int offsetY = 0;
    private int width = 1;
    private int height = 1;
    @NotNull
    private Alignments alignment;
    @NotNull
    private Alignments screenAlignment;

    private boolean dirtyX = true;
    private boolean dirtyY = true;
    private int actualX = 0;
    private int actualY = 0;

    private boolean isEditing = false;
    private int editingX = 0;
    private int editingY = 0;
    @NotNull
    private Alignments editingAlignment;

    public AlignmentSizeOffsetImpl(int offsetX, int offsetY, int width, int height, @NotNull Alignments alignment, @NotNull Alignments screenAlignment) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.alignment = alignment;
        this.screenAlignment = screenAlignment;

        this.editingX = this.offsetX;
        this.editingY = this.offsetY;
        this.editingAlignment = this.alignment.copy();

        DisplayResizeNotifier.register(this);
    }

    public AlignmentSizeOffsetImpl(int actualX, int actualY, int width, int height, @NotNull Alignments screenAlignment) {
        this.screenAlignment = screenAlignment;

        this.offsetX = PositioningHelper.getOffsetX(actualX, width);
        this.offsetY = PositioningHelper.getOffsetY(actualY, height);
        this.width = width;
        this.height = height;
        this.alignment = Alignments.create(PositioningHelper.getHAlignment(actualX, width), PositioningHelper.getVAlignment(actualY, height));

        this.editingX = this.offsetX;
        this.editingY = this.offsetY;
        this.editingAlignment = this.alignment.copy();

        DisplayResizeNotifier.register(this);
    }

    public AlignmentSizeOffsetImpl() {
        this.alignment = Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP);
        this.screenAlignment = Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP);
        this.editingAlignment = Alignments.create(Alignments.HAlignment.LEFT, Alignments.VAlignment.TOP);
    }

    @Override
    public int getOffsetX() {
        return this.isEditing ? this.editingX : this.offsetX;
    }

    @Override
    public int getOffsetY() {
        return this.isEditing ? this.editingY : this.offsetY;
    }

    @Override
    public void setOffsetX(int offsetX) {
        if (this.isEditing) {
            this.editingX = offsetX;
        }
        else {
            this.offsetX = offsetX;
        }
        markDirty();
    }

    @Override
    public void setOffsetY(int offsetY) {
        if (this.isEditing) {
            this.editingY = offsetY;
        }
        else {
            this.offsetY = offsetY;
        }
        markDirty();
    }

    @Override
    public int getActualX() {
        if (dirtyX) {
            this.actualX = PositioningHelper.getActualX(this);
            dirtyX = false;
        }
        return this.actualX;
    }

    @Override
    public int getActualY() {
        if (dirtyY) {
            this.actualY = PositioningHelper.getActualY(this);
            dirtyY = false;
        }
        return this.actualY;
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
    public void setWidth(int width) {
        this.width = width;
        markDirty();
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
        markDirty();
    }

    @Override
    public @NotNull Alignments getAlignment() {
        return this.isEditing ? this.editingAlignment : this.alignment;
    }

    @Override
    public void setAlignment(@NotNull Alignments alignment) {
        if (this.isEditing) {
            this.editingAlignment = alignment;
        }
        else {
            this.alignment = alignment;
        }
        markDirty();
    }

    @Override
    public @NotNull Alignments getScreenAlignment() {
        return this.screenAlignment;
    }

    @Override
    public void setScreenAlignment(@NotNull Alignments screenAlignment) {
        this.screenAlignment = screenAlignment;
        this.markDirty();
    }

    @Override
    public void markDirty() {
        this.dirtyX = true;
        this.dirtyY = true;
    }

    @Override
    public boolean isDirty() {
        return this.dirtyX || this.dirtyY;
    }

    @Override
    public void setIsEditing(boolean editing) {
        this.isEditing = editing;
        markDirty();
    }

    @Override
    public boolean isEditing() {
        return this.isEditing;
    }

    @Override
    public boolean hasUnsavedChanges() {
        if (!this.isEditing) {
            return false;
        }
        return this.editingX != this.offsetX || this.editingY != this.offsetY || !this.editingAlignment.equals(this.alignment);
    }

    @Override
    public boolean isSimilar(AlignmentSizeOffset other) {
        return getOffsetX() == other.getOffsetX() && getOffsetY() == other.getOffsetY() && getWidth() == other.getWidth() && getHeight() == other.getHeight() && getAlignment().equals(other.getAlignment());
    }

    @Override
    public AlignmentSizeOffset copy() {
        AlignmentSizeOffsetImpl copy = new AlignmentSizeOffsetImpl();

        copy.offsetX = this.offsetX;
        copy.offsetY = this.offsetY;
        copy.width = this.width;
        copy.height = this.height;
        copy.alignment = this.alignment.copy();
        copy.screenAlignment = this.screenAlignment.copy();
        copy.dirtyX = this.dirtyX;
        copy.dirtyY = this.dirtyY;
        copy.actualX = this.actualX;
        copy.actualY = this.actualY;
        copy.isEditing = this.isEditing;
        copy.editingX = this.editingX;
        copy.editingY = this.editingY;
        copy.editingAlignment = this.editingAlignment.copy();

        return copy;
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
        return offsetX == other.offsetX && offsetY == other.offsetY && width == other.width && height == other.height && alignment.equals(other.alignment) && screenAlignment.equals(other.screenAlignment) && dirtyX == other.dirtyX && dirtyY == other.dirtyY && actualX == other.actualX && actualY == other.actualY && isEditing == other.isEditing && editingX == other.editingX && editingAlignment.equals(other.editingAlignment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offsetX, offsetY, width, height, alignment, screenAlignment, dirtyX, dirtyY, actualX, actualY, isEditing, editingX, editingY, editingAlignment);
    }

}

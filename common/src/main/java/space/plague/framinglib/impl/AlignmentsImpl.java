package space.plague.framinglib.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.Nullable;

import space.plague.framinglib.api.util.Alignments;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class AlignmentsImpl implements Alignments {

    private final HAlignment hAlignment;
    private final VAlignment vAlignment;

    public AlignmentsImpl(HAlignment hAlignment, VAlignment vAlignment) {
        this.hAlignment = hAlignment;
        this.vAlignment = vAlignment;
    }

    @Override
    public HAlignment getHAlignment() {
        return hAlignment;
    }

    @Override
    public VAlignment getVAlignment() {
        return vAlignment;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AlignmentsImpl other = (AlignmentsImpl)o;
        return hAlignment == other.hAlignment && vAlignment == other.vAlignment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hAlignment, vAlignment);
    }
}

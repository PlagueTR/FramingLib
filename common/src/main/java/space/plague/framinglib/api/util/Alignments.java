package space.plague.framinglib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import space.plague.framinglib.impl.AlignmentsImpl;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface Alignments {

   enum HAlignment {
       LEFT,
       MIDDLE,
       RIGHT
   }

   enum VAlignment {
       TOP,
       CENTER,
       BOTTOM
   }

    static Alignments create(HAlignment hAlignment, VAlignment vAlignment) {
        return new AlignmentsImpl(hAlignment, vAlignment);
    }

   HAlignment getHAlignment();
   VAlignment getVAlignment();

}

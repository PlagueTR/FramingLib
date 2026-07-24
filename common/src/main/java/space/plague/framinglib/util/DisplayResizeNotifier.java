package space.plague.framinglib.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import space.plague.framinglib.impl.AlignmentSizeOffsetImpl;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

@Environment(EnvType.CLIENT)
public class DisplayResizeNotifier {
    private static final Set<AlignmentSizeOffsetImpl> ASO_REGISTRY = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap<>()));

    public static void register(AlignmentSizeOffsetImpl alignmentSizeOffset) {
        ASO_REGISTRY.add(alignmentSizeOffset);
    }

    public static void onResize() {

        synchronized (ASO_REGISTRY) {
            ASO_REGISTRY.forEach(AlignmentSizeOffsetImpl::markDirty);
        }

    }

}

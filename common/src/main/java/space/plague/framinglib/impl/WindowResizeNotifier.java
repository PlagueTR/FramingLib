package space.plague.framinglib.impl;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

@Environment(EnvType.CLIENT)
public class WindowResizeNotifier {
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

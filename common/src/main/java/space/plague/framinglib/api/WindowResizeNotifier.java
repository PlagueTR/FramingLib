package space.plague.framinglib.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import space.plague.framinglib.impl.WindowResizeNotifierImpl;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public interface WindowResizeNotifier {

    static void subscribe(Runnable runnable) {
        WindowResizeNotifierImpl.subscribe(runnable);
    }

    static void unsubscribe(Runnable runnable) {
        WindowResizeNotifierImpl.unsubscribe(runnable);
    }

}

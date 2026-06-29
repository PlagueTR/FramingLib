package space.plague.framinglib.impl;

import com.mojang.blaze3d.platform.Window;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.Minecraft;

import space.plague.framinglib.api.WindowResizeNotifier;
import space.plague.framinglib.util.PositioningHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Environment(EnvType.CLIENT)
public class WindowResizeNotifierImpl implements WindowResizeNotifier {
    private static final List<Runnable> subscribers = new ArrayList<>();

    public static void subscribe(Runnable runnable) {
        subscribers.add(runnable);
    }

    public static void unsubscribe(Runnable runnable) {
        subscribers.remove(runnable);
    }

    public static void onResize() {

        Minecraft mc = Minecraft.getInstance();
        if (mc == null) {
            return;
        }
        Window window = mc.getWindow();
        if (window == null) {
            return;
        }
        PositioningHelper.windowWidth = window.getGuiScaledWidth();
        PositioningHelper.windowHeight = window.getGuiScaledHeight();

        synchronized (subscribers) {
            Iterator<Runnable> iterator = subscribers.iterator();
            while (iterator.hasNext()) {
                Runnable runnable = iterator.next();
                if (runnable != null) {
                    runnable.run();
                }
                else {
                    iterator.remove();
                }
            }
        }
    }

}

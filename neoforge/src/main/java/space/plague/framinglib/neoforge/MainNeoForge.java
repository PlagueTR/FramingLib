package space.plague.framinglib.neoforge;

import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import org.jetbrains.annotations.ApiStatus;

import space.plague.framinglib.Main;
import space.plague.framinglib.demo.FramingLibDemo;

@ApiStatus.Internal
@Mod(Main.MOD_ID)
public final class MainNeoForge {
    public MainNeoForge() {
        Main.init();

        ModLoadingContext.get().registerExtensionPoint(
            IConfigScreenFactory.class,
            () -> (minecraftClient, screen) ->
                FramingLibDemo.getDemoLayoutConfigScreenBuilder().build();
        );
    }
}

package space.plague.framinglib.forge;

import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

import org.jetbrains.annotations.ApiStatus;

import space.plague.framinglib.Main;
import space.plague.framinglib.demo.FramingLibDemo;

@ApiStatus.Internal
@Mod(Main.MOD_ID)
public final class MainForge {
    public MainForge() {

        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
            () -> new ConfigScreenHandler.ConfigScreenFactory(
            (minecraft, screen) -> FramingLibDemo.getDemoLayoutConfigScreenBuilder().build()
        ));

        Main.init();
    }
}

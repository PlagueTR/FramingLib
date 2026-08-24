package space.plague.framinglib.forge;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fmlclient.ConfigGuiHandler;
import org.jetbrains.annotations.ApiStatus;

import space.plague.framinglib.Main;
import space.plague.framinglib.demo.FramingLibDemo;

@ApiStatus.Internal
@Mod(Main.MOD_ID)
public final class MainForge {
    public MainForge() {

        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class,
            () -> new ConfigGuiHandler.ConfigGuiFactory(
            (minecraft, screen) -> FramingLibDemo.getDemoLayoutConfigScreenBuilder().build()
        ));

        Main.init();
    }
}

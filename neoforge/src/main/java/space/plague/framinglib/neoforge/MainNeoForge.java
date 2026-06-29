package space.plague.framinglib.neoforge;

import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import space.plague.framinglib.Main;
import space.plague.framinglib.config.gui.GeneralOptionsScreen;

@Mod(Main.MOD_ID)
public final class MainNeoForge {
    public MainNeoForge() {
        Main.init();

        if (ModList.get().isLoaded("cloth_config")) {
            ModLoadingContext.get().registerExtensionPoint(
                    IConfigScreenFactory.class,
                    () -> (minecraftClient, screen) ->
                            GeneralOptionsScreen.get()
            );
        }
    }
}
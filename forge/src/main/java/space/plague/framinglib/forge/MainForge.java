package space.plague.framinglib.forge;

import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

import space.plague.framinglib.Main;
import space.plague.framinglib.demo.FramingLibDemo;

@Mod(Main.MOD_ID)
public final class MainForge {
    public MainForge() {

        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY,
            () -> ((minecraft, screen) -> FramingLibDemo.getDemoLayoutConfigScreenBuilder().build())
        );

        Main.init();
    }
}
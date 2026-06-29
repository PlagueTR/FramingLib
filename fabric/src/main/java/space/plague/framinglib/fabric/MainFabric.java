package space.plague.framinglib.fabric;

import net.fabricmc.api.ModInitializer;

import space.plague.framinglib.Main;

public final class MainFabric implements ModInitializer {
    @Override
    public void onInitialize() {

        Main.init();

    }
}
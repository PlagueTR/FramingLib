package space.plague.framinglib.fabric;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import org.jetbrains.annotations.ApiStatus;

import space.plague.framinglib.Main;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public final class MainFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        Main.init();

    }

}

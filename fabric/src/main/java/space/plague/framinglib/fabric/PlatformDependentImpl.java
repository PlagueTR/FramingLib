package space.plague.framinglib.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;

@ApiStatus.Internal
@Environment(EnvType.CLIENT)
public class PlatformDependentImpl {

    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }

}

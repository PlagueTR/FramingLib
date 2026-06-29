package space.plague.framinglib.forge;

import net.minecraftforge.fml.loading.FMLPaths;

import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;

@ApiStatus.Internal
public class PlatformDependentImpl {

    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }

}

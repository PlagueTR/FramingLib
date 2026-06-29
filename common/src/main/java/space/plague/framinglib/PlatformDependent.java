package space.plague.framinglib;

import org.jetbrains.annotations.ApiStatus;

import java.nio.file.Path;

@ApiStatus.Internal
public class PlatformDependent {

    @dev.architectury.injectables.annotations.ExpectPlatform
    public static Path getConfigDirectory() {
        throw new AssertionError();
    }

}

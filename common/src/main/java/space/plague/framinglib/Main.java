package space.plague.framinglib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class Main {
    public static final String MOD_ID = "framinglib";
    public static final String MOD_NAME = "Framing Lib";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        logInfo("Loading...");

        logInfo("All done!");
    }

    public static void logInfo(String message, Object... params) {
        LOGGER.info("[" + MOD_NAME + "] " + message, params);
    }

}

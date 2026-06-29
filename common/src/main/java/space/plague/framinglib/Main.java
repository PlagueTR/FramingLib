package space.plague.framinglib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Main {
    public static final String MOD_ID = "framinglib";
    public static final String MOD_NAME = "Framing Lib";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {

        LOGGER.info("[" + MOD_NAME + "] Loading...");

        LOGGER.info("[" + MOD_NAME + "] All done!");
    }

}
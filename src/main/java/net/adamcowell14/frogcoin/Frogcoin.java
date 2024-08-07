package net.adamcowell14.frogcoin;

import net.adamcowell14.frogcoin.config.Point;
import net.fabricmc.api.ModInitializer;
import net.adamcowell14.frogcoin.config.ConfigParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Frogcoin implements ModInitializer {
    public static final ConfigParser config = ConfigParser.INSTANCE;
    public static final String MOD_ID = "frogcoin";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static Point[] points = new Point[0];
    public static final String MOD_CMD = "fcbot";
    public static long delay = 250;
    public static boolean running = false;

    @Override
    public void onInitialize() {
        config.load();
        config.changeRunning(false);
    }
}

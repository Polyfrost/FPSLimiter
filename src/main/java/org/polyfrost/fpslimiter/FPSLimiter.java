package org.polyfrost.fpslimiter;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.polyfrost.fpslimiter.config.FPSLimiterConfig;

// TODO: Fabric Entrypoint
@Mod(modid = FPSLimiter.ID, name = FPSLimiter.NAME, version = FPSLimiter.VERSION)
public class FPSLimiter {
    public static final String ID = "@MOD_ID@";
    public static final String NAME = "@MOD_NAME@";
    public static final String VERSION = "@MOD_VERSION@";

    public static FPSLimiterConfig config;

    @Mod.EventHandler
    public void onFMLInitializeEvent(FMLInitializationEvent event) {
        initialize();
    }

    public void initialize() {
        config = new FPSLimiterConfig();
    }
}

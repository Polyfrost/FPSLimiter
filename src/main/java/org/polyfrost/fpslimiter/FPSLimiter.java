package org.polyfrost.fpslimiter;

//#if FABRIC
//$$ import net.fabricmc.api.ModInitializer;
//#elseif FORGE
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
//#endif

import org.polyfrost.fpslimiter.config.FPSLimiterConfig;

//#if FORGE-LIKE
@Mod(modid = FPSLimiter.ID, name = FPSLimiter.NAME, version = FPSLimiter.VERSION)
//#endif
public class FPSLimiter
        //#if FABRIC
        //$$ implements ModInitializer
        //#endif
{
    public static final String ID = "@MOD_ID@";
    public static final String NAME = "@MOD_NAME@";
    public static final String VERSION = "@MOD_VERSION@";

    public static FPSLimiterConfig config;

    //#if FABRIC
    //$$ @Override
    //#elseif FORGE
    @Mod.EventHandler
    //#endif
    public void onInitialize(
            //#if FORGE
            FMLInitializationEvent event
            //#endif
    ) {
        config = new FPSLimiterConfig();
    }
}
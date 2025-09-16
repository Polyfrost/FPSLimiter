package org.polyfrost.fpslimiter;

//#if FABRIC
//$$ import net.fabricmc.api.ModInitializer;
//#elseif FORGE
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
//#endif
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundManager;
import org.lwjgl.opengl.Display;
import org.polyfrost.fpslimiter.config.FPSLimiterConfig;
import org.polyfrost.fpslimiter.mixin.SoundHandlerAccessor;
import org.polyfrost.oneconfig.api.event.v1.EventManager;
import org.polyfrost.oneconfig.api.event.v1.events.TickEvent;
import org.polyfrost.oneconfig.api.event.v1.invoke.impl.Subscribe;

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


    private boolean previousActive = true;
    private float previousVolume = -1f;

    @Subscribe
    public void onTick(TickEvent.Start event) {
        boolean active = Display.isActive();
        if (active != previousActive) {
            previousActive = active;
            handleFocusChange();
        }
    }

    private void handleFocusChange() {
        if (previousActive) {
            if (previousVolume == -1f) return;
            ((SoundHandlerAccessor) Minecraft.getMinecraft().getSoundHandler()).getSoundManager().setSoundCategoryVolume(SoundCategory.MASTER, previousVolume);
            previousVolume = -1f;
        } else {
            SoundManager soundManager = ((SoundHandlerAccessor) Minecraft.getMinecraft().getSoundHandler()).getSoundManager();
            previousVolume = Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MASTER);
            if (previousVolume == -1f) return;
            soundManager.setSoundCategoryVolume(SoundCategory.MASTER, config.unfocusedSounds * previousVolume);
        }
    }

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
        EventManager.INSTANCE.register(this);
    }
}
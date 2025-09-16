package org.polyfrost.fpslimiter.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import org.lwjgl.opengl.Display;
import org.polyfrost.fpslimiter.FPSLimiter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    // Also doesnt work
    @Redirect(method = "getLimitFramerate", at = @At(value = "CONSTANT", args = "intValue=30"))
    private int redirectIntValue(Integer original) {
        return FPSLimiter.config.useMaxMainMenuFpsLimit ? FPSLimiter.config.maxMainMenuFpsLimit : original;
    }

    @Redirect(method = "getLimitFramerate", at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;limitFramerate:I"))
    private int setIngameLimit(GameSettings instance) {
        if (Display.isActive()) {
            if (FPSLimiter.config.useFpsLimit) {
                return FPSLimiter.config.fpsLimit;
            }
        } else if (FPSLimiter.config.useUnfocusedFpsLimit) {
            return FPSLimiter.config.unfocusedFpsLimit;
        }

        return instance.limitFramerate;
    }

    @Inject(method = "isFramerateLimitBelowMax", at = @At("HEAD"), cancellable = true)
    private void removeMax(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(FPSLimiter.config.useFpsLimit);
    }
}

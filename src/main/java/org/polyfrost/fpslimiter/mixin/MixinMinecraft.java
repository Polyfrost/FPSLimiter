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
    @ModifyConstant(method = "getLimitFramerate", constant = @Constant(intValue = 30))
    private int setMainMenuLimit(int constant) {
        return FPSLimiter.config.useMaxMainMenuFpsLimit ? FPSLimiter.config.maxMainMenuFpsLimit : 30;
    }

    @Redirect(method = "getLimitFramerate", at = @At(value = "FIELD", target = "Lnet/minecraft/client/settings/GameSettings;limitFramerate:I"))
    private int idk(GameSettings instance) {
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

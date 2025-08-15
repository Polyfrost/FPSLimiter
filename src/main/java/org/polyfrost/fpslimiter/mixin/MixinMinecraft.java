package org.polyfrost.fpslimiter.mixin;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.polyfrost.fpslimiter.FPSLimiter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Inject(method = "getLimitFramerate", at = @At("HEAD"), cancellable = true)
    private void setFramerateLimit(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(Display.isActive() ? FPSLimiter.config.fpsLimit : FPSLimiter.config.unfocusedFpsLimit);
    }

    @Inject(method = "isFramerateLimitBelowMax", at = @At("HEAD"), cancellable = true)
    private void removeMax(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}

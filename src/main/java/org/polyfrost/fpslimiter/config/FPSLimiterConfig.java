package org.polyfrost.fpslimiter.config;

import org.polyfrost.fpslimiter.FPSLimiter;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.Checkbox;
import org.polyfrost.oneconfig.api.config.v1.annotations.Number;
import org.polyfrost.oneconfig.api.config.v1.annotations.Slider;

public class FPSLimiterConfig extends Config {
    @Checkbox(
            title = "Use Custom FPS Limit"
    )
    public boolean useFpsLimit = false;

    @Number(
            title = "Custom FPS Limit",
            min = 5, max = Integer.MAX_VALUE
    )
    public int fpsLimit = 120;

    @Checkbox(
            title = "Use Max Main Menu FPS Limit"
    )
    public boolean useMaxMainMenuFpsLimit = false;

    @Slider(
            title = "Max Main Menu FPS Limit",
            description = "Seems to not work :(",
            min = 5f, max = 255f, step = 5f
    )
    public int maxMainMenuFpsLimit = 30;

    @Checkbox(
            title = "Use Unfocused FPS Limit"
    )
    public boolean useUnfocusedFpsLimit = false;

    @Number(
            title = "Unfocused FPS Limit",
            min = 5f, max = 255f
    )
    public int unfocusedFpsLimit = 60;

    @Slider( // todo: number inputs seem to be broken
            title = "Unfocused Sounds",
            min = 0f, max = 1f, step = 0.05f
    )
    public float unfocusedSounds = 1f;

    public FPSLimiterConfig() {
        super(FPSLimiter.ID + ".json", FPSLimiter.NAME, Category.QOL);
    }
}

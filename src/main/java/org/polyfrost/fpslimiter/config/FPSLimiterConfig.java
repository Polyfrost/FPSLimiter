package org.polyfrost.fpslimiter.config;

import org.polyfrost.fpslimiter.FPSLimiter;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.Number;

public class FPSLimiterConfig extends Config {
    // todo: add toggles for each setting
    // todo: main menu fps limit
    // todo: unfocused sound volume

    @Number(
            title = "Custom FPS Limit",
            min = 5, max = Integer.MAX_VALUE
    )
    public int fpsLimit = 120;

    @Number(
            title = "Unfocused FPS Limit",
            min = 5f
    )
    public int unfocusedFpsLimit = 60;

    public FPSLimiterConfig() {
        super(FPSLimiter.ID + ".json", FPSLimiter.NAME, Category.QOL);
    }
}

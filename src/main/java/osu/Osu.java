package osu;

import v1.ApiHandler;

public class Osu {
    private String keyV1;

    private ApiHandler apiHandler;

    public Osu(OsuSettings osuSettings) {
        this.keyV1 = osuSettings.getKey();

    }

    public ApiHandler getV1() {
        return apiHandler;
    }
}

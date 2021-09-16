package v1;

import v1.entities.beatmap.Beatmap;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Route<T> {
    private static final String OSU_API_V1_URL = "https://osu.ppy.sh/api/";

    public static final Route<Beatmap> GET_BEATMAPS = new Route<>("get_beatmaps", Beatmap.class);
    public static final Route<Beatmap> GET_USER = new Route<>("get_user", Beatmap.class);



    private final String route;
    private final Class<T> type;

    public Route(String route, Class<T> type) {
        this.route = OSU_API_V1_URL + route;
        this.type = type;
    }

    public String compile(String key) {
        return route + "?k=" + key;
    }

    public Class<T> getType() {
        return type;
    }
}

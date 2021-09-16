package osu;

import futures.Future;
import v1.entities.beatmap.Beatmap;
import v1.entities.beatmap.BeatmapImpl;
import v1.entities.beatmap.BeatmapRequestBuilder;

public interface Osu {
    Future<Beatmap> retrieveBeatmap(BeatmapRequestBuilder beatmapRequestBuilder);
}

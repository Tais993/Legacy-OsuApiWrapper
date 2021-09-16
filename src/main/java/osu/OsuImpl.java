package osu;

import futures.Future;
import futures.FutureImpl;
import v1.entities.beatmap.Beatmap;
import v1.entities.beatmap.BeatmapImpl;
import v1.entities.beatmap.BeatmapRequestBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;

public class OsuImpl implements Osu {
    private final String key;
    private final EntityBuilder entityBuilder;

    private final ArrayBlockingQueue<Runnable> blockingQueue;
    private final ExecutorService executorService;

    public OsuImpl(OsuSettings osuSettings) {
        this.key = osuSettings.getKey();
        this.blockingQueue = osuSettings.getBlockingQueue();
        this.executorService = osuSettings.getExecutorService();
        this.entityBuilder = new EntityBuilder();
    }

    public EntityBuilder getEntityBuilder() {
        return entityBuilder;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public Future<Beatmap> retrieveBeatmap(BeatmapRequestBuilder beatmapRequestBuilder) {
        return new FutureImpl<>(this, beatmapRequestBuilder, Beatmap.class);
    }
}

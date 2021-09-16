package osu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OsuSettings {
    private final String key;

    private final ArrayBlockingQueue<Runnable> blockingQueue;
    private final ExecutorService executorService;

    public OsuSettings(OsuBuilder osuSB) {
        key = osuSB.getKey();

        blockingQueue = new ArrayBlockingQueue<>(osuSB.getQueueCapacity());
        executorService = new ThreadPoolExecutor(osuSB.getThreadCount(), osuSB.getMaxThreadCount(), 100L, TimeUnit.SECONDS, blockingQueue);
    }

    public String getKey() {
        return key;
    }

    public ArrayBlockingQueue<Runnable> getBlockingQueue() {
        return blockingQueue;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}

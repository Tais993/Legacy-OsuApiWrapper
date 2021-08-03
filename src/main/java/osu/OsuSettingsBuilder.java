package osu;

public class OsuSettingsBuilder {
    private String key = "";

    private int threadCount = 1;
    private int maxThreadCount = 10;
    private int queueCapacity = 100;

    private boolean separateQueues = false;

    public OsuSettingsBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    protected String getKey() {
        return key;
    }

    public OsuSettingsBuilder setThreadCount(int threadCount) {
        this.threadCount = threadCount;
        return this;
    }

    protected int getThreadCount() {
        return threadCount;
    }

    public OsuSettingsBuilder setMaxQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
        return this;
    }

    protected int getQueueCapacity() {
        return queueCapacity;
    }

    public OsuSettingsBuilder setMaxThreadCount(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
        return this;
    }

    protected int getMaxThreadCount() {
        return maxThreadCount;
    }

    public OsuSettingsBuilder isSeparateQueues(boolean separateQueues) {
        this.separateQueues = separateQueues;
        return this;
    }

    protected boolean isSeparateQueues() {
        return separateQueues;
    }

    public OsuSettings build() {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null!");
        }
        return new OsuSettings(this);
    }
}

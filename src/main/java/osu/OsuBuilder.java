package osu;

public class OsuBuilder {
    private String key = "";

    private int threadCount = 1;
    private int maxThreadCount = 10;
    private int queueCapacity = 100;

    private boolean separateQueues = false;

    public OsuBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    protected String getKey() {
        return key;
    }

    public OsuBuilder setThreadCount(int threadCount) {
        this.threadCount = threadCount;
        return this;
    }

    protected int getThreadCount() {
        return threadCount;
    }

    public OsuBuilder setMaxQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
        return this;
    }

    protected int getQueueCapacity() {
        return queueCapacity;
    }

    public OsuBuilder setMaxThreadCount(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
        return this;
    }

    protected int getMaxThreadCount() {
        return maxThreadCount;
    }

    public OsuBuilder isSeparateQueues(boolean separateQueues) {
        this.separateQueues = separateQueues;
        return this;
    }

    protected boolean isSeparateQueues() {
        return separateQueues;
    }

    public Osu build() {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null!");
        }
        return new OsuImpl(new OsuSettings(this));
    }
}

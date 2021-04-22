package futures;

import v1.entities.OsuEntity;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class ConvertedFuture<T extends OsuEntity, O> {
    private final FutureImpl<T> asyncFuture;
    private final Function<T, ? extends O> converter;

    public ConvertedFuture(FutureImpl<T> asyncFuture, Function<T, ? extends O> converter) {
        this.asyncFuture = asyncFuture;
        this.converter = converter;
    }

    public CompletableFuture<Void> submit() {
        return asyncFuture.submit();
    }

    public void runAsync(Consumer<O> consumer) {
        asyncFuture.runAsync(dbObject -> consumer.accept(converter.apply(dbObject)));
    }

    public O runSync() {
        try {
            return converter.apply(asyncFuture.runSync());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
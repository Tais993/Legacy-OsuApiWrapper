package futures;

import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;

public class MapFutureImpl<I, O> extends MapFuture<I, O> {
    protected final Function<? super O, ? extends I> map;

    protected MapFutureImpl(Future<O> future, Function<? super O, ? extends I> map) {
        super(future);

        this.map = map;
    }

    @Override
    public void runAsync(@Nullable Consumer<I> onSuccess, @Nullable Consumer<Exception> onFailure) {
        future.runAsync(result -> {
            if (onSuccess != null) {
                onSuccess.accept(map.apply(result));
            } else {
                map.apply(result);
            }
        }, onFailure);
    }

    @Override
    public I runSync() throws Exception {
        return null;
    }
}

package futures;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Future<T> {

    default void runAsync() {
        runAsync(null);
    }

    default void runAsync(@Nullable Consumer<T> onSuccess) {
        runAsync(onSuccess, null);
    }

    void runAsync(@Nullable Consumer<T> onSuccess, @Nullable Consumer<Exception> onFailure);

    T runSync() throws IOException, InterruptedException;

    default <O> Future<O> map(@NotNull Function<T, O> converter) {
        return new MapFutureImpl<>(this, converter);
    }
}

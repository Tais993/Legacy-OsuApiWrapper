package futures;

import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class MapFuture<I, O> implements Future<I> {
    protected final Future<O> future;

    protected MapFuture(Future<O> future) {
        this.future = future;
    }
}

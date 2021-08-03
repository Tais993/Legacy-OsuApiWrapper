package futures;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;
import java.util.function.Function;

public class FutureImpl<T> implements Future<T> {
    private final Callable<T> callable;
    private final ExecutorService executorService;

    public FutureImpl(Callable<T> callable, ExecutorService executorService) {
        this.callable = callable;
        this.executorService = executorService;
    }

    @Override
    public void runAsync(@Nullable Consumer<T> onSuccess, @Nullable Consumer<Exception> onFailure) {
        executorService.submit(() -> {
            try {
                if (onSuccess != null) {
                    onSuccess.accept(callable.call());
                } else {
                    callable.call();
                }
            } catch (Exception e) {
                if (onFailure != null) {
                    onFailure.accept(e);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public T runSync() throws Exception {
        return callable.call();
    }
}

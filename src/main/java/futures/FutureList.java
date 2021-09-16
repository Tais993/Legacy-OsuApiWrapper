package futures;

import osu.Osu;
import osu.OsuImpl;
import v1.entities.RequestBuilder;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public interface FutureList<T> extends Future<List<T>> {

    void forEachAsync(Consumer<T> onSuccess);

    void forEachAsync(Consumer<T> onSuccess, Consumer<Exception> onFailure);

    void forEachSync(Consumer<T> onSuccess) throws IOException, InterruptedException;
}

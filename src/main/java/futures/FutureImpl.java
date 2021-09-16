package futures;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;
import osu.Osu;
import osu.OsuImpl;
import v1.entities.RequestBuilder;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;

public class FutureImpl<T> implements Future<T> {
    private final OsuImpl osu;

    private final RequestBuilder requestBuilder;
    private final Function<JsonObject, T> jsonToObject;
    private final ExecutorService executorService;

    public FutureImpl(Osu osu, RequestBuilder requestBuilder, Class<T> clazz) {
        this.osu = (OsuImpl) osu;
        this.requestBuilder = requestBuilder;
        this.jsonToObject = this.osu.getEntityBuilder().getConverterFromClass(clazz);
        this.executorService = ((OsuImpl) osu).getExecutorService();
    }

    @Override
    public void runAsync(@Nullable Consumer<T> onSuccess, @Nullable Consumer<Exception> onFailure) {

        executorService.submit(() -> {
            try {
                T entity = jsonToObject.apply(osu.getEntityBuilder().getJsonFromUrlString(requestBuilder.toUrl()));
                if (onSuccess != null) {
                    onSuccess.accept(entity);
                }
            } catch (IOException | InterruptedException e) {
                if (onFailure != null) {
                    onFailure.accept(e);
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public T runSync() throws IOException, InterruptedException {
        return jsonToObject.apply(osu.getEntityBuilder().getJsonFromUrlString(requestBuilder.toUrl()));
    }
}

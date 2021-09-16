package v1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import futures.Future;
import futures.FutureImpl;
import osu.OsuSettings;
import v1.entities.beatmap.BeatmapImpl;
import v1.entities.beatmap.BeatmapRequestBuilder;
import v1.entities.bestperformance.BestPerformance;
import v1.entities.bestperformance.BestPerformanceRequestBuilder;
import v1.entities.multiplayer.Match;
import v1.entities.multiplayer.MatchRequestBuilder;
import v1.entities.replay.Replay;
import v1.entities.replay.ReplayRequestBuilder;
import v1.entities.scores.Score;
import v1.entities.scores.ScoresRequestBuilder;
import v1.entities.user.User;
import v1.entities.user.UserRequestBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

public class ApiHandler {
    private final String key;
    public final static String startUrl = "https://osu.ppy.sh/api/";
    private final ArrayBlockingQueue<Runnable> blockingQueue;
    private final ExecutorService executorService;

    public ApiHandler(OsuSettings osuS) {
        this.key = osuS.getKey();

        this.blockingQueue = osuS.getBlockingQueue();
        this.executorService = osuS.getExecutorService();
    }

    public Future<BeatmapImpl> retrieveBeatmap(BeatmapRequestBuilder beatmapRequestBuilder) {
        return null;
    }
//
//    public Future<Set<BeatmapImpl>> retrieveBeatmaps(BeatmapRequestBuilder beatmapRequestBuilder) {
//        Callable<Set<BeatmapImpl>> callable = () -> {
//            Set<BeatmapImpl> beatmaps = new HashSet<>();
//
//            beatmapRequestBuilder.setKey(key);
//            JsonArray json = getJsonArray(beatmapRequestBuilder.toUrl());
//
//            json.forEach(jsonElement -> beatmaps.add(BeatmapImpl.getBeatmapFromJson(jsonElement.getAsJsonObject())));
//
//            return beatmaps;
//        };
//
//        return new FutureImpl<>(callable, executorService);
//    }
//
//    public FutureImpl<User> retrieveUser(UserRequestBuilder userRequestBuilder) {
//        Callable<User> callable = ()  -> {
//
//            userRequestBuilder.setKey(key);
//            System.out.println(userRequestBuilder.toUrl());
//
//            return new User(getJsonArray(userRequestBuilder.toUrl()).get(0).getAsJsonObject());
//        };
//
//        return new FutureImpl<>(callable, executorService);
//    }
//
//    public Future<Set<Score>> retrieveScores(ScoresRequestBuilder scoresRequestBuilder) {
//        Callable<Set<Score>> callable = () -> {
//            Set<Score> scores = new HashSet<>();
//
//            scoresRequestBuilder.setKey(key);
//
//            JsonArray json = getJsonArray(scoresRequestBuilder.toUrl());
//
//            json.forEach(jsonElement -> scores.add(new Score(jsonElement.getAsJsonObject())));
//
//            return scores;
//        };
//
//        return new FutureImpl<>(callable, executorService);
//    }
//
//    public Future<Set<BestPerformance>> retrieveBestPerformance(BestPerformanceRequestBuilder bestPerformanceRequestBuilder) {
//        Callable<Set<BestPerformance>> callable = () -> {
//            Set<BestPerformance> bestPerformances = new HashSet<>();
//
//            bestPerformanceRequestBuilder.setKey(key);
//
//            JsonArray json = getJsonArray(bestPerformanceRequestBuilder.toUrl());
//
//            System.out.println(json);
//
//            json.forEach(jsonElement -> bestPerformances.add(new BestPerformance(jsonElement.getAsJsonObject())));
//
//            return bestPerformances;
//        };
//
//        return new FutureImpl<>(callable, executorService);
//    }
//
//    public FutureImpl<Match> retrieveMatchInfo(MatchRequestBuilder matchRequestBuilder) {
//        Callable<Match> callable = ()  -> {
//
//            matchRequestBuilder.setKey(key);
//            System.out.println(matchRequestBuilder.toUrl());
//
//            return new Match(getJsonObject(matchRequestBuilder.toUrl()));
//        };
//
//        return new FutureImpl<>(callable, executorService);
//    }
//
//    public FutureImpl<Replay> retrieveReplay(ReplayRequestBuilder replayRequestBuilder) {
//        Callable<Replay> callable = ()  -> {
//
//            replayRequestBuilder.setKey(key);
//
//            return new Replay(getJsonObject(replayRequestBuilder.toUrl()));
//        };
//
//        return new FutureImpl<>(callable, executorService);
//    }
//
//    protected static JsonObject getJsonObject(String urlString) throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(urlString))
//                .GET()
//                .build();
//
//        String jsonString = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
//        return new Gson().fromJson(jsonString, JsonObject.class);
//    }
//
//    protected static JsonArray getJsonArray(String urlString) throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(urlString))
//                .GET()
//                .build();
//
//        String jsonString = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
//        return new Gson().fromJson(jsonString, JsonArray.class);
//    }
//
//    protected static JsonElement getJsonElement(String urlString) throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(urlString))
//                .GET()
//                .build();
//
//        String jsonString = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
//        return new Gson().fromJson(jsonString, JsonElement.class);
//    }
}

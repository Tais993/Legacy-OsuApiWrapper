package osu;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import v1.entities.beatmap.Approved;
import v1.entities.beatmap.Beatmap;
import v1.entities.beatmap.BeatmapImpl;
import v1.entities.global.Length;
import v1.entities.global.Mode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class EntityBuilder {

    protected EntityBuilder() {}
    public JsonObject getJsonFromUrlString(String urlString) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        String jsonString = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return new Gson().fromJson(jsonString, JsonObject.class);
    }

    public <R> Function<JsonObject, R> getConverterFromClass(Class<R> clazz) {
        if (clazz == Beatmap.class) {
            return json -> (R) createBeatmapFromJson(json);
        }

        throw new IllegalArgumentException("Class has to be an osu entity!");
    }

    public Beatmap createBeatmapFromJson(JsonObject json) {
        long beatmapSetId = json.get("beatmapset_id").getAsLong();
        long beatmapId = json.get("beatmap_id").getAsLong();

        Approved approved = Approved.fromId(json.get("approved").getAsInt());

        Length length = new Length(json.get("total_length").getAsInt());
        int hitLength = json.get("hit_length").getAsInt();

        String version = json.get("version").getAsString();

        String fileMd5 = json.get("file_md5").getAsString();

        double diffSize = json.get("diff_size").getAsDouble();
        double diffOverall = json.get("diff_overall").getAsDouble();
        double diffApproach = json.get("diff_approach").getAsDouble();
        double diffDrain = json.get("diff_drain").getAsDouble();

        Mode mode = Mode.getById(json.get("mode").getAsInt());

        int countNormal = json.get("count_normal").getAsInt();
        int countSlider = json.get("count_slider").getAsInt();
        int countSpinner = json.get("count_spinner").getAsInt();

        LocalDateTime submitDate = getDateFromString(json.get("submit_date").getAsString());
        JsonElement approvedDateJson = json.get("approved_date");

        LocalDateTime approvedDate = approvedDateJson.isJsonNull() ? null : getDateFromString(approvedDateJson.getAsString());

        LocalDateTime lastUpdateDate = getDateFromString(json.get("last_update").getAsString());

        String artist = json.get("artist").getAsString();
        String title = json.get("title").getAsString();

        String creatorName = json.get("creator").getAsString();
        long creatorId = json.get("creator_id").getAsLong();

        double bpm = json.get("bpm").getAsDouble();

        String source = json.get("source").getAsString();
        List<String> tags = new ArrayList<>(Arrays.asList(json.get("tags").getAsString().split(" ")));
        Beatmap.Genre genre = Beatmap.Genre.getById(json.get("genre_id").getAsInt());
        Beatmap.Language language = Beatmap.Language.getById(json.get("language_id").getAsInt());

        int favouriteCount = json.get("favourite_count").getAsInt();
        double rating = json.get("rating").getAsDouble();

        boolean storyboard = json.get("storyboard").getAsBoolean();
        boolean video = json.get("video").getAsBoolean();
        boolean downloadUnavailable = json.get("download_unavailable").getAsBoolean();
        boolean audioUnavailable = json.get("audio_unavailable").getAsBoolean();

        int playCount = json.get("playcount").getAsInt();
        int passCount = json.get("passcount").getAsInt();

        List<String> packs;
        if (json.get("packs").isJsonNull()) {
            packs = List.of();
        } else {
            packs = new ArrayList<>(Arrays.asList(json.get("packs").getAsString().split(",")));
        }

        int maxCombo = json.get("max_combo").getAsInt();

        double diffAim = json.get("diff_aim").getAsDouble();
        double diffSpeed = json.get("diff_speed").getAsDouble();

        double difficultyRating = json.get("difficultyrating").getAsDouble();
        return new BeatmapImpl(beatmapSetId, beatmapId, approved,
                length, hitLength, version,
                fileMd5, diffSize, diffOverall, diffApproach,
                diffDrain, mode, countNormal, countSlider,
                countSpinner, submitDate, approvedDate,
                lastUpdateDate, artist, title,
                creatorName, creatorId, bpm, source,
                tags, genre,
                language, favouriteCount, rating, storyboard,
                video, downloadUnavailable, audioUnavailable, playCount,
                passCount, packs, maxCombo, diffAim,
                diffSpeed, difficultyRating);
    }

    private static LocalDateTime getDateFromString(String timeAsString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(timeAsString, formatter);
    }
}

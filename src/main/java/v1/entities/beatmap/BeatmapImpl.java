package v1.entities.beatmap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import v1.entities.OsuEntity;
import v1.entities.global.Length;
import v1.entities.global.Mode;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record BeatmapImpl(long beatmapSetId, long beatmapId, Approved approved,
                          Length length, int hitLength, String version,
                          String fileMd5, double diffSize, double diffOverall, double diffApproach,
                          double diffDrain, Mode mode, int countNormal, int countSlider,
                          int countSpinner, LocalDateTime submitDate, LocalDateTime approvedDate,
                          LocalDateTime lastUpdateDate, String artist, String title,
                          String creatorName, long creatorId, double bpm, String source,
                          List<String> tags, Genre genre,
                          Language language, int favouriteCount, double rating, boolean storyboard,
                          boolean video, boolean downloadUnavailable, boolean audioUnavailable, int playCount,
                          int passCount, List<String> packs, int maxCombo, double diffAim,
                          double diffSpeed, double difficultyRating) implements Beatmap {

    public static BeatmapImpl getBeatmapFromJson(JsonObject json) {
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
        Genre genre = Genre.getById(json.get("genre_id").getAsInt());
        Language language = Language.getById(json.get("language_id").getAsInt());

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

    @NotNull
    public String beatmapCoverImageUrl() {
        return "https://assets.ppy.sh/beatmaps/" + beatmapId + "/covers/cover.jpg";
    }

    @NotNull
    public InputStream retrieveBeatmapCoverImage() {
        try {
            URL url = new URL("https://assets.ppy.sh/beatmaps/" + beatmapId + "/covers/cover.jpg");
            return url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    @NotNull
    public String beatmapCoverThumbnailUrl() {
        return "https://b.ppy.sh/thumb/" + beatmapId + "l.jpg";
    }

    @NotNull
    public InputStream retrieveBeatmapCoverThumbnail() {
        try {
            URL url = new URL("https://b.ppy.sh/thumb/" + beatmapId + "l.jpg");
            return url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    @NotNull
    public String beatmapLink() {
        return "https://osu.ppy.sh/beatmapsets/" + beatmapSetId + "#" + mode.getTitle().toLowerCase() + "/" + beatmapId;
    }

    @Override
    public String toString() {
        return "BeatmapImpl{" +
                "beatmapSetId=" + beatmapSetId +
                ", beatmapId=" + beatmapId +
                ", approved=" + approved +
                ", length=" + length +
                ", hitLength=" + hitLength +
                ", version='" + version + '\'' +
                ", fileMd5='" + fileMd5 + '\'' +
                ", diffSize=" + diffSize +
                ", diffOverall=" + diffOverall +
                ", diffApproach=" + diffApproach +
                ", diffDrain=" + diffDrain +
                ", mode=" + mode +
                ", countNormal=" + countNormal +
                ", countSlider=" + countSlider +
                ", countSpinner=" + countSpinner +
                ", submitDate=" + submitDate +
                ", approvedDate=" + approvedDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", creatorId=" + creatorId +
                ", bpm=" + bpm +
                ", source='" + source + '\'' +
                ", tags=" + tags +
                ", genre=" + genre +
                ", language=" + language +
                ", favouriteCount=" + favouriteCount +
                ", rating=" + rating +
                ", storyboard=" + storyboard +
                ", video=" + video +
                ", downloadUnavailable=" + downloadUnavailable +
                ", audioUnavailable=" + audioUnavailable +
                ", playCount=" + playCount +
                ", passCount=" + passCount +
                ", packs=" + packs +
                ", maxCombo=" + maxCombo +
                ", diffAim=" + diffAim +
                ", diffSpeed=" + diffSpeed +
                ", difficultyRating=" + difficultyRating +
                '}';
    }
}

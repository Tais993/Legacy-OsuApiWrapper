package v1.entities.beatmap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import v1.entities.OsuEntity;
import v1.entities.global.Length;
import v1.entities.global.Mode;
import v1.entities.user.User;

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

    @Override
    public User retrieveCreator() {
        return null;
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

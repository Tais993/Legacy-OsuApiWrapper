package v1.entities.beatmap;

import org.jetbrains.annotations.NotNull;
import v1.entities.global.Length;
import v1.entities.global.Mode;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface Beatmap {

    long beatmapSetId();

    long beatmapId();

    @NotNull
    Approved approved();

    @NotNull
    Length length();

    int hitLength();

    @NotNull
    String version();

    @NotNull
    String fileMd5();

    double diffSize();

    double diffOverall();

    double diffApproach();

    double diffDrain();

    @NotNull
    Mode mode();

    int countNormal();

    int countSlider();

    int countSpinner();

    @NotNull
    LocalDateTime submitDate();

    @NotNull
    LocalDateTime approvedDate();

    @NotNull
    LocalDateTime lastUpdateDate();

    @NotNull
    String artist();

    @NotNull
    String title();

    @NotNull
    String creatorName();

    long creatorId();

    double bpm();

    @NotNull
    String source();

    @NotNull
    List<String> tags();

    @NotNull
    Genre genre();

    @NotNull
    Language language();

    int favouriteCount();

    double rating();

    boolean storyboard();

    boolean video();

    boolean downloadUnavailable();

    boolean audioUnavailable();

    int playCount();

    int passCount();

    @NotNull
    List<String> packs();

    int maxCombo();

    double diffAim();

    double diffSpeed();

    double difficultyRating();

    @NotNull
    String beatmapCoverImageUrl();

    @NotNull
    InputStream retrieveBeatmapCoverImage();

    @NotNull
    String beatmapCoverThumbnailUrl();

    @NotNull
    InputStream retrieveBeatmapCoverThumbnail();

    @NotNull
    String beatmapLink();

    enum Genre {
        ANY(0, "any"),
        UNSPECIFIED(1, "unspecified"),
        VIDEO_GAME(2, "video game"),
        ANIME(3, "anime"),
        ROCK(4, "rock"),
        POP(5, "pop"),
        OTHER(6, "other"),
        NOVELTY(7, "novelty"),
        HIPHOP(9, "hiphop"),
        ELECTRONIC(10, "electronic"),
        METAL(11, "metal"),
        CLASSICAL(12, "classical"),
        FOLK(13, "folk"),
        JAZZ(14, "jazz");

        private final int id;
        private final String title;

        Genre(int id, String title) {
            this.id = id;
            this.title = title;
        }
        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public static Genre getById(int id) {
            for (Genre genre : Genre.values()) {
                if (genre.getId() == id) {
                    return genre;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return id + "";
        }
    }

    enum Language {
        ANY(0, "any"),
        UNSPECIFIED(1, "unspecified"),
        ENGLISH(2, "english"),
        JAPANESE(3, "japanese"),
        CHINESE(4, "chinese"),
        INSTRUMENTAL(5, "instrumental"),
        KOREAN(6, "korean"),
        FRENCH(7, "french"),
        GERMAN(8, "german"),
        SWEDISH(9, "swedish"),
        SPANISH(10, "spanish"),
        ITALIAN(11, "italian"),
        RUSSIAN(12, "russian"),
        POLISH(13, "polish"),
        OTHER(14, "other");

        private final int id;
        private final String title;

        Language(int id, String title) {
            this.id = id;
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public static Language getById(int id) {
            for (Language language : Language.values()) {
                if (language.getId() == id) {
                    return language;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return id + "";
        }
    }
}

package v1.entities.global;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public enum Mod {
    MIRROR(1073741824, "mirror", "M"),
    SCOREV2(536870912, "score v2", "v2"),

    KEY2(268435456, "2 keys", "2K", "k", "fm"),
    KEY3(134217728, "3 keys", "3K", "k", "fm"),
    KEY1(67108864, "1 key", "1K", "k", "fm"),
    KEYCOOP(33554432, "key coop", "KC", "k", "fm"),
    KEY9(16777216, "9 keys", "9K", "k", "fm"),

    TARGET(8388608, "target", "T"),
    CINEMA(4194304, "cinema", "C"),
    RANDOM(2097152, "random", "R"),
    FADEIN(1048576, "fade in", "FI", "fm", "sim"),

    KEY8(524288, "8 keys", "8K", "k", "fm"),
    KEY7(262144, "7 keys", "7K", "k", "fm"),
    KEY6(131072, "6 keys", "6K", "k", "fm"),
    KEY5(65536, "5 keys", "5K", "k", "fm"),
    KEY4(32768, "4 keys", "4K", "k", "fm"),

    PERFECT(16384, "perfect", "PF"), // Only set along with SuddenDeath. i.e: PF only gives 16416
    RELAX2(8192, "autopilot", "AP", "fm"),    // Autopilot
    SPUNOUT(4096, "spun out", "SO", "fm"),
    AUTOPLAY(2048, "autoplay", "AP"),
    FLASHLIGHT(1024, "flashlight", "FL", "fm", "sim"),
    NIGHTCORE(512, "night core", "NC"), // Only set along with DoubleTime. i.e: NC only gives 576
    HALFTIME(256, "half time", "HT"),
    RELAX(128, "relax", "R", "fm"),
    DOUBLETIME(64, "double time", "DT", "sim"),

    SUDDENDEATH(32, "sudden death", "SD", "fm"),

    HARDROCK(16,"hardrock", "HR", "fm", "sim"),
    HIDDEN(8, "hidden", "HD", "fm", "sim"),
    TOUCHDEVICE(4, "touch devices", "TD"),

    EASY(2, "easy", "EZ", "fm"),
    NOFAIL(1, "no fail", "NF", "fm"),
    NONE(0, "none", "");



    private final int bitwise;
    private final String title;
    private final String abbreviation;
    private boolean isKeyMod = false;
    private boolean allowsFreeMod = false;
    private boolean isScoreIncreaseMod = false;

    Mod(int bitwise, String title, String abbreviation) {
        this.bitwise = bitwise;
        this.title = title;
        this.abbreviation = abbreviation;
    }

    Mod(int id, String title, String abbreviation, String... types) {
        this.bitwise = id;
        this.title = title;
        this.abbreviation = abbreviation;

        for (String type : types) {
            switch (type.toLowerCase()) {
                case "key", "k" -> isKeyMod = true;
                case "freemod", "fm" -> allowsFreeMod = true;
                case "scoreincreasemods", "sim" -> isScoreIncreaseMod = true;
            }
        }
    }

    public int getBitwise() {
        return bitwise;
    }

    public String getTitle() {
        return title;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public boolean allowsFreeMod() {
        return allowsFreeMod;
    }

    public boolean isKeyMod() {
        return isKeyMod;
    }

    public boolean increasesScore() {
        return isScoreIncreaseMod;
    }

    public static List<Mod> getByBitwise(int bitwise) {
        List<Mod> mods = new ArrayList<>();

        FindBitwise:
        while (bitwise != 0) {

            for (Mod mod : Mod.values()) {
                if (mod.getBitwise() > bitwise) {
                    mods.add(mod);
                    bitwise -= mod.getBitwise();
                    continue FindBitwise;
                }
            }
        }

        return mods;
    }

    public static int getBitwiseFromMods(List<Mod> mods) {
        return mods.stream().mapToInt(Mod::getBitwise).sum();
    }

    public static int getBitwiseFromMods(Mod... mods) {
        return Stream.of(mods).mapToInt(Mod::getBitwise).sum();
    }

    @Override
    public String toString() {
        return "\n title='" + title + "' \n";
    }

    public static void main(String[] args) {
        for (Mod value : Mod.values()) {
            System.out.println(value);
        }
    }
}

package me.xtrm.mcversioning;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class SementicVersion implements Comparable<SementicVersion> {

    private final int major;
    private final int minor;
    private final int patch;

    private final String classifier;

    private final int comparableInt;

    private SementicVersion(String version, String classifier) {
        String[] tokens = version.split(Pattern.quote("."));
        if (tokens.length > 3 || tokens.length < 2) {
            throw new UnsupportedOperationException("Invalid version: \"" + version + "\"");
        }

        this.major = Integer.parseInt(tokens[0]);
        this.minor = Integer.parseInt(tokens[1]);
        if (tokens.length == 3) {
            this.patch = Integer.parseInt(tokens[2]);
        } else {
            this.patch = 0;
        }

        this.classifier = classifier;

        // this is gonna get ugly real quick
        String comparableString = String.format(
                "%s%s%s",
                major,
                (minor < 10) ? ("0" + minor) : minor,
                (patch < 10) ? ("0" + patch) : patch
        );
        this.comparableInt = Integer.parseInt(comparableString);
    }

    public static SementicVersion from(String versionString) {
        String version = versionString.toLowerCase(Locale.ROOT);
        String classifier = null;

        int index = version.indexOf('-');
        if (index != -1) {
            classifier = version.substring(index + 1);
            version = version.substring(0, index);
        }

        // Minecraft checks
        if (version.contains("w")) {
            throw new UnsupportedOperationException("Snapshot versions aren't supported.");
        }
        if (version.contains("a")
                || version.contains("b")
                || version.contains("c")
                || version.contains("inf")
                || version.contains("rd")
                || version.contains("ex")) {
            throw new UnsupportedOperationException("Alpha/Beta/Indev versions aren't supported.");
        }

        return new SementicVersion(version, classifier);
    }

    @Override
    public int compareTo(SementicVersion that) {
        if(that == null) {
            throw new IllegalArgumentException("Cannot compare to null.");
        }

        return Integer.compare(this.comparableInt, that.comparableInt);
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + patch + (this.classifier != null ? "-" + classifier : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SementicVersion mcVersion = (SementicVersion) o;
        return major == mcVersion.major && minor == mcVersion.minor && patch == mcVersion.patch;
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, minor, patch);
    }
}

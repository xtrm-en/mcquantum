package me.xtrm.mcversioning.version;

import com.github.zafarkhaja.semver.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.xtrm.mcversioning.VersionCache;
import me.xtrm.mcversioning.manifest.VersionManifestMetadata;
import me.xtrm.mcversioning.manifest.VersionsManifestJson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class MCVersion implements Comparable<MCVersion> {

    private static final Pattern simpleVersionPattern = Pattern.compile("^([0-9]+\\.)?([0-9]+\\.)?(\\*|[0-9]+)$");
    private static final Pattern snapshotVersionPattern = Pattern.compile("^([1-9][0-9]w)([0-9][0-9]([a-z]+))$");

    private final String versionName;
    private final VersionType versionType;
    private final Version underlyingRelease;
    private Version typeVersion = Version.forIntegers(0, 0, 0);

    public static MCVersion fromString(String versionName) {
        String versionStr = versionName.trim();

        // release
        Matcher verMatcher = simpleVersionPattern.matcher(versionStr);
        if (verMatcher.matches()) {
            if (versionStr.split(Pattern.quote(".")).length < 3) {
                versionStr += ".0";
            }
            return new MCVersion(versionStr, VersionType.RELEASE, Version.valueOf(versionStr));
        }

        // snapshot
        Matcher snapMatcher = snapshotVersionPattern.matcher(versionStr);
        if (snapMatcher.matches()) {
            VersionsManifestJson manifest = VersionCache.INSTANCE.getVersionsManifest();
            VersionManifestMetadata meta = manifest.findManifest(versionStr);

            if (meta == null) {
                throw new IllegalArgumentException("Unknown snapshot version.");
            }

            boolean previous = false;
            VersionManifestMetadata relMeta = manifest.findNextRelease(meta);
            if (relMeta == null) {
                previous = true;
                relMeta = manifest.findLastRelease(meta);
                if (relMeta == null) {
                    throw new IllegalArgumentException("Unknown snapshot version (no surrounding version).");
                }
            }

            String releaseVersion = relMeta.getId();
            if (releaseVersion.split(Pattern.quote(".")).length < 3) {
                releaseVersion += ".0";
            }

            return new MCVersion(versionStr, VersionType.SNAPSHOT, Version.valueOf(releaseVersion), Version.valueOf("0.0.0+" + (previous ? "p." : "") + meta.getId()));
        }

        // beta

        // alpha

        return null;
    }

    @Override
    public String toString() {
        return (underlyingRelease != null ? underlyingRelease.toString() : "")
                + (this.versionType != VersionType.RELEASE ? "+" + this.versionType.getId() + "." + typeVersion.getBuildMetadata() : "");
    }

    @Override
    public int compareTo(MCVersion that) {
        if (that == null) return 1;

        VersionType versionType = that.versionType;
        if (versionType != this.getVersionType()) {
            // only if we're comparing something to alpha/beta
            // should we decide to return the comparaison
            // of the version type, since snapshots, releases and
            // experiments mangle together
            if (versionType == VersionType.OLD_ALPHA || this.versionType == VersionType.OLD_ALPHA || versionType == VersionType.OLD_BETA || this.versionType == VersionType.OLD_BETA) {
                return versionType.compareTo(this.getVersionType());
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        MCVersion testVer1 = MCVersion.fromString("1.7.10");
        MCVersion testVer2 = MCVersion.fromString("1.8.9");
        MCVersion testVer3 = MCVersion.fromString("1.8");
        MCVersion testVer4 = MCVersion.fromString("b1.8.1");
        MCVersion testVer5 = MCVersion.fromString("15w31c");

        Stream.of(testVer1, testVer2, testVer3, testVer5).sorted().forEach(System.out::println);
    }
}

package me.xtrm.mcversioning.manifest;

import lombok.Data;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @see <a href="https://launchermeta.mojang.com/mc/game/version_manifest.json">version_manifest.json</a>
 */
public @Data
class VersionsManifestJson {
    private final LatestVersions latest;
    private final List<VersionManifestMetadata> versions;

    public VersionManifestMetadata findManifest(String id) {
        return this.versions.stream()
                .filter(v -> v.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public VersionManifestMetadata findNextRelease(VersionManifestMetadata metadata) {
        int index = this.versions.indexOf(metadata);
        for (int i = index; i < this.versions.size(); i++) {
            VersionManifestMetadata meta = this.versions.get(i);
            if (meta.getType().equalsIgnoreCase("release")) {
                return meta;
            }
        }
        return null;
    }

    public VersionManifestMetadata findLastRelease(VersionManifestMetadata metadata) {
        int index = this.versions.indexOf(metadata);
        for (int i = index; i >= 0; i--) {
            VersionManifestMetadata meta = this.versions.get(i);
            if (meta.getType().equalsIgnoreCase("release")) {
                return meta;
            }
        }
        return null;
    }

    public void sort() {
        this.versions.sort((o1, o2) -> {
            Instant i1 = OffsetDateTime.parse(o1.getReleaseTime()).toInstant();
            Instant i2 = OffsetDateTime.parse(o2.getReleaseTime()).toInstant();
            return i1.compareTo(i2);
        });
    }
}


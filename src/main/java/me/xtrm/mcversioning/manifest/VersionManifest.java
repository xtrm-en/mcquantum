package me.xtrm.mcversioning.manifest;

import lombok.Data;

/**
 * @see <a href="https://launchermeta.mojang.com/mc/game/version_manifest.json">version_manifest.json</a>
 */
public @Data class VersionManifest {
    private final LatestVersions latest;
    private final VersionMetadata[] versions;
}

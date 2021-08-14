package me.xtrm.mcversioning.manifest;

import lombok.Data;

public @Data class LatestVersions {
    private final String release;
    private final String snapshot;
}

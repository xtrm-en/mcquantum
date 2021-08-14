package me.xtrm.mcversioning.manifest;

import lombok.Data;

public @Data class VersionMetadata {
    private final String id;
    private final String type;
    private final String url;
    private final String time;
    private final String releaseTime;
}

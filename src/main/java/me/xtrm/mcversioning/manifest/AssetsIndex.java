package me.xtrm.mcversioning.manifest;

import lombok.Data;

public @Data class AssetsIndex {
    private final String id;
    private final String sha1;
    private final long size;
    private final long totalSize;
    private final String url;
}

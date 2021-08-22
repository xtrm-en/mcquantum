package me.xtrm.mcversioning.manifest;

import lombok.Data;

public @Data class VersionJson {
    private final AssetsIndex assetsIndex;
    private final String assets;
    private final String id;

}

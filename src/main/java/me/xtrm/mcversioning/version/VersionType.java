package me.xtrm.mcversioning.version;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VersionType {
    OLD_ALPHA("Old Alpha", "old_alpha"),
    OLD_BETA("Old Beta", "old_beta"),
    EXPERIMENTS("Experiments", "experimental"),
    SNAPSHOT("Snapshot", "snapshot"),
    RELEASE("Release", "rel");

    private final String name;
    private final String id;
}

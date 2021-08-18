package me.xtrm.mcversioning.environment.platform.mod;

import lombok.Data;
import me.xtrm.mcversioning.environment.platform.IPlatformMod;

import java.io.File;
import java.util.List;

public @Data class PlatformModImpl implements IPlatformMod {

    private final String name;
    private final String identifier;
    private final String version;
    private final List<String> authors;
    private final File source;

}

package me.xtrm.mcversioning.environment;

import lombok.Data;
import me.xtrm.mcversioning.SementicVersion;
import me.xtrm.mcversioning.environment.platform.IPlatform;

public @Data class MCEnvironment {
    private final SementicVersion minecraftVersion;
    private final IPlatform runtimePlatform;
}

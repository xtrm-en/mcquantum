package me.xtrm.mcversioning.environment;

import lombok.Data;
import me.xtrm.mcversioning.environment.platform.IPlatform;
import me.xtrm.mcversioning.version.MCVersion;

public @Data class MCEnvironment {
    private final MCVersion minecraftVersion;
    private final IPlatform runtimePlatform;
}

package me.xtrm.mcversioning;

import lombok.Data;
import me.xtrm.mcversioning.platform.IPlatform;

public @Data class MCEnvironment {
    private final MCVersion version;
    private final IPlatform platform;
}

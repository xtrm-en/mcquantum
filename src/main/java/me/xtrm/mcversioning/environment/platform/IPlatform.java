package me.xtrm.mcversioning.environment.platform;

import me.xtrm.mcversioning.environment.platform.impl.FabricPlatform;
import me.xtrm.mcversioning.environment.platform.impl.LegacyLauncherPlatform;
import me.xtrm.mcversioning.environment.platform.impl.ModLauncherPlatform;

import java.util.List;

/**
 * Interface describing a mod-loading platform.
 *
 * @author xTrM_
 */
public interface IPlatform {

    String getPlatformName();

    String getPlatformVersion();

    List<IPlatformMod> getLoadedMods();

    ClassLoader getPlatformClassLoader();

    default boolean isFabric() {
        return this instanceof FabricPlatform;
    }

    default boolean isForge() {
        return this instanceof IForgeablePlatform && ((IForgeablePlatform) this).isForge();
    }

    default boolean isLaunchWrapper() {
        return this instanceof LegacyLauncherPlatform;
    }

    default boolean isModLauncher() {
        return this instanceof ModLauncherPlatform;
    }
}

package me.xtrm.mcversioning.environment.platform;

public interface IForgeablePlatform {
    boolean isForge();

    boolean isLegacyForge();

    String getForgeVersion();
}

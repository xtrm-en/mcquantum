package me.xtrm.mcversioning.environment.platform.impl;

import me.xtrm.mcversioning.environment.platform.IForgeablePlatform;
import me.xtrm.mcversioning.environment.platform.IPlatform;
import me.xtrm.mcversioning.environment.platform.IPlatformMod;
import me.xtrm.mcversioning.environment.platform.mod.PlatformModImpl;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LegacyLauncherPlatform implements IPlatform, IForgeablePlatform {

    @Override
    public String getPlatformName() {
        return "LaunchWrapper" + (isForge() ? " (Forge)" : "");
    }

    @Override
    public String getPlatformVersion() {
        return "1.12"; // no way of getting the real version
    }

    @Override
    public List<IPlatformMod> getLoadedMods() {
        return isForge() ? getForgeMods() : getTweaks();
    }

    public List<IPlatformMod> getForgeMods() {
        return Collections.emptyList();
    }

    public List<IPlatformMod> getTweaks() {
        List<?> tweakers = (List<?>) Launch.blackboard.getOrDefault("Tweaks", new ArrayList<>());
        return tweakers.stream()
                .map(t -> {
                    Class<?> clazz = t.getClass();
                    Package pkg = clazz.getPackage();

                    URL location = clazz.getProtectionDomain().getCodeSource().getLocation();
                    File source = null;
                    if (location != null) {
                        source = new File(location.getFile());
                    }

                    return new PlatformModImpl(
                            clazz.getName(),
                            clazz.getName(),
                            pkg == null
                                    ? null
                                    : pkg.getImplementationVersion(),
                            pkg == null
                                    ? Collections.emptyList()
                                    : Collections.singletonList(pkg.getImplementationVendor()),
                            source
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public ClassLoader getPlatformClassLoader() {
        return Launch.classLoader;
    }

    @Override
    public boolean isForge() {
        return hasTweak("cpw.mods.fml") || hasTweak("net.minecraftforge.fml");
    }

    @Override
    public boolean isLegacyForge() {
        return hasTweak("cpw.mods.fml");
    }

    @Override
    public String getForgeVersion() {
        Object[] data;
        if (isLegacyForge()) {
            data = cpw.mods.fml.relauncher.FMLInjectionData.data();
        } else {
            data = net.minecraftforge.fml.relauncher.FMLInjectionData.data();
        }
        return data[0] + "." + data[1] + "." + data[2] + "." + data[3];
    }

    private boolean hasTweak(String className) {
        List<?> tweakers = (List<?>) Launch.blackboard.getOrDefault("Tweaks", new ArrayList<>());
        for (Object o : tweakers) {
            Class<?> clazz = o.getClass();
            String name = clazz.getName();

            if (name.startsWith(className)) {
                return true;
            }
        }
        return false;
    }
}

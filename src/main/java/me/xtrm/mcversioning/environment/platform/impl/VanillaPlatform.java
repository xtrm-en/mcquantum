package me.xtrm.mcversioning.environment.platform.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.xtrm.mcversioning.environment.platform.IPlatform;
import me.xtrm.mcversioning.environment.platform.IPlatformMod;
import net.minecraft.client.main.Main;

import java.util.Collections;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class VanillaPlatform implements IPlatform {

    private final String platformVersion;

    @Override
    public String getPlatformName() {
        return "Vanilla";
    }

    @Override
    public List<IPlatformMod> getLoadedMods() {
        return Collections.emptyList();
    }

    @Override
    public ClassLoader getPlatformClassLoader() {
        return Main.class.getClassLoader();
    }
}

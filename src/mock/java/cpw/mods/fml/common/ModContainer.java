package cpw.mods.fml.common;

import java.io.File;

public interface ModContainer {

    String getModId();

    String getName();

    String getVersion();

    File getSource();

    ModMetadata getMetadata();

}

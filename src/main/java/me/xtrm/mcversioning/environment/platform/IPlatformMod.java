package me.xtrm.mcversioning.environment.platform;

import java.io.File;
import java.util.List;

public interface IPlatformMod {

    String getName();

    String getIdentifier();

    String getVersion();

    List<String> getAuthors();

    File getSource();

}

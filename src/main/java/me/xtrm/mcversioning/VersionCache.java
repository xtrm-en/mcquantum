package me.xtrm.mcversioning;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.xtrm.mcversioning.manifest.VersionsManifestJson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public enum VersionCache {
    INSTANCE;

    private static final String VERSION_MANIFEST_JSON = "https://launchermeta.mojang.com/mc/game/version_manifest.json";
    private static final Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private VersionsManifestJson versionsManifestJson;

    VersionCache() {

    }

    public VersionsManifestJson getVersionsManifest() {
        if (versionsManifestJson == null) {
            try {
                fetchManifest();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return versionsManifestJson;
    }

    private void fetchManifest() throws IOException {
        URL url = new URL(VERSION_MANIFEST_JSON);
        InputStream inputStream = url.openStream();
        InputStreamReader isr = new InputStreamReader(inputStream);
        this.versionsManifestJson = GSON.fromJson(isr, VersionsManifestJson.class);
        this.versionsManifestJson.sort();
        inputStream.close();

    }
}

package me.xtrm.mcversioning.manifest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public @Data
class VersionManifestMetadata {

    private static final Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    private final String id;
    private final String type;
    private final String url;
    private final String time;
    private final String releaseTime;

    private transient VersionJson cachedJson;

    public VersionJson getVersionJson() {
        if (cachedJson == null) {
            try {
                URL url = new URL(getUrl());
                InputStreamReader isr = new InputStreamReader(url.openStream());
                this.cachedJson = GSON.fromJson(isr, VersionJson.class);
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cachedJson;
    }
}

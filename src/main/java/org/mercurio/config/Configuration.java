package org.mercurio.config;


import java.io.*;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Configuration implements Serializable {
    private static Configuration instance;
    private String language = "en";
    private ThemeMode themeMode = ThemeMode.SYSTEM_DEFAULT;
    private transient Path dataPath;
    private String defaultMaxRam = "4G";
    private String defaultMinRam = "512M";

    private Configuration() {}

    public static Configuration getInstance() {
        if (instance == null) instance = new Configuration();
        return instance;
    }

    public static void instanceConfig(Path dataPath) {
        Path filePath = dataPath.resolve("config.json");
        File file = filePath.toFile();
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    System.out.println("Created configuration file");
                }
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                try (FileWriter writer = new FileWriter(file)) {
                    instance = new Configuration();
                    gson.toJson(instance, writer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Configuration.loadConfig(dataPath);
        }
    }

    public void saveConfig() throws IOException {
        Path filePath = this.dataPath.resolve("config.json");
        File file = filePath.toFile();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(this, writer);
        }
    }

    public static void loadConfig(Path dataPath) {
        Path filePath = dataPath.resolve("config.json");
        File file = filePath.toFile();

        try (Reader reader = new FileReader(file)) {
            Gson gson = new Gson();
            instance = gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            instance = new Configuration();
        }
    }


    public String getLanguage() {
        return this.language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public ThemeMode getThemeMode() {
        return this.themeMode;
    }
    public void setThemeMode(ThemeMode themeMode) {
        this.themeMode = themeMode;
    }

    public Path getDataPath() {
        return this.dataPath;
    }
    public void setDataPath(Path dataPath) {
        this.dataPath = dataPath;
    }

    public String getDefaultMaxRam() {
        return defaultMaxRam;
    }

    public void setDefaultMaxRam(String defaultMaxRam) {
        this.defaultMaxRam = defaultMaxRam;
    }

    public String getDefaultMinRam() {
        return defaultMinRam;
    }

    public void setDefaultMinRam(String defaultMinRam) {
        this.defaultMinRam = defaultMinRam;
    }
}

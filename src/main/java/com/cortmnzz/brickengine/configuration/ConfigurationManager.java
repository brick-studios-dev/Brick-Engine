package com.cortmnzz.brickengine.configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cortmnzz.brickengine.BrickEngine;
import org.bukkit.configuration.InvalidConfigurationException;

public class ConfigurationManager {

    private static final Map<String, Configuration> configurationMap = new HashMap<>();

    public static Configuration getConfig(String name) {
        Configuration configuration = configurationMap.get(name);

        if (configuration != null) {
            return configuration;
        }

        File configurationFile = new File(BrickEngine.getInstance().getDataFolder(), name);
        if (!configurationFile.exists()) {
            configurationFile.getParentFile().mkdirs();
            BrickEngine.getInstance().saveResource(name, false);
        }

        configuration = new Configuration(configurationFile);
        try {
            configuration.load();
            configurationMap.put(name, configuration);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }

        return configuration;
    }
    public static List<Configuration> configurationList() {
        return new ArrayList<>(configurationMap.values());
    }
}
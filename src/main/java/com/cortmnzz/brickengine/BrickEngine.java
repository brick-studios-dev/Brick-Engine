package com.cortmnzz.brickengine;

import com.cortmnzz.brickengine.configuration.Configuration;
import com.cortmnzz.brickengine.configuration.ConfigurationManager;
import com.cortmnzz.brickengine.sequence.SequenceLoader;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class BrickEngine extends JavaPlugin {

    @Getter private static BrickEngine instance;

    @Getter private final Configuration sequencesConfiguration = ConfigurationManager.getConfig("sequences.yml");

    @Getter private SequenceLoader sequenceLoader;

    @Override
    public void onEnable() {
        instance = this;

        this.sequenceLoader = new SequenceLoader();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

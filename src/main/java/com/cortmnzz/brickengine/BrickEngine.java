package com.cortmnzz.brickengine;

import co.aikar.commands.PaperCommandManager;
import com.cortmnzz.brickengine.command.SequenceCommand;
import com.cortmnzz.brickengine.configuration.Configuration;
import com.cortmnzz.brickengine.configuration.ConfigurationManager;
import com.cortmnzz.brickengine.sequence.SequenceLoader;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class BrickEngine extends JavaPlugin {

    @Getter private static BrickEngine instance;

    @Getter private final Configuration sequencesConfiguration = ConfigurationManager.getConfig("sequences.yml");

    @Getter private PaperCommandManager paperCommandManager;

    @Getter private SequenceLoader sequenceLoader;

    @Override
    public void onEnable() {
        instance = this;

        this.paperCommandManager = new PaperCommandManager(this);
        this.paperCommandManager.registerCommand(new SequenceCommand());

        this.sequenceLoader = new SequenceLoader();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

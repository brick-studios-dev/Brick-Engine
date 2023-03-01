package com.cortmnzz.brickengine.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import com.cortmnzz.brickengine.BrickEngine;
import com.cortmnzz.brickengine.action.SequenceAction;
import org.bukkit.entity.Player;

import java.util.Optional;

@CommandAlias("sequence")
public class SequenceCommand extends BaseCommand {
    @Subcommand("start")
    @CommandPermission("sequence.admin")
    private void start(Player player, String name) {
        Optional<SequenceAction> sequenceActionOptional = BrickEngine.getInstance().getSequenceLoader().getSequenceAction(name);

        if (sequenceActionOptional.isPresent()) {
            sequenceActionOptional.get().perform();
            return;
        }

        player.sendMessage("no-sequence");
    }
}
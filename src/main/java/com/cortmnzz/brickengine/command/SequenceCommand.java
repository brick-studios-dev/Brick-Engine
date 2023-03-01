package com.cortmnzz.brickengine.command;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.suggestions.Suggestions;
import com.cortmnzz.brickengine.BrickEngine;
import com.cortmnzz.brickengine.action.SequenceAction;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SequenceCommand {
    @CommandMethod("sequence start <sequence>")
    private void start(Player player, @Argument(value = "sequence", suggestions = "sequence_suggestion") String sequence) {
        Optional<SequenceAction> sequenceActionOptional = BrickEngine.getInstance().getSequenceLoader().getSequenceAction(sequence);

        if (sequenceActionOptional.isPresent()) {
            sequenceActionOptional.get().perform();
            return;
        }

        player.sendMessage("no-sequence");
    }

    @Suggestions("sequence_suggestion")
    public List<String> suggestions() {
        return BrickEngine.getInstance().getSequenceLoader().getSequenceActionList()
                .stream().map(SequenceAction::getName).collect(Collectors.toList());
    }
}
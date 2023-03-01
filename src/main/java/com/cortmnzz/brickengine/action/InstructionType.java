package com.cortmnzz.brickengine.action;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public enum InstructionType {
    SOUND(instructionType -> {
        instructionType.getPlayerList().forEach(player -> player.playSound(player.getLocation(),
                Sound.valueOf((String) instructionType.getInstructionProperty().get(InstructionProperty.SOUND)), 1, 1));

        instructionType.getEnd().perform();
    }),
    MESSAGE(instructionType -> {
        instructionType.getPlayerList().forEach(player -> player.sendMessage((String) instructionType.getInstructionProperty().get(InstructionProperty.MESSAGE)));

        instructionType.getEnd().perform();
    }),
    TITLE(instructionType -> {
        instructionType.getPlayerList().forEach(player -> player.sendTitle((String) instructionType.getInstructionProperty().get(InstructionProperty.TITLE_TITLE),
                (String) instructionType.getInstructionProperty().get(InstructionProperty.TITLE_SUBTITLE)));

        instructionType.getEnd().perform();
    }),
    COMMAND(instructionType -> {
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), (String) instructionType.getProperty(InstructionProperty.COMMAND));

        instructionType.getEnd().perform();
    }),
    COMMAND_PLAYER(instructionType -> {
        instructionType.getPlayerList().forEach(player -> player.performCommand((String) instructionType.getProperty(InstructionProperty.COMMAND_PLAYER)));

        instructionType.getEnd().perform();
    }),
    COMMAND_GLOBAL(instructionType -> {
        instructionType.getPlayerList().forEach(player -> {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),
                    ((String) instructionType.getProperty(InstructionProperty.COMMAND_GLOBAL)).replace("%player_name%", player.getName()));
        });

        instructionType.getEnd().perform();
    });
    
    @Getter
    private final Consumer<SequenceAction> consumer;

    InstructionType(Consumer<SequenceAction> consumer) {
        this.consumer = consumer;
    }
}

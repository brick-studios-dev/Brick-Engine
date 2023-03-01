package com.cortmnzz.brickengine.action;

import com.google.gson.annotations.Expose;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Data
public class SequenceAction {
    @Expose private final List<Player> playerList;

    @Expose private final List<SequenceAction> next;
    @Expose private SequenceAction end;
    @Expose private long delay;
    @Expose private boolean stopSequenceOnException;

    @Expose private Runnable runnable;
    @Expose private Consumer<Player> consumer;

    @Expose private final InstructionType type;
    @Expose private HashMap<InstructionProperty, ?> instructionProperty;

    public SequenceAction(InstructionType type) {
        this.type = type;

        this.playerList = Bukkit.getServer().getOnlinePlayers().stream()
                .filter(player -> !player.hasPermission("")).collect(Collectors.toList());

        this.next = new ArrayList<>();
        this.instructionProperty = new HashMap<>();
    }
    public void addNext(SequenceAction sequenceAction) {
        this.next.add(sequenceAction);
    }
    public Object getProperty(InstructionProperty instructionProperty) {
        return this.instructionProperty.get(instructionProperty);
    }
    public void perform() {
        this.instructionProperty.forEach((key, value) -> this.type.getConsumer().accept(this));
    }
}

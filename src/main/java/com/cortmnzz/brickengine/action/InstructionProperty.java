package com.cortmnzz.brickengine.action;

import lombok.Getter;

public enum InstructionProperty {
    SOUND("sound"),
    MESSAGE("message"),
    CHAT("chat"),
    TITLE_TITLE("title"),
    TITLE_SUBTITLE("subtitle"),
    COMMAND("command"),
    COMMAND_PLAYER("command_player"),
    COMMAND_GLOBAL("command_global"),
    RUNNABLE("runnable"),
    PLAYER_CONSUMER("player_consumer");

    @Getter private final String name;

    InstructionProperty(String name) {
        this.name = name;
    }
}

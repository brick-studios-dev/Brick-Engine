package com.cortmnzz.brickengine.action;

import lombok.Data;

@Data
public class InstructionValue {
    private final InstructionType instructionType;
    private final Object object;
}

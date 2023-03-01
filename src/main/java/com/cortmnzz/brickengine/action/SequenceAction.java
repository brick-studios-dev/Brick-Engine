package com.cortmnzz.brickengine.action;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SequenceAction {
    @Expose private final List<SequenceAction> next;
    @Expose private SequenceAction end;
    @Expose private long delay;
    @Expose private boolean stopSequenceOnException;

    public SequenceAction() {
        this.next = new ArrayList<>();
    }
    public void addNext(SequenceAction sequenceAction) {
        this.next.add(sequenceAction);
    }
}

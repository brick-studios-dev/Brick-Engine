package com.cortmnzz.brickengine.sequence;

import com.cortmnzz.brickengine.BrickEngine;
import com.cortmnzz.brickengine.action.SequenceAction;
import com.cortmnzz.brickengine.configuration.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SequenceLoader {
    private final Configuration configuration = BrickEngine.getInstance().getSequencesConfiguration();
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    @Getter private final List<SequenceAction> sequenceActionList = new ArrayList<>();

    public SequenceLoader() {
        Optional.ofNullable(configuration.getConfigurationSection("enabled")).ifPresent(enabled -> {
            enabled.getKeys(false).forEach(section -> {
                try {
                    SequenceAction sequenceAction = objectMapper.readValue(section, SequenceAction.class);

                    Optional.ofNullable(configuration.getConfigurationSection(section)).ifPresent(value -> {
                        value.getKeys(false).forEach(next -> {
                            try {
                                sequenceAction.addNext(objectMapper.readValue(section, SequenceAction.class));
                            } catch (JsonProcessingException exception) {
                                throw new RuntimeException(exception);
                            }
                        });
                    });

                    sequenceActionList.add(sequenceAction);
                } catch (JsonProcessingException exception) {
                    throw new RuntimeException(exception);
                }
            });
        });
    }

    public Optional<SequenceAction> getSequenceAction(String name) {
        return this.sequenceActionList.stream().filter(sequenceAction -> sequenceAction.getName().equals(name)).findFirst();
    }
}

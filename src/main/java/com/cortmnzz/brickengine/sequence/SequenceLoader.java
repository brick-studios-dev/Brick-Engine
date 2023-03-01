package com.cortmnzz.brickengine.sequence;

import com.cortmnzz.brickengine.BrickEngine;
import com.cortmnzz.brickengine.action.SequenceAction;
import com.cortmnzz.brickengine.configuration.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SequenceLoader {
    private static final Configuration configuration = BrickEngine.getInstance().getSequencesConfiguration();
    private static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    private static final List<SequenceAction> sequenceActionList = new ArrayList<>();

    static {
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
}

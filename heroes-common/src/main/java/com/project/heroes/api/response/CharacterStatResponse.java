package com.project.heroes.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CharacterStatResponse(@JsonProperty("stat") List<CharacterStatOption> stats) {

    public record CharacterStatOption(@JsonProperty("stat_name") String statName,
                                      @JsonProperty("stat_value") String statValue) {

    }

}

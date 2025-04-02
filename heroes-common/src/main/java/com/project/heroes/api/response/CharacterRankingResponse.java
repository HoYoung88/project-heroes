package com.project.heroes.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CharacterRankingResponse(@JsonProperty("ranking") List<Ranking> rankings) {

    public record Ranking(@JsonProperty("ranking_type") String rankingType,
                          @JsonProperty("ranking") long ranking,
                          @JsonProperty("character_name") String characterName,
                          @JsonProperty("score") long score) {
    }

}
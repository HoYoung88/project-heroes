package com.project.heroes.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CharacterTitleResponse(@JsonProperty("title") List<Title> titles) {

    public record Title(@JsonProperty("title_type") String titleType,
                        @JsonProperty("title_name") String titleName) {

    }
}

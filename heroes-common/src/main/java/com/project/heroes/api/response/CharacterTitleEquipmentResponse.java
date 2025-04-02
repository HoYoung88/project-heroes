package com.project.heroes.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.heroes.api.response.CharacterTitleResponse.Title;
import java.util.List;

public record CharacterTitleEquipmentResponse(@JsonProperty("title_equipment") List<TitleEquipment> titleEquipments) {

    public record TitleEquipment(@JsonProperty("title_equipment_type_name") String titleEquipmentTypeName,
                                 @JsonProperty("title_type") String titleType,
                                 @JsonProperty("title_name") String titleName) {

    }
}

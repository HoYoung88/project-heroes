package com.project.heroes.character.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;

public record CharacterOcid(String ocid, String characterName) {

}

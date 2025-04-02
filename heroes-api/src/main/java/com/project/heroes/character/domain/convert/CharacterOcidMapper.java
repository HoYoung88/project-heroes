package com.project.heroes.character.domain.convert;

import com.project.heroes.character.domain.CharacterOcid;
import com.project.heroes.entity.character.HeroesCharacterOcid;
import org.mapstruct.Mapper;

@Mapper
public interface CharacterOcidMapper {

    HeroesCharacterOcid dtoToEntity(CharacterOcid characterOcid);

}

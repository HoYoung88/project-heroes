package com.project.heroes.character.domain.convert;

import com.project.heroes.character.domain.CharacterInfo.CharacterStat;
import com.project.heroes.entity.character.HeroesCharacterStat;
import org.mapstruct.Mapper;

@Mapper
public interface CharacterStatMapper {

    HeroesCharacterStat dtoToEntity(CharacterStat characterStat);
}

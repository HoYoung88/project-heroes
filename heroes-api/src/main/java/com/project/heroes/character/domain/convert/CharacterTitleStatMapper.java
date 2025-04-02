package com.project.heroes.character.domain.convert;

import com.project.heroes.character.domain.CharacterInfo.TitleStat;
import com.project.heroes.entity.character.HeroesCharacterTitleStat;
import org.mapstruct.Mapper;

@Mapper
public interface CharacterTitleStatMapper {

    HeroesCharacterTitleStat dtoToEntity(TitleStat titleStat);
}

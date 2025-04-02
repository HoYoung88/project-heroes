package com.project.heroes.character.domain.convert;

import com.project.heroes.character.domain.CharacterInfo.SkillAwakeningOption;
import com.project.heroes.entity.character.HeroesCharacterSkillAwakening;
import org.mapstruct.Mapper;

@Mapper
public interface CharacterSkillAwakeningMapper {

    HeroesCharacterSkillAwakening dtoToEntity(SkillAwakeningOption skillAwakeningOption);

}

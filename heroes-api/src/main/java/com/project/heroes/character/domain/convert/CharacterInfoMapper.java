package com.project.heroes.character.domain.convert;

import com.project.heroes.api.response.CharacterBasicResponse;
import com.project.heroes.api.response.CharacterGuildResponse;
import com.project.heroes.api.response.CharacterItemEquipmentResponse;
import com.project.heroes.api.response.CharacterStatResponse;
import com.project.heroes.character.domain.CharacterInfo;
import com.project.heroes.character.domain.CharacterInfo.CharacterStat;
import com.project.heroes.character.domain.CharacterInfo.SkillAwakeningOption;
import com.project.heroes.character.domain.CharacterInfo.TitleStat;
import com.project.heroes.character.utils.CharacterStatUtils;
import com.project.heroes.character.utils.TitleStatUtils;
import com.project.heroes.entity.character.HeroesCharacter;
import com.project.heroes.entity.character.HeroesCharacterSkillAwakening;
import com.project.heroes.entity.character.HeroesCharacterStat;
import com.project.heroes.entity.character.HeroesCharacterTitleStat;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
    imports = {TitleStatUtils.class, CharacterStatUtils.class},
    uses = {
        CharacterItemEquipmentMapper.class
    }
)
public interface CharacterInfoMapper {

    @Mapping(target = "titleStat", expression = "java(TitleStatUtils.responseToDto(characterBasicResponse))")
    @Mapping(target = "characterStat", expression = "java(CharacterStatUtils.responseToDto(characterStatResponse))")
    @Mapping(target = "characterGuildName", source="characterGuildResponse.guildName")
    CharacterInfo responseToDto(CharacterBasicResponse characterBasicResponse,
                                CharacterGuildResponse characterGuildResponse,
                                CharacterStatResponse characterStatResponse,
                                CharacterItemEquipmentResponse characterItemEquipmentResponse);

    @Mapping(source = "characterInfo.dressPoint.totalPoint", target = "dressPointTotalPoint")
    @Mapping(source = "characterInfo.dressPoint.avatarPoint", target = "dressPointAvatarPoint")
    @Mapping(source = "characterInfo.dressPoint.backPoint", target = "dressPointBackPoint")
    @Mapping(source = "characterInfo.dressPoint.tailPoint", target = "dressPointTailPoint")
    @Mapping(source = "characterInfo.dressPoint.objectPoint", target = "dressPointObjectPoint")
    HeroesCharacter dtoToEntity(String ocid, CharacterInfo characterInfo,
                                @Context CharacterItemEquipmentMapper characterItemEquipmentMapper);

    HeroesCharacterStat dtoToStatEntity(CharacterStat characterStat);
    HeroesCharacterTitleStat dtoToTitleStatEntity(TitleStat titleStat);
    HeroesCharacterSkillAwakening dtoToSkillAwakeningEntity(SkillAwakeningOption skillAwakeningOption);

    @AfterMapping
    default void afterDtoToEntity(@MappingTarget HeroesCharacter heroesCharacter,
                                  CharacterInfo dto,
                                  @Context CharacterItemEquipmentMapper characterItemEquipmentMapper) {

        heroesCharacter.addStat(dtoToStatEntity(dto.characterStat()));
        heroesCharacter.addTitleStat(dtoToTitleStatEntity(dto.titleStat()));

        dto.skillAwakenings().forEach(item -> {
            heroesCharacter.addSkillAwakening(dtoToSkillAwakeningEntity(item));
        });

        dto.itemEquipments().forEach(item -> {
            heroesCharacter.addItemEquipment(characterItemEquipmentMapper.dtoToEntity(item));
        });

    }

    @Mapping(target = "dressPoint.totalPoint", source = "dressPointTotalPoint")
    @Mapping(target = "dressPoint.avatarPoint", source = "dressPointAvatarPoint")
    @Mapping(target = "dressPoint.backPoint", source = "dressPointBackPoint")
    @Mapping(target = "dressPoint.tailPoint", source = "dressPointTailPoint")
    @Mapping(target = "dressPoint.objectPoint", source = "dressPointObjectPoint")
    @Mapping(target = "characterStat.physicalAttack", source = "stat.physicalAttack")
    @Mapping(target = "characterStat.magicAttack", source = "stat.magicAttack")
    @Mapping(target = "characterStat.defense", source = "stat.defense")
    @Mapping(target = "characterStat.strength", source = "stat.strength")
    @Mapping(target = "characterStat.dexterity", source = "stat.dexterity")
    @Mapping(target = "characterStat.intelligence", source = "stat.intelligence")
    @Mapping(target = "characterStat.will", source = "stat.will")
    @Mapping(target = "characterStat.luck", source = "stat.luck")
    @Mapping(target = "characterStat.maxHealth", source = "stat.maxHealth")
    @Mapping(target = "characterStat.maxStamina", source = "stat.maxStamina")
    @Mapping(target = "characterStat.attackSpeed", source = "stat.attackSpeed")
    @Mapping(target = "characterStat.additionalDamage", source = "stat.additionalDamage")
    @Mapping(target = "characterStat.critical", source = "stat.critical")
    @Mapping(target = "characterStat.criticalDamage", source = "stat.criticalDamage")
    @Mapping(target = "characterStat.criticalResistance", source = "stat.criticalResistance")
    @Mapping(target = "characterStat.balance", source = "stat.balance")
    @Mapping(target = "characterStat.attackLimitRelease", source = "stat.attackLimitRelease")
    @Mapping(target = "characterStat.resistance", source = "stat.resistance")
    CharacterInfo entityToDto(HeroesCharacter heroesCharacter);

}

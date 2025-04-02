package com.project.heroes.character.domain.convert;

import com.project.heroes.character.domain.CharacterInfo.CharacterItemEquipment;
import com.project.heroes.character.domain.CharacterInfo.CharacterItemEquipment.ItemOption;
import com.project.heroes.entity.character.HeroesCharacterItemEquipmentOption;
import com.project.heroes.entity.character.HeroesCharacterItemEquipmentTuningStat;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface CharacterItemEquipmentOptionMapper {

    CharacterItemEquipment.ItemOption entityToDto(
        HeroesCharacterItemEquipmentOption heroesCharacterItemEquipmentOption);

    HeroesCharacterItemEquipmentOption dtoToEntity(CharacterItemEquipment.ItemOption itemOption);

    @AfterMapping
    default void afterDtoToEntity(@MappingTarget HeroesCharacterItemEquipmentOption heroesCharacterItemEquipmentOption,
                                  CharacterItemEquipment.ItemOption itemOption) {

        itemOption.tuningStats().forEach(item ->
            heroesCharacterItemEquipmentOption.addTuningStat(dtoToTuningStatEntity(item)));

    }

    HeroesCharacterItemEquipmentTuningStat dtoToTuningStatEntity(ItemOption.TuningStatOption itemOption);
}

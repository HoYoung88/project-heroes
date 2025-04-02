package com.project.heroes.character.domain.convert;

import com.project.heroes.api.response.CharacterItemEquipmentResponse.ItemEquipment;
import com.project.heroes.character.domain.CharacterInfo.CharacterItemEquipment;
import com.project.heroes.character.utils.ItemEquipmentUtils;
import com.project.heroes.entity.character.HeroesCharacterItemEquipment;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(imports = {
    ItemEquipmentUtils.class
}, uses = {
    CharacterItemEquipmentOptionMapper.class
})
public interface CharacterItemEquipmentMapper {

    @Mapping(target = "itemEquipmentSlotName", expression = "java( ItemEquipmentUtils.replaceItemEquipmentSlotName(itemEquipment) )")
    @Mapping(target = "itemOption.enhancementLevel", source = "itemOption.enhancementLevel", defaultValue = "0")
    @Mapping(target = "itemOption.prefixEnchantPreset", expression = "java( ItemEquipmentUtils.getPrefixEnchantPreset(itemOption) )")
    @Mapping(target = "itemOption.suffixEnchantPreset", expression = "java( ItemEquipmentUtils.getSuffixEnchantUsePreset(itemOption) )")
    @Mapping(target = "itemOption.powerInfusionPresetStatName", expression = "java( ItemEquipmentUtils.getPowerInfusionPresetStatName(itemOption) )")
    @Mapping(target = "itemOption.powerInfusionPresetStatValue", expression = "java( ItemEquipmentUtils.getPowerInfusionPresetStatValue(itemOption) )")
    @Mapping(target = "itemOption.color1", source = "itemOption.cashItemColor.color1")
    @Mapping(target = "itemOption.color2", source = "itemOption.cashItemColor.color2")
    @Mapping(target = "itemOption.color3", source = "itemOption.cashItemColor.color3")
    CharacterItemEquipment responseToDto(ItemEquipment itemEquipment);

    HeroesCharacterItemEquipment dtoToEntity(CharacterItemEquipment characterItemEquipment);

    @AfterMapping
    default void afterDtoToEntity(@MappingTarget HeroesCharacterItemEquipment heroesCharacterItemEquipment,
                                  CharacterItemEquipment dto) {
        CharacterItemEquipmentOptionMapper mapper = Mappers.getMapper(CharacterItemEquipmentOptionMapper.class);
        heroesCharacterItemEquipment.addItemOption(mapper.dtoToEntity(dto.itemOption()));
    }

    CharacterItemEquipment entityToDto(HeroesCharacterItemEquipment heroesCharacterItemEquipment);
}

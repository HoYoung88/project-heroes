package com.project.heroes.meta.dto;

import java.util.Map;

public record MetaDto(CharacterMetaDto character, EquipmentSlotDto equipment) {

    public record CharacterMetaDto(String[] physicalClass, String[] magicClass, Map<String, String[]> skill) {}

    public record EquipmentSlotDto(Map<String, String> slotName, String[] slotOrders) {}
}

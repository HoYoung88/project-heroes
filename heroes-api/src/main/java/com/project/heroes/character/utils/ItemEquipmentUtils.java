package com.project.heroes.character.utils;

import com.project.heroes.api.response.CharacterItemEquipmentResponse.ItemEquipment;
import com.project.heroes.api.response.CharacterItemEquipmentResponse.ItemOption;
import com.project.heroes.api.response.CharacterItemEquipmentResponse.StatOption;
import java.util.Objects;
import java.util.Optional;

public class ItemEquipmentUtils {

    public static String getPrefixEnchantPreset(ItemOption itemOption) {
        if (!Objects.isNull(itemOption.prefixEnchantUsePresetNo())) {
            return itemOption.prefixEnchantUsePresetNo() == 1 ? itemOption.prefixEnchantPreset1()
                : itemOption.prefixEnchantPreset2();
        }

        return null;
    }

    public static String getSuffixEnchantUsePreset(ItemOption itemOption) {
        if (!Objects.isNull(itemOption.suffixEnchantUsePresetNo())) {
            return itemOption.suffixEnchantUsePresetNo() == 1 ? itemOption.suffixEnchantPreset1()
                : itemOption.suffixEnchantPreset2();
        }

        return null;
    }

    public static String getPowerInfusionPresetStatName(ItemOption itemOption) {

        if (!Objects.isNull(itemOption.powerInfusionUsePresetNo())) {
            return Optional.ofNullable(itemOption.powerInfusionUsePresetNo() == 1 ? itemOption.powerInfusionPreset1()
                    : itemOption.powerInfusionPreset2()).orElseGet(() -> new StatOption(null, null))
                .statName();
        }

        return null;
    }

    public static String getPowerInfusionPresetStatValue(ItemOption itemOption) {

        if (!Objects.isNull(itemOption.powerInfusionUsePresetNo())) {
            return Optional.ofNullable(itemOption.powerInfusionUsePresetNo() == 1 ? itemOption.powerInfusionPreset1()
                    : itemOption.powerInfusionPreset2()).orElseGet(() -> new StatOption(null, null))
                .statValue();
        }

        return null;
    }

    public static String replaceItemEquipmentSlotName(ItemEquipment itemEquipment) {
        return itemEquipment.itemEquipmentSlotName().replaceAll(" ", "_").toUpperCase();
    }
}

package com.project.heroes.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CharacterItemEquipmentResponse(@JsonProperty("item_equipment") List<ItemEquipment> itemEquipments) {

    public record ItemEquipment(@JsonProperty("item_equipment_page") String itemEquipmentPage,
                                @JsonProperty("item_equipment_slot_name") String itemEquipmentSlotName,
                                @JsonProperty("item_name") String itemName,
                                @JsonProperty("item_option") ItemOption itemOption) {

    }

    public record ItemOption(@JsonProperty("enhancement_level") Integer enhancementLevel,
                             @JsonProperty("tuning_stat") List<StatOption> tuningStats,
                             @JsonProperty("ability_name") String abilityName,
                             @JsonProperty("prefix_enchant_use_preset_no") Integer prefixEnchantUsePresetNo,
                             @JsonProperty("suffix_enchant_use_preset_no") Integer suffixEnchantUsePresetNo,
                             @JsonProperty("prefix_enchant_preset_1") String prefixEnchantPreset1,
                             @JsonProperty("suffix_enchant_preset_1") String suffixEnchantPreset1,
                             @JsonProperty("prefix_enchant_preset_2") String prefixEnchantPreset2,
                             @JsonProperty("suffix_enchant_preset_2") String suffixEnchantPreset2,
                             @JsonProperty("power_infusion_use_preset_no") Integer powerInfusionUsePresetNo,
                             @JsonProperty("power_infusion_preset_1") StatOption powerInfusionPreset1,
                             @JsonProperty("power_infusion_preset_2") StatOption powerInfusionPreset2,
                             @JsonProperty("cash_item_color") CashItemColor cashItemColor,
                             @JsonProperty("avatar_color_use_preset_no") Integer avatarColorUsePresetNo,
                             @JsonProperty("avatar_color_preset_1") CashItemColor avatarColorPreset1,
                             @JsonProperty("avatar_color_preset_2") CashItemColor avatarColorPreset2,
                             @JsonProperty("avatar_color_preset_3") CashItemColor avatarColorPreset3,
                             @JsonProperty("avatar_color_preset_4") CashItemColor avatarColorPreset4,
                             @JsonProperty("avatar_color_preset_5") CashItemColor avatarColorPreset5,
                             @JsonProperty("avatar_inner_armor_color_preset_1") AvatarInnerColorOption avatarInnerArmorColorPreset1,
                             @JsonProperty("avatar_inner_armor_color_preset_2") AvatarInnerColorOption avatarInnerArmorColorPreset2,
                             @JsonProperty("avatar_inner_armor_color_preset_3") AvatarInnerColorOption avatarInnerArmorColorPreset3,
                             @JsonProperty("avatar_inner_armor_color_preset_4") AvatarInnerColorOption avatarInnerArmorColorPreset4,
                             @JsonProperty("avatar_inner_armor_color_preset_5") AvatarInnerColorOption avatarInnerArmorColorPreset5) {

    }

    public record StatOption(@JsonProperty("stat_name") String statName,
                             @JsonProperty("stat_value") String statValue) {

    }

    public record CashItemColor(@JsonProperty("color_1") String color1,
                                @JsonProperty("color_2") String color2,
                                @JsonProperty("color_3") String color3) {

    }

    public record AvatarInnerColorOption(@JsonProperty("color_1") String color1,
                                         @JsonProperty("color_2") String color2,
                                         @JsonProperty("color_3") String color3,
                                         @JsonProperty("default_color_flag") String defaultColorFlag) {

    }
}

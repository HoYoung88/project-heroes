package com.project.heroes.character.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.project.heroes.api.response.CharacterItemEquipmentResponse.ItemEquipment;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public record CharacterInfo(String characterName,
                            String characterGuildName,
                            LocalDateTime characterDateCreate,
                            LocalDateTime characterDateLastLogin,
                            LocalDateTime characterDateLastLogout,
                            String characterClassName,
                            String characterGender,
                            Long characterExp,
                            Long characterLevel,
                            String cairdeName,
                            Long titleCount,
                            Long idTitleCount,
                            Long totalTitleCount,
                            TitleStat titleStat,
                            List<SkillAwakeningOption> skillAwakenings,
                            DressPoint dressPoint,
                            @JsonProperty("stat")
                            CharacterStat characterStat,
                            List<CharacterItemEquipment> itemEquipments) {

    public record TitleStat(Long physicalAttack,
                            Long magicAttack,
                            Long strength,
                            Long dexterity,
                            Long intelligence,
                            Long will,
                            Long maxHealth,
                            Long critical,
                            Long balance) {

    }

    public record SkillAwakeningOption(String skillName, String itemName) {

    }

    public record DressPoint(Long totalPoint,
                             Long avatarPoint,
                             Long backPoint,
                             Long tailPoint,
                             Long objectPoint) {

    }

    public record CharacterStat(Long physicalAttack,
                                Long magicAttack,
                                Long defense,
                                Long strength,
                                Long dexterity,
                                Long intelligence,
                                Long will,
                                Long luck,
                                Long maxHealth,
                                Long maxStamina,
                                Long attackSpeed,
                                Long additionalDamage,
                                Long critical,
                                Long criticalDamage,
                                Long criticalResistance,
                                Long balance,
                                Long attackLimitRelease,
                                Long resistance) {

    }

    public record CharacterItemEquipment(String itemEquipmentPage,
                                         String itemEquipmentSlotName,
                                         String itemName,
                                         ItemOption itemOption) {

        public record ItemOption(Integer enhancementLevel,
                                 List<TuningStatOption> tuningStats,
                                 String abilityName,
                                 String prefixEnchantPreset,
                                 String suffixEnchantPreset,
                                 String powerInfusionPresetStatName,
                                 String powerInfusionPresetStatValue,
                                 String color1,
                                 String color2,
                                 String color3) {

            public record TuningStatOption(String statName,
                                           String statValue) {

            }

        }

    }

}

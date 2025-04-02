package com.project.heroes.entity.character;

import com.project.heroes.entity.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeroesCharacterItemEquipmentOption extends BaseEntity {

    private Integer enhancementLevel;
    private String abilityName;
    private String prefixEnchantPreset;
    private String suffixEnchantPreset;
    private String powerInfusionPresetStatName;
    private String powerInfusionPresetStatValue;
    private String color1;
    private String color2;
    private String color3;

    @OneToMany(mappedBy = "heroesCharacterItemEquipmentOption", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy
    private Set<HeroesCharacterItemEquipmentTuningStat> tuningStats;

    @Setter
    @OneToOne
    @JoinColumn
    private HeroesCharacterItemEquipment heroesCharacterItemEquipment;

    @Builder
    public HeroesCharacterItemEquipmentOption(Integer enhancementLevel,
                                              String abilityName,
                                              String prefixEnchantPreset,
                                              String suffixEnchantPreset,
                                              String powerInfusionPresetStatName,
                                              String powerInfusionPresetStatValue,
                                              String color1,
                                              String color2,
                                              String color3) {
        this.enhancementLevel = enhancementLevel;
        this.abilityName = abilityName;
        this.prefixEnchantPreset = prefixEnchantPreset;
        this.suffixEnchantPreset = suffixEnchantPreset;
        this.powerInfusionPresetStatName = powerInfusionPresetStatName;
        this.powerInfusionPresetStatValue = powerInfusionPresetStatValue;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.tuningStats = new LinkedHashSet<>();
    }

    public void addTuningStat(HeroesCharacterItemEquipmentTuningStat tuningStat) {
        this.tuningStats.add(tuningStat);
        tuningStat.setHeroesCharacterItemEquipmentOption(this);
    }

    public Set<HeroesCharacterItemEquipmentTuningStat> getTuningStats() {
        return Objects.isNull(tuningStats) ? Collections.emptySet() : Collections.unmodifiableSet(tuningStats);
    }
}

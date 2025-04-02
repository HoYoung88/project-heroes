package com.project.heroes.entity.character;

import com.project.heroes.entity.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeroesCharacterItemEquipmentTuningStat extends BaseEntity {

    private String statName;
    private String statValue;

    @Setter
    @ManyToOne
    private HeroesCharacterItemEquipmentOption heroesCharacterItemEquipmentOption;

    @Builder
    public HeroesCharacterItemEquipmentTuningStat(String statName, String statValue) {
        this.statName = statName;
        this.statValue = statValue;
    }

}

package com.project.heroes.entity.character;

import com.project.heroes.entity.base.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeroesCharacterItemEquipment extends BaseEntity {

    private String itemEquipmentPage;
    private String itemEquipmentSlotName;
    private String itemName;

    @OneToOne(mappedBy = "heroesCharacterItemEquipment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private HeroesCharacterItemEquipmentOption itemOption;

    @Setter
    @ManyToOne
    private HeroesCharacter character;

    @Builder
    public HeroesCharacterItemEquipment(String itemEquipmentPage,
                                        String itemEquipmentSlotName,
                                        String itemName) {
        this.itemEquipmentPage = itemEquipmentPage;
        this.itemEquipmentSlotName = itemEquipmentSlotName;
        this.itemName = itemName;

    }

    public void addItemOption(HeroesCharacterItemEquipmentOption itemOption) {
        this.itemOption = itemOption;
        itemOption.setHeroesCharacterItemEquipment(this);
    }
}

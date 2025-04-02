package com.project.heroes.entity.character;

import com.project.heroes.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeroesCharacterSkillAwakening extends BaseEntity {

    private String skillName;
    private String itemName;

    @Setter
    @ManyToOne
    private HeroesCharacter character;

    @Builder
    public HeroesCharacterSkillAwakening(String skillName, String itemName) {
        this.skillName = skillName;
        this.itemName = itemName;
    }
}

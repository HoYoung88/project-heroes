package com.project.heroes.entity.character;

import com.project.heroes.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeroesCharacterTitleStat extends BaseEntity {

    private Long physicalAttack;
    private Long magicAttack;
    private Long strength;
    private Long dexterity;
    private Long intelligence;
    private Long will;
    private Long maxHealth;
    private Long critical;
    private Long balance;

    @Setter
    @OneToOne
    @JoinColumn
    private HeroesCharacter character;

    @Builder
    public HeroesCharacterTitleStat(Long physicalAttack, Long magicAttack, Long strength, Long dexterity,
                                    Long intelligence,
                                    Long will, Long maxHealth, Long critical, Long balance) {
        this.physicalAttack = physicalAttack;
        this.magicAttack = magicAttack;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.will = will;
        this.maxHealth = maxHealth;
        this.critical = critical;
        this.balance = balance;
    }
}

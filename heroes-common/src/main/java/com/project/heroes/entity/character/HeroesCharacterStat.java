package com.project.heroes.entity.character;

import com.project.heroes.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class HeroesCharacterStat extends BaseEntity {

    private Long physicalAttack;
    private Long magicAttack;
    private Long defense;
    private Long strength;
    private Long dexterity;
    private Long intelligence;
    private Long will;
    private Long luck;
    private Long maxHealth;
    private Long maxStamina;
    private Long attackSpeed;
    private Long additionalDamage;
    private Long critical;
    private Long criticalDamage;
    private Long criticalResistance;
    private Long balance;
    private Long attackLimitRelease;
    private Long resistance;

    @Setter
    @OneToOne
    @JoinColumn
    private HeroesCharacter character;

    @Builder
    public HeroesCharacterStat(Long physicalAttack, Long magicAttack, Long defense, Long strength, Long dexterity,
                               Long intelligence, Long will, Long luck, Long maxHealth, Long maxStamina,
                               Long attackSpeed,
                               Long additionalDamage,
                               Long critical,
                               Long criticalDamage,
                               Long criticalResistance,
                               Long balance, Long attackLimitRelease, Long resistance) {
        this.physicalAttack = physicalAttack;
        this.magicAttack = magicAttack;
        this.defense = defense;
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.will = will;
        this.luck = luck;
        this.maxHealth = maxHealth;
        this.maxStamina = maxStamina;
        this.attackSpeed = attackSpeed;
        this.additionalDamage = additionalDamage;
        this.critical = critical;
        this.criticalDamage = criticalDamage;
        this.criticalResistance = criticalResistance;
        this.balance = balance;
        this.attackLimitRelease = attackLimitRelease;
        this.resistance = resistance;
    }
}

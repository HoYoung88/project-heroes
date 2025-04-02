package com.project.heroes.api.response.types;

import java.util.Arrays;
import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatType {

    PHYSICAL_ATTACK("공격력"),
    MAGIC_ATTACK("마법공격력"),
    DEFENSE("방어력"),
    STRENGTH("힘"),
    DEXTERITY("민첩"),
    INTELLIGENCE("지능"),
    WILL("의지"),
    LUCK("행운"),
    MAX_HEALTH("최대 생명력"),
    MAX_STAMINA("최대 스태미나"),
    ATTACK_SPEED("공격속도"),
    ADDITIONAL_DAMAGE("추가피해"),
    CRITICAL("크리티컬"),
    CRITICAL_DAMAGE("크리티컬 피해량"),
    CRITICAL_RESISTANCE("크리티컬 저항"),
    BALANCE("밸런스"),
    ATTACK_LIMIT_RELEASE("공격력 제한 해제"),
    RESISTANCE("대항력");

    private final String statName;

    public static StatType[] titleStatNameValues() {
        return Arrays.asList(PHYSICAL_ATTACK,
                MAGIC_ATTACK,
                STRENGTH,
                DEXTERITY,
                INTELLIGENCE,
                WILL,
                MAX_HEALTH,
                CRITICAL,
                BALANCE)
            .toArray(StatType[]::new);
    }
}

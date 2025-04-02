package com.project.heroes.character.utils;

import static com.project.heroes.api.response.types.StatType.*;

import com.project.heroes.api.response.CharacterStatResponse;
import com.project.heroes.api.response.CharacterStatResponse.CharacterStatOption;
import com.project.heroes.api.response.types.StatType;
import com.project.heroes.character.domain.CharacterInfo;
import com.project.heroes.character.domain.CharacterInfo.CharacterStat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CharacterStatUtils {

    public static Map<String, Long> createCharacterStatMap(List<CharacterStatOption> characterStatOptions) {
        return characterStatOptions.stream()
            .collect(Collectors.toMap(CharacterStatOption::statName,
                stat -> Long.parseLong(stat.statValue())));
    }

    public static Long getStatValue(Map<String, Long> characterStatMap, StatType statType) {
        return characterStatMap.getOrDefault(statType.getStatName(), 0L);
    }

    public static CharacterInfo.CharacterStat responseToDto(CharacterStatResponse characterStatResponse) {
        Map<String, Long> characterStatMap = createCharacterStatMap(characterStatResponse.stats());

        Map<StatType, Long> statValues = Arrays.stream(StatType.values())
            .collect(Collectors.toMap(statType -> statType, statType -> getStatValue(characterStatMap, statType)));

        return new CharacterStat(
            statValues.get(PHYSICAL_ATTACK),
            statValues.get(MAGIC_ATTACK),
            statValues.get(DEFENSE),
            statValues.get(STRENGTH),
            statValues.get(DEXTERITY),
            statValues.get(INTELLIGENCE),
            statValues.get(WILL),
            statValues.get(LUCK),
            statValues.get(MAX_HEALTH),
            statValues.get(MAX_STAMINA),
            statValues.get(ATTACK_SPEED),
            statValues.get(ADDITIONAL_DAMAGE),
            statValues.get(CRITICAL),
            statValues.get(CRITICAL_DAMAGE),
            statValues.get(CRITICAL_RESISTANCE),
            statValues.get(BALANCE),
            statValues.get(ATTACK_LIMIT_RELEASE),
            statValues.get(RESISTANCE)
        );
    }
}

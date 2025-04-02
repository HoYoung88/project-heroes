package com.project.heroes.character.utils;

import static com.project.heroes.api.response.types.StatType.*;

import com.project.heroes.api.response.CharacterBasicResponse;
import com.project.heroes.api.response.CharacterBasicResponse.TitleStatOption;
import com.project.heroes.api.response.types.StatType;
import com.project.heroes.character.domain.CharacterInfo.TitleStat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TitleStatUtils {

    public static Map<String, Long> createTitleStatMap(List<TitleStatOption> titleStats) {
        return titleStats.stream()
            .collect(Collectors.toMap(TitleStatOption::statName,
                stat -> Long.parseLong(stat.statValue())));
    }

    public static Long getStatValue(Map<String, Long> titleStatMap, StatType statType) {
        return titleStatMap.getOrDefault(statType.getStatName(), 0L);
    }

    public static TitleStat responseToDto(CharacterBasicResponse characterBasicResponse) {

        Map<String, Long> titleStatMap = TitleStatUtils.createTitleStatMap(characterBasicResponse.titleStats());
        Map<StatType, Long> statValues = Arrays.stream(StatType.titleStatNameValues())
            .collect(Collectors.toMap(statType -> statType, statType -> getStatValue(titleStatMap, statType)));

        return new TitleStat(
            statValues.get(PHYSICAL_ATTACK),
            statValues.get(MAGIC_ATTACK),
            statValues.get(STRENGTH),
            statValues.get(DEXTERITY),
            statValues.get(INTELLIGENCE),
            statValues.get(WILL),
            statValues.get(MAX_HEALTH),
            statValues.get(CRITICAL),
            statValues.get(BALANCE)
        );

    }
}

package com.project.heroes.api.response.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RankingType {
    PHYSICAL_DAMAGE("0"),
    MAGIC_DAMAGE("1");

    private final String value;
}

package com.project.heroes.ranking.real_time.domain.dto;

public record RealTimeDto(String rankingType,
                          long ranking,
                          String characterName,
                          long score) {

}

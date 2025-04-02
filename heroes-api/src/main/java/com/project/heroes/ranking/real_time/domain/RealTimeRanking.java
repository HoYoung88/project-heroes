package com.project.heroes.ranking.real_time.domain;

public record RealTimeRanking(String rankingType,
                              Long ranking,
                              String characterName,
                              Long score) {

}

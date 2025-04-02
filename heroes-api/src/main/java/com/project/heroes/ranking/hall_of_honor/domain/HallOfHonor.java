package com.project.heroes.ranking.hall_of_honor.domain;

public record HallOfHonor(String rankingType,
                          Long ranking,
                          String characterName,
                          Long score) {

}

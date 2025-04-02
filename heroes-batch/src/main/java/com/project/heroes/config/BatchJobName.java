package com.project.heroes.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BatchJobName {
    RANKING_HALL_OF_HONOR("rankingHallOfHonorJob"),
    RANKING_REAL_TIME("rankingRealTimeJob"),
    ;

    private final String jobName;
}

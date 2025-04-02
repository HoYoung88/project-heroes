package com.project.heroes.utils;

import java.time.LocalDateTime;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

public class JobParameterUtils {

    public static JobParameters getRealTimeSaveTime() {
        return new JobParametersBuilder()
            .addLocalDateTime("time", LocalDateTime.now().withMinute(0).withSecond(0))
            .toJobParameters();
    }

    public static JobParameters getHallOfHonorSaveTime() {
        return new JobParametersBuilder()
            .addLocalDateTime("time", LocalDateTime.now().withHour(9).withMinute(0).withSecond(0))
            .toJobParameters();
    }
}

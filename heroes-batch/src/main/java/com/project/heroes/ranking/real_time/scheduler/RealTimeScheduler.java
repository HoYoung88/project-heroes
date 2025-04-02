package com.project.heroes.ranking.real_time.scheduler;

import com.project.heroes.config.BatchJobName;
import com.project.heroes.utils.BatchJobRunner;
import com.project.heroes.utils.JobParameterUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RealTimeScheduler {

    private final JobLauncher jobLauncher;
    private final TaskExecutor threadPoolTaskExecutor;
    private final BatchJobRunner batchJobRunner;

    @Scheduled(cron = "0 0 0/1 * * *")
    public void run() {

        threadPoolTaskExecutor.execute(() -> {
            try {
                batchJobRunner.run(BatchJobName.RANKING_REAL_TIME, JobParameterUtils.getRealTimeSaveTime());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

}

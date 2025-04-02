package com.project.heroes.ranking.hall_of_honor.scheduler;

import com.project.heroes.config.BatchJobName;
import com.project.heroes.event.BatchErrorEvent;
import com.project.heroes.utils.BatchJobRunner;
import com.project.heroes.utils.JobParameterUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HallOfHonorScheduler {

    private final TaskExecutor threadPoolTaskExecutor;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final BatchJobRunner batchJobRunner;

    @Scheduled(cron = "0 0 9 * * *")
	public void run() {

		threadPoolTaskExecutor.execute(() -> {
            try {
                batchJobRunner.run(BatchJobName.RANKING_HALL_OF_HONOR, JobParameterUtils.getHallOfHonorSaveTime());
            } catch (Exception e) {

                BatchErrorEvent batchErrorEvent = BatchErrorEvent.builder()
                    .message(e.getMessage())
                    .build();

                applicationEventPublisher.publishEvent(batchErrorEvent);

                throw new RuntimeException(e);
            }
        });

	}
}

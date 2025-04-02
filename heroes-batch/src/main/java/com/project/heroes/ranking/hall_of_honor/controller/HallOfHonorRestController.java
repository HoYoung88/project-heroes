package com.project.heroes.ranking.hall_of_honor.controller;

import com.project.heroes.config.BatchJobName;
import com.project.heroes.event.BatchErrorEvent;
import com.project.heroes.utils.BatchJobRunner;
import com.project.heroes.utils.JobParameterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ranking/hall-of-honor")
@RequiredArgsConstructor
public class HallOfHonorRestController {

    private final TaskExecutor threadPoolTaskExecutor;
    private final BatchJobRunner batchJobRunner;
    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping(value = "")
    public ResponseEntity<Void> runHallOfHonor() {
        executeJob();
        return ResponseEntity.ok().build();
    }

    private void executeJob() {

        try {
            batchJobRunner.asyncRun(BatchJobName.RANKING_HALL_OF_HONOR,
                JobParameterUtils.getHallOfHonorSaveTime());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        threadPoolTaskExecutor.execute(() -> {
//            try {
//                batchJobRunner.run(BatchJobName.RANKING_REAL_TIME, JobParameterUtils.getHallOfHonorSaveTime());
//            } catch (Exception e) {
//
//                applicationEventPublisher.publishEvent(BatchErrorEvent.builder()
//                    .message(e.getMessage())
//                    .build());
//
//                throw new RuntimeException(e);
//            }
//        });
    }
}

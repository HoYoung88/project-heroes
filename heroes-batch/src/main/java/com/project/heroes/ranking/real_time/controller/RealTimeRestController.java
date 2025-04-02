package com.project.heroes.ranking.real_time.controller;

import com.project.heroes.config.BatchJobName;
import com.project.heroes.utils.BatchJobRunner;
import com.project.heroes.utils.JobParameterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ranking/real-time")
@RequiredArgsConstructor
public class RealTimeRestController {

    private final TaskExecutor threadPoolTaskExecutor;
    private final BatchJobRunner batchJobRunner;

    @GetMapping(value = "")
    public ResponseEntity<Void> run() {
        executeJob();
        return ResponseEntity.ok().build();
    }

    private void executeJob() {
        threadPoolTaskExecutor.execute(() -> {
            try {
                batchJobRunner.run(BatchJobName.RANKING_REAL_TIME, JobParameterUtils.getHallOfHonorSaveTime());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}

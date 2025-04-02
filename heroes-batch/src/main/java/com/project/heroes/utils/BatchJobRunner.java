package com.project.heroes.utils;

import com.project.heroes.config.BatchJobName;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BatchJobRunner {

    private final JobLauncher jobLauncher;
    private final Map<BatchJobName, Job> jobMap;
    private final TaskExecutor threadPoolTaskExecutor;

    public JobExecution run(BatchJobName jobName,
                            JobParameters jobParameters) throws Exception {
        Job job = jobMap.get(jobName);
        if (Objects.isNull(job)) {
            throw new IllegalArgumentException("해당 Job 존재하지 않습니다: " + jobName);
        }

        return jobLauncher.run(job, jobParameters);
    }

    public void asyncRun(BatchJobName jobName,
                         JobParameters jobParameters,
                         Consumer<JobExecution> handler) throws Exception {

        Job job = jobMap.get(jobName);
        if (Objects.isNull(job)) {
            throw new IllegalArgumentException("해당 Job 존재하지 않습니다: " + jobName);
        }

        threadPoolTaskExecutor.execute(() -> {
            try {
                handler.accept(jobLauncher.run(job, jobParameters));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void asyncRun(BatchJobName jobName,
                         JobParameters jobParameters) throws Exception {

        Job job = jobMap.get(jobName);
        if (Objects.isNull(job)) {
            throw new IllegalArgumentException("해당 Job 존재하지 않습니다: " + jobName);
        }

        threadPoolTaskExecutor.execute(() -> {
            try {
                jobLauncher.run(job, jobParameters);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}

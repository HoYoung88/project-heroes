package com.project.heroes.config;

import com.project.heroes.entity.ranking.RankingRealTime;
import com.project.heroes.ranking.hall_of_honor.tasklet.HallOfHonorTasklet;
import com.project.heroes.ranking.real_time.domain.dto.RealTimeDto;
import com.project.heroes.ranking.real_time.processor.MagicRealTimeProcessor;
import com.project.heroes.ranking.real_time.processor.PhysicalRealTimeProcessor;
import com.project.heroes.ranking.real_time.reader.MagicRealTimeReader;
import com.project.heroes.ranking.real_time.reader.PhysicalRealTimeReader;
import com.project.heroes.ranking.real_time.writer.MagicRealTimeWriter;
import com.project.heroes.ranking.real_time.writer.PhysicalRealTimeWriter;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchJobConfig {

    @Bean
    public Step rankingHallOfHonorStep(JobRepository jobRepository,
                                       PlatformTransactionManager platformTransactionManager,
                                       HallOfHonorTasklet hallOfHonorTasklet) {
        return new StepBuilder("rankingHallOfHonorStep", jobRepository)
            .tasklet(hallOfHonorTasklet, platformTransactionManager)
            .build();
    }

    @Bean
    public Step physicalRealTimeStep(JobRepository jobRepository,
                                     PlatformTransactionManager platformTransactionManager,
                                     PhysicalRealTimeReader physicalRealTimeReader,
                                     PhysicalRealTimeWriter physicalRealTimeWriter,
                                     PhysicalRealTimeProcessor physicalRealTimeProcessor) {
        return new StepBuilder("physicalRealTimeStep", jobRepository)
            .<RealTimeDto, RankingRealTime>chunk(500, platformTransactionManager)
            .reader(physicalRealTimeReader)
            .processor(physicalRealTimeProcessor)
            .writer(physicalRealTimeWriter)
            .build();
    }

    @Bean
    public Step magicRealTimeStep(JobRepository jobRepository,
                                  PlatformTransactionManager platformTransactionManager,
                                  MagicRealTimeReader magicRealTimeReader,
                                  MagicRealTimeWriter magicRealTimeWriter,
                                  MagicRealTimeProcessor magicRealTimeProcessor) {
        return new StepBuilder("magicRealTimeStep", jobRepository)
            .<RealTimeDto, RankingRealTime>chunk(500, platformTransactionManager)
            .reader(magicRealTimeReader)
            .processor(magicRealTimeProcessor)
            .writer(magicRealTimeWriter)
            .build();
    }

    @Bean
    public Job rankingHallOfHonorJob(JobRepository jobRepository, Step rankingHallOfHonorStep) {
        return new JobBuilder(BatchJobName.RANKING_HALL_OF_HONOR.getJobName(), jobRepository)
            .start(rankingHallOfHonorStep)
            .build();
    }

    @Bean
    public Job rankingRealTimeJob(JobRepository jobRepository,
                                  Step physicalRealTimeStep,
                                  Step magicRealTimeStep) {
        return new JobBuilder(BatchJobName.RANKING_REAL_TIME.getJobName(), jobRepository)
            .start(physicalRealTimeStep)
            .next(magicRealTimeStep)
            .build();
    }

    @Bean
    public Map<BatchJobName, Job> jobMap(Job rankingHallOfHonorJob,
                                         Job rankingRealTimeJob) {
        return Map.of(
            BatchJobName.RANKING_HALL_OF_HONOR, rankingHallOfHonorJob,
            BatchJobName.RANKING_REAL_TIME, rankingRealTimeJob);
    }

}

package com.project.heroes.ranking.hall_of_honor.tasklet;

import com.project.heroes.api.response.CharacterRankingResponse.Ranking;
import com.project.heroes.infra.service.HeroesRankingApiService;
import com.project.heroes.ranking.hall_of_honor.domain.mappings.HallOfHonorMapper;
import com.project.heroes.ranking.hall_of_honor.sevice.HallOfHonorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HallOfHonorTasklet implements Tasklet {

    private final HeroesRankingApiService heroesRankingApiService;
    private final HallOfHonorService hallOfHonorService;
    private final HallOfHonorMapper hallOfHonorMapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        List<Ranking> rankings = heroesRankingApiService.callRankingHallOfHonor();
        hallOfHonorService.saveAll(hallOfHonorMapper.responseToEntity(rankings));

        return RepeatStatus.FINISHED;
    }
}

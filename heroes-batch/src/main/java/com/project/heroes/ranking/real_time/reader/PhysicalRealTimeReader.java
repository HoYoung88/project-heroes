package com.project.heroes.ranking.real_time.reader;

import com.project.heroes.api.response.CharacterRankingResponse.Ranking;
import com.project.heroes.infra.service.HeroesRankingApiService;
import com.project.heroes.ranking.real_time.domain.dto.RealTimeDto;
import com.project.heroes.ranking.real_time.domain.mappings.RealTimeMapper;
import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhysicalRealTimeReader extends AbstractPagingItemReader<RealTimeDto> {

    private final HeroesRankingApiService heroesRankingApiService;
    private final RealTimeMapper realTimeMapper;

    @PostConstruct
    public void init() {
        setPageSize(500);
    }

    @Override
    protected void doReadPage() {
        int page = getPage() + 1;

        results = new CopyOnWriteArrayList<>();

        if (getPageSize() * page > totalContentCount()) {
            results = Collections.emptyList();
        } else {

            List<Ranking> rankingRealTime = heroesRankingApiService.callPhysicalRankingRealTime(page);

            if (rankingRealTime.isEmpty()) {
                results = Collections.emptyList();
            } else {
                results.addAll(realTimeMapper.responseToDto(rankingRealTime));
            }
        }

    }

    private int totalContentCount() {
        return 4000;
    }
}

package com.project.heroes.ranking.real_time.processor;

import com.project.heroes.entity.ranking.RankingRealTime;
import com.project.heroes.ranking.real_time.domain.dto.RealTimeDto;
import com.project.heroes.ranking.real_time.domain.mappings.RealTimeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhysicalRealTimeProcessor implements ItemProcessor<RealTimeDto, RankingRealTime> {

    private final RealTimeMapper realTimeMapper;

    @Override
    public RankingRealTime process(RealTimeDto item) throws Exception {
        return realTimeMapper.responseToEntity(item);
    }
}

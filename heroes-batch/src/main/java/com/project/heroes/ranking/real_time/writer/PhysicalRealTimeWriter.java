package com.project.heroes.ranking.real_time.writer;

import com.project.heroes.entity.ranking.RankingHallOfHonor;
import com.project.heroes.entity.ranking.RankingRealTime;
import com.project.heroes.ranking.hall_of_honor.sevice.HallOfHonorService;
import com.project.heroes.ranking.real_time.domain.dto.RealTimeDto;
import com.project.heroes.ranking.real_time.sevice.RealTimeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhysicalRealTimeWriter implements ItemWriter<RankingRealTime> {

    private final RealTimeService realTimeService;

    @Override
    public void write(Chunk<? extends RankingRealTime> chunk) throws Exception {
        if(!chunk.getItems().isEmpty()) {
            realTimeService.saveAll(chunk.getItems());
        }
    }
}

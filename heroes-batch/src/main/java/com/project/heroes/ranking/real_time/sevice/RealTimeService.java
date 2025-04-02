package com.project.heroes.ranking.real_time.sevice;

import com.project.heroes.entity.ranking.RankingRealTime;
import com.project.heroes.ranking.real_time.repository.RealTimeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RealTimeService {

    private final RealTimeRepository realTimeRepository;

    public void saveAll(List<? extends RankingRealTime> rankingRealTimes) {
        realTimeRepository.saveAll(rankingRealTimes);
    }
}

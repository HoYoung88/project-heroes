package com.project.heroes.ranking.real_time.service;

import com.project.heroes.api.response.types.RankingType;
import com.project.heroes.entity.ranking.RankingHallOfHonor;
import com.project.heroes.entity.ranking.RankingRealTime;
import com.project.heroes.ranking.real_time.repository.RealTimeRankingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class RealTimeRankingService {

    private final RealTimeRankingRepository realTimeRankingRepository;

    private Page<RankingRealTime> findAllRealTimeList(RankingType rankingType, Pageable pageable) {
        return realTimeRankingRepository.findByRankingType(rankingType.getValue(), pageable);
    }

    public Page<RankingRealTime> findPhysicalRealTimeList(Pageable pageable) {
        return findAllRealTimeList(RankingType.PHYSICAL_DAMAGE, pageable);
    }

    public Page<RankingRealTime> findMagicRealTimeList(Pageable pageable) {
        return findAllRealTimeList(RankingType.MAGIC_DAMAGE, pageable);
    }
}

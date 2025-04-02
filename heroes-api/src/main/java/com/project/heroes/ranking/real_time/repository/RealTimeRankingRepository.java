package com.project.heroes.ranking.real_time.repository;

import com.project.heroes.entity.ranking.RankingHallOfHonor;
import com.project.heroes.entity.ranking.RankingRealTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealTimeRankingRepository extends JpaRepository<RankingRealTime, Long> {

    Page<RankingRealTime> findByRankingType(String rankingType, Pageable pageable);
}

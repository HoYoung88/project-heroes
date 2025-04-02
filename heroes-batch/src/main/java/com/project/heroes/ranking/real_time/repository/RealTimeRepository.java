package com.project.heroes.ranking.real_time.repository;

import com.project.heroes.entity.ranking.RankingRealTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealTimeRepository extends JpaRepository<RankingRealTime, Long> {

}

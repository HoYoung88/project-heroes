package com.project.heroes.ranking.hall_of_honor.repository;

import com.project.heroes.entity.ranking.RankingHallOfHonor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallOfHonorRepository extends JpaRepository<RankingHallOfHonor, Long> {

}

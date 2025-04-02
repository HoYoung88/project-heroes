package com.project.heroes.ranking.hall_of_honor.repository;

import com.project.heroes.entity.ranking.RankingHallOfHonor;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HallOfHonorRepository extends JpaRepository<RankingHallOfHonor, Long> {

    @Query(value = """
        SELECT r
        FROM RankingHallOfHonor r
        WHERE r.rankingType = :rankingType
          AND r.createdAt = function('DATE_FORMAT', now(), '%Y-%m-%d 09:00:00')
        """)
    Page<RankingHallOfHonor> findByRankingType(String rankingType, Pageable pageable);
}

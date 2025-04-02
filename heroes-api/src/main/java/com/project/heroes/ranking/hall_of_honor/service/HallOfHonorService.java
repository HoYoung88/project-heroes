package com.project.heroes.ranking.hall_of_honor.service;

import com.project.heroes.api.response.types.RankingType;
import com.project.heroes.entity.ranking.RankingHallOfHonor;
import com.project.heroes.ranking.hall_of_honor.domain.convert.HallOfHonorMapper;
import com.project.heroes.ranking.hall_of_honor.repository.HallOfHonorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class HallOfHonorService {

    private final HallOfHonorRepository hallOfHonorRepository;

    private Page<RankingHallOfHonor> findAllHallOfHonorList(RankingType rankingType, Pageable pageable) {
        return hallOfHonorRepository.findByRankingType(rankingType.getValue(),
            pageable);
    }

    public Page<RankingHallOfHonor> findPhysicalHallOfHonorList(Pageable pageable) {
        return findAllHallOfHonorList(RankingType.PHYSICAL_DAMAGE, pageable);
    }

    public Page<RankingHallOfHonor> findMagicHallOfHonorList(Pageable pageable) {
        return findAllHallOfHonorList(RankingType.MAGIC_DAMAGE, pageable);
    }
}

package com.project.heroes.ranking.hall_of_honor.sevice;

import com.project.heroes.entity.ranking.RankingHallOfHonor;
import com.project.heroes.ranking.hall_of_honor.repository.HallOfHonorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HallOfHonorService {

    private final HallOfHonorRepository hallOfHonorRepository;

    public void saveAll(List<RankingHallOfHonor> rankingHallOfHonors) {
        hallOfHonorRepository.saveAll(rankingHallOfHonors);
    }
}

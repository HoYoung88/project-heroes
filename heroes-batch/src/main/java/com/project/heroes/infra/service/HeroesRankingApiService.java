package com.project.heroes.infra.service;

import com.project.heroes.api.NexonHeroesApiClient;
import com.project.heroes.api.response.CharacterRankingResponse.Ranking;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroesRankingApiService {

    private final NexonHeroesApiClient nexonHeroesApiClient;

    public List<Ranking> callRankingHallOfHonor() {

        return Stream.of(
                nexonHeroesApiClient.getPhysicalRankingHallOfHonor().rankings(),
                nexonHeroesApiClient.getMagicRankingHallOfHonor().rankings())
            .flatMap(List::stream)
            .toList();

    }

    public List<Ranking> callPhysicalRankingRealTime(int page) {
        return nexonHeroesApiClient.getPhysicalRankingRealTime(page).rankings();
    }

    public List<Ranking> callMagicRankingRealTime(int page) {
        return nexonHeroesApiClient.getMagicRankingRealTime(page).rankings();
    }

}

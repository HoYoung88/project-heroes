package com.project.heroes.api.uri.heroes;

import com.project.heroes.api.properties.OpenNexonApiProperties;
import com.project.heroes.api.properties.OpenNexonApiProperties.Ranking;
import com.project.heroes.api.response.types.RankingType;
import com.project.heroes.api.uri.AbstractUriBuilder;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeroesRankingUriBuilder extends AbstractUriBuilder {

    private final Ranking ranking;

    public HeroesRankingUriBuilder(OpenNexonApiProperties openNexonApiProperties) {
        this.ranking = openNexonApiProperties.getHeroes().getPaths().getRanking();
    }

    public URI buildRankingHallOfHonor(RankingType rankingType) {
        return buildUri(ranking.getHallOfHonor(), rankingType.getValue());
    }

    public URI buildRankingRealTime(RankingType rankingType, long pageNo) {
        return buildUri(ranking.getRealTime(), rankingType.getValue(), pageNo);
    }
}

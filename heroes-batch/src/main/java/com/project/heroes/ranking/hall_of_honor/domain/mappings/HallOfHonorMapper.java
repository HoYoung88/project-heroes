package com.project.heroes.ranking.hall_of_honor.domain.mappings;

import com.project.heroes.api.response.CharacterRankingResponse.Ranking;
import com.project.heroes.entity.ranking.RankingHallOfHonor;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface HallOfHonorMapper {

    List<RankingHallOfHonor> responseToEntity(List<Ranking> dtos);

}

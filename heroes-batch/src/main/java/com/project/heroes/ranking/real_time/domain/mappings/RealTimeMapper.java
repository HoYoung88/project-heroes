package com.project.heroes.ranking.real_time.domain.mappings;

import com.project.heroes.api.response.CharacterRankingResponse.Ranking;
import com.project.heroes.entity.ranking.RankingRealTime;
import com.project.heroes.ranking.real_time.domain.dto.RealTimeDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface RealTimeMapper {
    RankingRealTime responseToEntity(RealTimeDto dto);
    List<RankingRealTime> responseToEntity(List<RealTimeDto> dtos);
    List<RealTimeDto> responseToDto(List<Ranking> dtos);
}

package com.project.heroes.ranking.real_time.domain.convert;

import com.project.heroes.entity.ranking.RankingRealTime;
import com.project.heroes.ranking.real_time.domain.RealTimeRanking;
import java.util.List;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

@Mapper
public interface RealTimeRankingMapper {

    List<RealTimeRanking> entitiesToDto(List<RankingRealTime> entities);

    default PagedModel<RealTimeRanking> entitiesToPageModel(Page<RankingRealTime> entities) {

        List<RealTimeRanking> content = entitiesToDto(entities.getContent());
        int pageNumber = entities.getNumber() + 1;
        int pageSize = entities.getPageable().getPageSize();
        long totalElements = entities.getTotalElements();

        return new PagedModel<>(new PageImpl<>(content, PageRequest.of(pageNumber, pageSize), totalElements));
    }
}

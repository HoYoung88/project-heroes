package com.project.heroes.ranking.hall_of_honor.domain.convert;

import com.project.heroes.entity.ranking.RankingHallOfHonor;
import com.project.heroes.ranking.hall_of_honor.domain.HallOfHonor;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

@Mapper
public interface HallOfHonorMapper {

    List<HallOfHonor> entitiesToDto(List<RankingHallOfHonor> entities);

    default PagedModel<HallOfHonor> entitiesToPageModel(Page<RankingHallOfHonor> entities) {

        List<HallOfHonor> content = entitiesToDto(entities.getContent());
        int pageNumber = entities.getNumber() + 1;
        int pageSize = entities.getPageable().getPageSize();
        long totalElements = entities.getTotalElements();

        return new PagedModel<>(new PageImpl<>(content, PageRequest.of(pageNumber, pageSize), totalElements));
    }
}

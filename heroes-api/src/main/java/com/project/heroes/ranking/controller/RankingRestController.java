package com.project.heroes.ranking.controller;

import com.project.heroes.entity.ranking.RankingHallOfHonor;
import com.project.heroes.entity.ranking.RankingRealTime;
import com.project.heroes.ranking.hall_of_honor.domain.HallOfHonor;
import com.project.heroes.ranking.hall_of_honor.domain.convert.HallOfHonorMapper;
import com.project.heroes.ranking.hall_of_honor.service.HallOfHonorService;
import com.project.heroes.ranking.real_time.domain.RealTimeRanking;
import com.project.heroes.ranking.real_time.domain.convert.RealTimeRankingMapper;
import com.project.heroes.ranking.real_time.service.RealTimeRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/ranking")
@RequiredArgsConstructor
public class RankingRestController {

    private final HallOfHonorService hallOfHonorService;
    private final HallOfHonorMapper hallOfHonorMapper;
    private final RealTimeRankingService realTimeRankingService;
    private final RealTimeRankingMapper realTimeRankingMapper;

    @GetMapping(value = "/hall-of-honor/physical-list")
    public PagedModel<HallOfHonor> findPhysicalHallOfHonorList(@RequestParam("page") int page,
                                                               @RequestParam("perPage") int perPage) {

        Page<RankingHallOfHonor> physicalHallOfHonorList = hallOfHonorService.findPhysicalHallOfHonorList(
            createPageRequest(page, perPage));

        return hallOfHonorMapper.entitiesToPageModel(physicalHallOfHonorList);
    }

    @GetMapping(value = "/hall-of-honor/magic-list")
    public PagedModel<HallOfHonor> findMagicHallOfHonorList(@RequestParam("page") int page,
                                                            @RequestParam("perPage") int perPage) {

        Page<RankingHallOfHonor> magicHallOfHonorList = hallOfHonorService.findMagicHallOfHonorList(
            createPageRequest(page, perPage));
        return hallOfHonorMapper.entitiesToPageModel(magicHallOfHonorList);
    }

    @GetMapping(value = "/real-time/physical-list")
    public PagedModel<RealTimeRanking> findPhysicalRealTimeList(@RequestParam("page") int page,
                                                                @RequestParam("perPage") int perPage) {
        Page<RankingRealTime> physicalRealTimeList = realTimeRankingService.findPhysicalRealTimeList(
            createPageRequest(page, perPage));

        return realTimeRankingMapper.entitiesToPageModel(physicalRealTimeList);
    }

    @GetMapping(value = "/real-time/magic-list")
    public PagedModel<RealTimeRanking> findMagicRealTimeList(@RequestParam("page") int page,
                                                             @RequestParam("perPage") int perPage) {
        Page<RankingRealTime> magicRealTimeList = realTimeRankingService.findMagicRealTimeList(
            createPageRequest(page, perPage));

        return realTimeRankingMapper.entitiesToPageModel(magicRealTimeList);
    }

    private PageRequest createPageRequest(int page, int perPage) {
        return PageRequest.of(page - 1, perPage);
    }
}

package com.project.heroes.ranking.controller;

import com.project.heroes.entity.ranking.RankingHallOfHonor;
import com.project.heroes.entity.ranking.RankingRealTime;
import com.project.heroes.ranking.hall_of_honor.domain.HallOfHonor;
import com.project.heroes.ranking.hall_of_honor.domain.convert.HallOfHonorMapper;
import com.project.heroes.ranking.hall_of_honor.service.HallOfHonorService;
import com.project.heroes.ranking.real_time.domain.RealTimeRanking;
import com.project.heroes.ranking.real_time.domain.convert.RealTimeRankingMapper;
import com.project.heroes.ranking.real_time.service.RealTimeRankingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RankingRestController.class)
@DisplayName("RankingRestController 테스트")
class RankingRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HallOfHonorService hallOfHonorService;

    @MockBean
    private HallOfHonorMapper hallOfHonorMapper;

    @MockBean
    private RealTimeRankingService realTimeRankingService;

    @MockBean
    private RealTimeRankingMapper realTimeRankingMapper;

    @Test
    @DisplayName("명예의 전당 물리 공격력 조회 성공")
    void findPhysicalHallOfHonorList_Success() throws Exception {
        // given
        int page = 1;
        int perPage = 10;
        Page<RankingHallOfHonor> mockPage = new PageImpl<>(
            new ArrayList<>(), 
            PageRequest.of(page - 1, perPage), 
            0
        );
        PagedModel<HallOfHonor> mockPagedModel = new PagedModel<>(
            new PageImpl<>(new ArrayList<>(), PageRequest.of(page - 1, perPage), 0)
        );

        given(hallOfHonorService.findPhysicalHallOfHonorList(any(PageRequest.class)))
            .willReturn(mockPage);
        given(hallOfHonorMapper.entitiesToPageModel(mockPage))
            .willReturn(mockPagedModel);

        // when & then
        mockMvc.perform(get("/v1/ranking/hall-of-honor/physical-list")
                .param("page", String.valueOf(page))
                .param("perPage", String.valueOf(perPage)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("0000"));
    }

    @Test
    @DisplayName("명예의 전당 조회 실패 - 페이지 번호가 0 이하")
    void findPhysicalHallOfHonorList_Fail_InvalidPage() throws Exception {
        // when & then
        mockMvc.perform(get("/v1/ranking/hall-of-honor/physical-list")
                .param("page", "0")
                .param("perPage", "10"))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("명예의 전당 조회 실패 - perPage가 100 초과")
    void findPhysicalHallOfHonorList_Fail_InvalidPerPage() throws Exception {
        // when & then
        mockMvc.perform(get("/v1/ranking/hall-of-honor/physical-list")
                .param("page", "1")
                .param("perPage", "101"))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("실시간 랭킹 물리 공격력 조회 성공")
    void findPhysicalRealTimeList_Success() throws Exception {
        // given
        int page = 1;
        int perPage = 20;
        Page<RankingRealTime> mockPage = new PageImpl<>(
            new ArrayList<>(), 
            PageRequest.of(page - 1, perPage), 
            0
        );
        PagedModel<RealTimeRanking> mockPagedModel = new PagedModel<>(
            new PageImpl<>(new ArrayList<>(), PageRequest.of(page - 1, perPage), 0)
        );

        given(realTimeRankingService.findPhysicalRealTimeList(any(PageRequest.class)))
            .willReturn(mockPage);
        given(realTimeRankingMapper.entitiesToPageModel(mockPage))
            .willReturn(mockPagedModel);

        // when & then
        mockMvc.perform(get("/v1/ranking/real-time/physical-list")
                .param("page", String.valueOf(page))
                .param("perPage", String.valueOf(perPage)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("0000"));
    }

    @Test
    @DisplayName("실시간 랭킹 조회 실패 - 페이지 번호 누락")
    void findPhysicalRealTimeList_Fail_MissingPage() throws Exception {
        // when & then
        mockMvc.perform(get("/v1/ranking/real-time/physical-list")
                .param("perPage", "10"))
            .andExpect(status().isBadRequest());
    }
}


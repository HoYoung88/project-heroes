package com.project.heroes.character.controller;

import com.project.heroes.character.domain.CharacterInfo;
import com.project.heroes.character.service.CharacterInfoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CharacterInfoRestController.class)
@DisplayName("CharacterInfoRestController 테스트")
class CharacterInfoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterInfoService characterInfoService;

    @Test
    @DisplayName("캐릭터 정보 조회 성공")
    void findCharacterInfo_Success() throws Exception {
        // given
        String characterName = "테스트캐릭터";
        CharacterInfo mockCharacterInfo = new CharacterInfo(
            characterName,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            100L,
            null,
            null,
            null,
            null,
            null,
            new ArrayList<>(),
            null,
            null,
            new ArrayList<>()
        );
        
        given(characterInfoService.findByCharacterName(characterName))
            .willReturn(mockCharacterInfo);

        // when & then
        mockMvc.perform(get("/v1/character/{characterName}", characterName))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("0000"))
            .andExpect(jsonPath("$.data.characterName").value(characterName));
    }

    @Test
    @DisplayName("캐릭터 정보 조회 실패 - 빈 문자열")
    void findCharacterInfo_Fail_EmptyString() throws Exception {
        // when & then
        mockMvc.perform(get("/v1/character/ "))
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("캐릭터 정보 조회 실패 - 특수문자 포함")
    void findCharacterInfo_Fail_SpecialCharacters() throws Exception {
        // when & then
        mockMvc.perform(get("/v1/character/test@character"))
            .andExpect(status().isBadRequest());
    }
}


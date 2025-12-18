package com.project.heroes.character.controller;

import com.project.heroes.character.domain.CharacterInfo;
import com.project.heroes.character.service.CharacterInfoService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v1/character")
@RequiredArgsConstructor
@Validated
public class CharacterInfoRestController {

    private final CharacterInfoService characterInfoService;

    @GetMapping(value = "/{characterName}")
    public CharacterInfo findCharacterInfo(
        @PathVariable
        @NotBlank(message = "캐릭터 이름은 필수입니다.")
        @Size(min = 1, max = 50, message = "캐릭터 이름은 1자 이상 50자 이하여야 합니다.")
        @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s]+$", message = "캐릭터 이름은 한글, 영문, 숫자, 공백만 허용됩니다.")
        String characterName) {
        
        log.debug("캐릭터 정보 조회 요청: {}", characterName);
        return characterInfoService.findByCharacterName(characterName.trim());
    }
}

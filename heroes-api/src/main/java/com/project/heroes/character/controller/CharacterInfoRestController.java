package com.project.heroes.character.controller;

import com.project.heroes.character.domain.CharacterInfo;
import com.project.heroes.character.domain.CharacterOcid;
import com.project.heroes.character.service.CharacterInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/v1/character")
@RequiredArgsConstructor
public class CharacterInfoRestController {

    private final CharacterInfoService characterInfoService;

    @GetMapping(value = "/{characterName}")
    public CharacterInfo findCharacterInfo(@PathVariable String characterName) {
        return characterInfoService.findByCharacterName(characterName);
    }
}

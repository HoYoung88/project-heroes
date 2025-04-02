package com.project.heroes.character.service;

import com.project.heroes.character.domain.CharacterInfo;
import com.project.heroes.character.domain.CharacterOcid;
import com.project.heroes.character.domain.convert.CharacterInfoMapper;
import com.project.heroes.character.domain.convert.CharacterItemEquipmentMapper;
import com.project.heroes.character.domain.convert.CharacterOcidMapper;
import com.project.heroes.character.repository.CharacterInfoRepository;
import com.project.heroes.character.repository.CharacterOcidRepository;
import com.project.heroes.entity.character.HeroesCharacter;
import com.project.heroes.entity.character.HeroesCharacterOcid;
import com.project.heroes.infra.service.HeroesApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CharacterInfoService {

    private final HeroesApiService heroesApiService;
    private final CharacterOcidMapper characterOcidMapper;
    private final CharacterInfoMapper characterInfoMapper;
    private final CharacterItemEquipmentMapper characterItemEquipmentMapper;

    private final CharacterOcidRepository characterOcidRepository;
    private final CharacterInfoRepository characterInfoRepository;

    @Cacheable(cacheNames = "heroes.character", key = "#characterName", unless = "#result == null")
    public CharacterInfo findByCharacterName(String characterName) {

        HeroesCharacterOcid heroesCharacterOcid = findOcidByCharacterName(characterName);
        return characterInfoMapper.entityToDto(findCharacterInfoByOcid(heroesCharacterOcid.getOcid()));
    }

    private HeroesCharacterOcid findOcidByCharacterName(String characterName) {

        return characterOcidRepository.findByCharacterName(characterName)
            .orElseGet(() -> saveCharacterOcid(characterName));
    }

    private HeroesCharacterOcid saveCharacterOcid(String characterName) {

        CharacterOcid characterOcid = heroesApiService.callCharacterOcid(characterName);
        return characterOcidRepository.save(characterOcidMapper.dtoToEntity(characterOcid));
    }

    private HeroesCharacter findCharacterInfoByOcid(String ocid) {

        return characterInfoRepository.findByOcid(ocid)
            .orElseGet(() -> saveCharacterInfo(ocid));
    }

    private HeroesCharacter saveCharacterInfo(String ocid) {

        CharacterInfo characterInfo = heroesApiService.callCharacterInfo(ocid);
        HeroesCharacter saveHeroesCharacter = characterInfoMapper.dtoToEntity(ocid, characterInfo, characterItemEquipmentMapper);
        return characterInfoRepository.save(saveHeroesCharacter);
    }


}

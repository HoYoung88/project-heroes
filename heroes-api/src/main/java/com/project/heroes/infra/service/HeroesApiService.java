package com.project.heroes.infra.service;

import com.project.heroes.api.NexonHeroesApiClient;
import com.project.heroes.api.exception.NexonOpenApiException;
import com.project.heroes.api.response.CharacterBasicResponse;
import com.project.heroes.api.response.CharacterGuildResponse;
import com.project.heroes.api.response.CharacterItemEquipmentResponse;
import com.project.heroes.api.response.CharacterStatResponse;
import com.project.heroes.character.domain.CharacterInfo;
import com.project.heroes.character.domain.CharacterOcid;
import com.project.heroes.character.domain.convert.CharacterInfoMapper;
import com.project.heroes.character.exception.CharacterServiceErrorCode;
import com.project.heroes.character.exception.CharacterServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeroesApiService {

    private final NexonHeroesApiClient nexonHeroesApiClient;
    private final CharacterInfoMapper characterInfoMapper;

    public CharacterOcid callCharacterOcid(String characterName) {

        try {

            String ocid = nexonHeroesApiClient.getCharacterOcid(characterName).ocid();
            return new CharacterOcid(ocid, characterName);

        } catch (NexonOpenApiException e) {

            if (e.isOpenApi00004()) {
                throw new CharacterServiceException(CharacterServiceErrorCode.CHARACTER_NOT_FOUND);
            } else {
                throw e;
            }
        }

    }

    public CharacterInfo callCharacterInfo(String ocid) {

        CharacterBasicResponse characterBasic = nexonHeroesApiClient.getCharacterBasic(ocid);
        CharacterStatResponse characterStat = nexonHeroesApiClient.getCharacterStat(ocid);
        CharacterGuildResponse characterGuild = nexonHeroesApiClient.getCharacterGuild(ocid);

        CharacterItemEquipmentResponse characterItemEquipment = nexonHeroesApiClient.getCharacterItemEquipment(ocid);

        return characterInfoMapper.responseToDto(characterBasic, characterGuild, characterStat, characterItemEquipment);
    }

}

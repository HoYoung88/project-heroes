package com.project.heroes.infra.service;

import com.project.heroes.character.domain.CharacterInfo;
import com.project.heroes.character.domain.convert.CharacterInfoMapper;
import com.project.heroes.entity.character.HeroesCharacter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = {
    "logging.level.com.project.heroes=debug",
    "spring.profiles.active=local"
})
class HeroesApiServiceTest {

    @Autowired
    private HeroesApiService heroesApiService;

    @Autowired
    private CharacterInfoMapper characterInfoMapper;

    @Test
    public void callCharacterInfo() {
        String ocid = "b427faaec6802de956e3736dd341d832a9153b8809d2a7bcbdb6f8fb3f3ef41b";
        CharacterInfo characterInfo = heroesApiService.callCharacterInfo(ocid);
//        HeroesCharacter heroesCharacter = characterInfoMapper.dtoToEntity(ocid, characterInfo);

//        log.debug("heroesCharacter: {}", heroesCharacter);
//        log.debug("heroesCharacterStat: {}", heroesCharacter.getStat());
        log.debug("heroesCharacter: {}", characterInfo.itemEquipments());

    }

}
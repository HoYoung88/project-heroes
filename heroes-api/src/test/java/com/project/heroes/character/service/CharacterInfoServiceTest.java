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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CharacterInfoService 테스트")
class CharacterInfoServiceTest {

    @Mock
    private HeroesApiService heroesApiService;

    @Mock
    private CharacterOcidRepository characterOcidRepository;

    @Mock
    private CharacterInfoRepository characterInfoRepository;

    @Mock
    private CharacterOcidMapper characterOcidMapper;

    @Mock
    private CharacterInfoMapper characterInfoMapper;

    @Mock
    private CharacterItemEquipmentMapper characterItemEquipmentMapper;

    @InjectMocks
    private CharacterInfoService characterInfoService;

    private static final String CHARACTER_NAME = "테스트캐릭터";
    private static final String OCID = "test-ocid-12345";

    @Test
    @DisplayName("캐릭터 이름으로 정보 조회 성공 - DB에 OCID와 CharacterInfo 모두 존재")
    void findByCharacterName_Success_WhenBothExist() {
        // given
        HeroesCharacterOcid mockOcid = mock(HeroesCharacterOcid.class);
        HeroesCharacter mockCharacter = mock(HeroesCharacter.class);
        CharacterInfo mockCharacterInfo = mock(CharacterInfo.class);
        
        when(mockOcid.getOcid()).thenReturn(OCID);
        when(characterOcidRepository.findByCharacterName(CHARACTER_NAME))
            .thenReturn(Optional.of(mockOcid));
        when(characterInfoRepository.findByOcid(OCID))
            .thenReturn(Optional.of(mockCharacter));
        when(characterInfoMapper.entityToDto(mockCharacter))
            .thenReturn(mockCharacterInfo);

        // when
        CharacterInfo result = characterInfoService.findByCharacterName(CHARACTER_NAME);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(mockCharacterInfo);
        verify(characterOcidRepository).findByCharacterName(CHARACTER_NAME);
        verify(characterInfoRepository).findByOcid(OCID);
        verify(heroesApiService, never()).callCharacterOcid(anyString());
        verify(heroesApiService, never()).callCharacterInfo(anyString());
    }

    @Test
    @DisplayName("캐릭터 이름으로 정보 조회 성공 - OCID가 없어서 외부 API 호출")
    void findByCharacterName_Success_WhenOcidNotExists() {
        // given
        CharacterOcid characterOcid = new CharacterOcid(OCID, CHARACTER_NAME);
        HeroesCharacterOcid savedOcid = mock(HeroesCharacterOcid.class);
        HeroesCharacter mockCharacter = mock(HeroesCharacter.class);
        CharacterInfo mockCharacterInfo = mock(CharacterInfo.class);
        
        when(savedOcid.getOcid()).thenReturn(OCID);
        when(characterOcidRepository.findByCharacterName(CHARACTER_NAME))
            .thenReturn(Optional.empty());
        when(heroesApiService.callCharacterOcid(CHARACTER_NAME))
            .thenReturn(characterOcid);
        when(characterOcidMapper.dtoToEntity(characterOcid))
            .thenReturn(savedOcid);
        when(characterOcidRepository.save(savedOcid))
            .thenReturn(savedOcid);
        when(characterInfoRepository.findByOcid(OCID))
            .thenReturn(Optional.of(mockCharacter));
        when(characterInfoMapper.entityToDto(mockCharacter))
            .thenReturn(mockCharacterInfo);

        // when
        CharacterInfo result = characterInfoService.findByCharacterName(CHARACTER_NAME);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(mockCharacterInfo);
        verify(heroesApiService).callCharacterOcid(CHARACTER_NAME);
        verify(characterOcidRepository).save(savedOcid);
        verify(characterInfoRepository).findByOcid(OCID);
    }

    @Test
    @DisplayName("캐릭터 이름으로 정보 조회 성공 - CharacterInfo가 없어서 외부 API 호출")
    void findByCharacterName_Success_WhenCharacterInfoNotExists() {
        // given
        HeroesCharacterOcid mockOcid = mock(HeroesCharacterOcid.class);
        HeroesCharacter savedCharacter = mock(HeroesCharacter.class);
        CharacterInfo apiCharacterInfo = mock(CharacterInfo.class);
        CharacterInfo mappedCharacterInfo = mock(CharacterInfo.class);
        
        when(mockOcid.getOcid()).thenReturn(OCID);
        when(characterOcidRepository.findByCharacterName(CHARACTER_NAME))
            .thenReturn(Optional.of(mockOcid));
        when(characterInfoRepository.findByOcid(OCID))
            .thenReturn(Optional.empty());
        when(heroesApiService.callCharacterInfo(OCID))
            .thenReturn(apiCharacterInfo);
        when(characterInfoMapper.dtoToEntity(eq(OCID), eq(apiCharacterInfo), eq(characterItemEquipmentMapper)))
            .thenReturn(savedCharacter);
        when(characterInfoRepository.save(savedCharacter))
            .thenReturn(savedCharacter);
        when(characterInfoMapper.entityToDto(savedCharacter))
            .thenReturn(mappedCharacterInfo);

        // when
        CharacterInfo result = characterInfoService.findByCharacterName(CHARACTER_NAME);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(mappedCharacterInfo);
        verify(heroesApiService).callCharacterInfo(OCID);
        verify(characterInfoRepository).save(savedCharacter);
    }
}


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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
        try {
            // 병렬로 외부 API 호출
            CompletableFuture<CharacterBasicResponse> basicFuture = 
                CompletableFuture.supplyAsync(() -> nexonHeroesApiClient.getCharacterBasic(ocid));
            
            CompletableFuture<CharacterStatResponse> statFuture = 
                CompletableFuture.supplyAsync(() -> nexonHeroesApiClient.getCharacterStat(ocid));
            
            CompletableFuture<CharacterGuildResponse> guildFuture = 
                CompletableFuture.supplyAsync(() -> nexonHeroesApiClient.getCharacterGuild(ocid));
            
            CompletableFuture<CharacterItemEquipmentResponse> itemEquipmentFuture = 
                CompletableFuture.supplyAsync(() -> nexonHeroesApiClient.getCharacterItemEquipment(ocid));

            // 모든 결과가 완료될 때까지 대기
            CompletableFuture.allOf(basicFuture, statFuture, guildFuture, itemEquipmentFuture).join();

            CharacterBasicResponse characterBasic = basicFuture.get();
            CharacterStatResponse characterStat = statFuture.get();
            CharacterGuildResponse characterGuild = guildFuture.get();
            CharacterItemEquipmentResponse characterItemEquipment = itemEquipmentFuture.get();

            return characterInfoMapper.responseToDto(characterBasic, characterGuild, characterStat, characterItemEquipment);
            
        } catch (NexonOpenApiException e) {
            // Nexon API 예외는 그대로 전파
            throw e;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("캐릭터 정보 조회 중 인터럽트 발생: ocid={}", ocid, e);
            throw new CharacterServiceException(CharacterServiceErrorCode.CHARACTER_NOT_FOUND);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof NexonOpenApiException) {
                throw (NexonOpenApiException) cause;
            }
            log.error("캐릭터 정보 조회 중 오류 발생: ocid={}", ocid, e);
            throw new CharacterServiceException(CharacterServiceErrorCode.CHARACTER_NOT_FOUND);
        }
    }
}

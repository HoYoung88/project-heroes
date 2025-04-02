package com.project.heroes.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import com.project.heroes.api.exception.NexonOpenApiException;
import com.project.heroes.api.response.CharacterBasicResponse;
import com.project.heroes.api.response.CharacterItemEquipmentResponse;
import com.project.heroes.api.response.CharacterStatResponse;
import com.project.heroes.api.response.CharacterTitleEquipmentResponse;
import com.project.heroes.api.response.CharacterTitleResponse;
import com.project.heroes.api.uri.heroes.HeroesOcidUriBuilder;
import com.project.heroes.api.uri.heroes.HeroesRankingUriBuilder;
import com.project.heroes.api.uri.heroes.HeroesCharacterUriBuilder;
import com.project.heroes.api.properties.OpenNexonApiProperties;
import com.project.heroes.api.response.CharacterGuildResponse;
import com.project.heroes.api.response.CharacterOcidResponse;
import com.project.heroes.api.response.error.NexonOpenApiErrorResponse;
import com.project.heroes.api.response.CharacterRankingResponse;
import com.project.heroes.api.response.types.RankingType;
import jakarta.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class NexonHeroesApiClient {

    private final OpenNexonApiProperties properties;
    private final HeroesOcidUriBuilder heroesOcidUriBuilder;
    private final HeroesCharacterUriBuilder heroesCharacterUriBuilder;
    private final HeroesRankingUriBuilder heroesRankingUriBuilder;
    private RestClient heroesClient;
    private RateLimiter rateLimiter;

    @PostConstruct
    public void init() {
        rateLimiter = RateLimiter.create(500);
        String x_NEXON_OPEN_API_KEY_HEADER__KEY = "x-nxopen-api-key";

        this.heroesClient = RestClient.builder()
            .baseUrl(properties.getHeroes().getDomain())
            .defaultHeader(x_NEXON_OPEN_API_KEY_HEADER__KEY, properties.getHeroes().getApiKey())
            .requestInterceptor((request, body, execution) -> {
                log.info("Nexon Heroes API Request URI: {}", request.getURI());
                log.info("Nexon Heroes API Request Required Header: {}", request.getHeaders());
                return execution.execute(request, body);
            })
            .defaultStatusHandler(HttpStatusCode::is4xxClientError, (_, res) -> {
                NexonOpenApiErrorResponse errorResponse = new ObjectMapper().readValue(res.getBody(), NexonOpenApiErrorResponse.class);
                log.debug(">> Nexon Open API Error: {}", errorResponse);
                throw new NexonOpenApiException(errorResponse.error().errorCode(), errorResponse.error().message());
            })
            .build();
    }

    /**
     * 캐릭터 식별자(ocid)를 조회합니다.
     *
     * @param characterName 영웅(캐릭터) 명
     * @return {@link CharacterOcidResponse}
     */
    public CharacterOcidResponse getCharacterOcid(String characterName) {
        URI uri = heroesOcidUriBuilder.buildOcidUri(characterName);
        return executeRequest(uri, CharacterOcidResponse.class);
    }

    /**
     * 기본 정보를 조회합니다.
     *
     * @param ocid 캐릭터 식별자
     * @return {@link CharacterBasicResponse}
     */
    public CharacterBasicResponse getCharacterBasic(String ocid) {
        URI uri = heroesCharacterUriBuilder.buildCharacterBasic(ocid);
        return executeRequest(uri, CharacterBasicResponse.class);
    }

    /**
     * 타이틀/문양 보유 정보를 조회합니다.
     *
     * @param ocid 캐릭터 식별자
     * @return {@link CharacterTitleResponse}
     */
    public CharacterTitleResponse getCharacterTitle(String ocid) {
        URI uri = heroesCharacterUriBuilder.buildCharacterTitle(ocid);
        return executeRequest(uri, CharacterTitleResponse.class);
    }

    /**
     * 장착 타이틀/문양 정보를 조회합니다.
     *
     * @param ocid 캐릭터 식별자
     * @return {@link CharacterTitleEquipmentResponse}
     */
    public CharacterTitleEquipmentResponse getCharacterTitleEquipment(String ocid) {
        URI uri = heroesCharacterUriBuilder.buildCharacterTitleEquipment(ocid);
        return executeRequest(uri, CharacterTitleEquipmentResponse.class);
    }

    /**
     * 장착 아이템 정보를 조회합니다.
     *
     * @param ocid 캐릭터 식별자
     * @return {@link CharacterItemEquipmentResponse}
     */
    public CharacterItemEquipmentResponse getCharacterItemEquipment(String ocid) {
        URI uri = heroesCharacterUriBuilder.buildCharacterItemEquipment(ocid);
        return executeRequest(uri, CharacterItemEquipmentResponse.class);
    }

    /**
     * 능력치 정보를 조회합니다.
     *
     * @param ocid 캐릭터 식별자
     * @return {@link CharacterStatResponse}
     */
    public CharacterStatResponse getCharacterStat(String ocid) {
        URI uri = heroesCharacterUriBuilder.buildCharacterStat(ocid);
        return executeRequest(uri, CharacterStatResponse.class);
    }

    /**
     * 가입/신청한 길드 정보를 조회합니다.
     *
     * @param ocid 캐릭터 식별자
     * @return {@link CharacterGuildResponse}
     */
    public CharacterGuildResponse getCharacterGuild(String ocid) {
        URI uri = heroesCharacterUriBuilder.buildCharacterGuild(ocid);
        return executeRequest(uri, CharacterGuildResponse.class);
    }

    /**
     * 명예의 전당 랭킹 정보(공격력 기준)를 조회합니다.
     * 명예의 전당 랭킹은 매일 오전 9시 실시간 랭킹 순위를 기준으로 반영됩니다.
     * 공격력 200위까지 조회 가능
     *
     * @return {@link CharacterRankingResponse}
     */
    public CharacterRankingResponse getPhysicalRankingHallOfHonor() {
        URI uri = heroesRankingUriBuilder.buildRankingHallOfHonor(RankingType.PHYSICAL_DAMAGE);
        return executeRequest(uri, CharacterRankingResponse.class);
    }

    /**
     * 명예의 전당 랭킹 정보(마법 공격력 기준)를 조회합니다.
     * 명예의 전당 랭킹은 매일 오전 9시 실시간 랭킹 순위를 기준으로 반영됩니다.
     * 마법 공격력 100위까지 조회 가능
     *
     * @return {@link CharacterRankingResponse}
     */
    public CharacterRankingResponse getMagicRankingHallOfHonor() {
        URI uri = heroesRankingUriBuilder.buildRankingHallOfHonor(RankingType.MAGIC_DAMAGE);
        return executeRequest(uri, CharacterRankingResponse.class);
    }

    /**
     * 실시간 랭킹 정보를 조회(공격력 기준)합니다. 실시간 랭킹 정보는 1시간마다 반영됩니다.
     *
     * @param pageNo 페이지 번호 한 페이지 당 최대 500개의 데이터가 조회됩니다.
     * @return {@link CharacterRankingResponse}
     */
    public CharacterRankingResponse getPhysicalRankingRealTime(long pageNo) {
        URI uri = heroesRankingUriBuilder.buildRankingRealTime(RankingType.PHYSICAL_DAMAGE, pageNo);
        return executeRequest(uri, CharacterRankingResponse.class);
    }

    /**
     * 실시간 랭킹 정보를 조회(마법 공격력 기준)합니다. 실시간 랭킹 정보는 1시간마다 반영됩니다.
     *
     * @param pageNo 페이지 번호 한 페이지 당 최대 500개의 데이터가 조회됩니다.
     * @return {@link CharacterRankingResponse}
     */
    public CharacterRankingResponse getMagicRankingRealTime(long pageNo) {
        URI uri = heroesRankingUriBuilder.buildRankingRealTime(RankingType.MAGIC_DAMAGE, pageNo);
        return executeRequest(uri, CharacterRankingResponse.class);
    }

    private <T> T executeRequest(URI uri, Class<T> responseType) {
        rateLimiter.acquire();
        return heroesClient.get()
            .uri(uriBuilder -> uriBuilder.path(uri.getPath()).query(uri.getQuery()).build())
            .retrieve()
            .body(responseType);
    }
}

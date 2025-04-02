package com.project.heroes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.heroes.api.NexonHeroesApiClient;
import com.project.heroes.api.exception.NexonOpenApiException;
import com.project.heroes.api.properties.OpenNexonApiProperties;
import com.project.heroes.api.response.CharacterItemEquipmentResponse;
import com.project.heroes.api.uri.heroes.HeroesCharacterUriBuilder;
import com.project.heroes.api.uri.heroes.HeroesOcidUriBuilder;
import com.project.heroes.api.uri.heroes.HeroesRankingUriBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

@Slf4j
@SpringBootTest
@TestPropertySource(properties = {
    "logging.level.com.project.heroes=debug"
})
@EnableConfigurationProperties(value = {OpenNexonApiProperties.class})
public class HeroesFrameworkApplicationTests {

    @Configuration
    static class Config {

        @Autowired
        private OpenNexonApiProperties openNexonApiProperties;

        @Bean
        public HeroesCharacterUriBuilder heroesUriBuilder() {
            return new HeroesCharacterUriBuilder(openNexonApiProperties);
        }

        @Bean
        public HeroesRankingUriBuilder heroesRankingUriBuilder() {
            return new HeroesRankingUriBuilder(openNexonApiProperties);
        }

        @Bean
        public HeroesOcidUriBuilder heroesOcidUriBuilder() {
            return new HeroesOcidUriBuilder(openNexonApiProperties);
        }

        @Bean
        public NexonHeroesApiClient nexonHeroesApiClient() {
            return new NexonHeroesApiClient(openNexonApiProperties, heroesOcidUriBuilder(), heroesUriBuilder(), heroesRankingUriBuilder());
        }
    }

    @Autowired
    private NexonHeroesApiClient nexonHeroesApiClient;

    @Test
    public void contextLoads() throws JsonProcessingException {
        try {

            String ocid = "67763890ce845cb6d290b0427606de7ad023d2dd0ac74222ddfa4dae06511e1ddaw9";
            CharacterItemEquipmentResponse characterItemEquipmentResponse = nexonHeroesApiClient.getCharacterItemEquipment(
                ocid);

            log.debug(">>> {}", characterItemEquipmentResponse);
        } catch (NexonOpenApiException e) {
            log.debug(">>> {}", e.getErrorCode().name());
            log.error(e.getMessage());
        }

    }

}

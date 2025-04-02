package com.project.heroes.api.properties;

import com.project.heroes.support.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@PropertySource(value = {"classpath:nexon-heroes.yml"}, factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "open.api.nexon")
public class OpenNexonApiProperties {

    private Heroes heroes = new Heroes();

    @Getter
    @Setter
    public static class Heroes {

        private String domain;
        private String apiKey;
        private Paths paths;

    }

    @Getter
    @Setter
    public static class Paths {

        private PathParams id;
        private Character character = new Character();
        private Ranking ranking = new Ranking();
    }

    @Getter
    @Setter
    public static class Character {

        private PathParams basic;
        private PathParams title;
        private PathParams titleEquipment;
        private PathParams itemEquipment;
        private PathParams stat;
        private PathParams guild;
    }

    @Getter
    @Setter
    public static class Ranking {

        private PathParams hallOfHonor;
        private PathParams realTime;
    }

    @Getter
    @Setter
    public static class PathParams {

        private String path;
        private String[] params;
    }

}

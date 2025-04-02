package com.project.heroes.api.uri.heroes;

import com.project.heroes.api.properties.OpenNexonApiProperties;
import com.project.heroes.api.properties.OpenNexonApiProperties.Paths;
import com.project.heroes.api.uri.AbstractUriBuilder;
import java.net.URI;
import org.springframework.stereotype.Component;

@Component
public class HeroesOcidUriBuilder extends AbstractUriBuilder {

    private final Paths paths;

    public HeroesOcidUriBuilder(OpenNexonApiProperties openNexonApiProperties) {
        paths = openNexonApiProperties.getHeroes().getPaths();
    }

    public URI buildOcidUri(String characterName) {
        return buildUri(paths.getId(), characterName);
    }

}

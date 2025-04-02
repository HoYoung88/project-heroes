package com.project.heroes.api.uri.heroes;

import com.project.heroes.api.properties.OpenNexonApiProperties;
import com.project.heroes.api.properties.OpenNexonApiProperties.Character;
import com.project.heroes.api.uri.AbstractUriBuilder;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeroesCharacterUriBuilder extends AbstractUriBuilder {

    private final Character character;

    public HeroesCharacterUriBuilder(OpenNexonApiProperties openNexonApiProperties) {
        this.character = openNexonApiProperties.getHeroes().getPaths().getCharacter();
    }

    public URI buildCharacterBasic(String ocid) {
        return buildUri(character.getBasic(), ocid);
    }

    public URI buildCharacterTitle(String ocid) {
        return buildUri(character.getTitle(), ocid);
    }

    public URI buildCharacterTitleEquipment(String ocid) {
        return buildUri(character.getTitleEquipment(), ocid);
    }

    public URI buildCharacterItemEquipment(String ocid) {
        return buildUri(character.getItemEquipment(), ocid);
    }

    public URI buildCharacterStat(String ocid) {
        return buildUri(character.getStat(), ocid);
    }

    public URI buildCharacterGuild(String ocid) {
        return buildUri(character.getGuild(), ocid);
    }

}

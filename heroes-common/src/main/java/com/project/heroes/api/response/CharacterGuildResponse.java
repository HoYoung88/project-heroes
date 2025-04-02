package com.project.heroes.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CharacterGuildResponse(@JsonProperty("guild_name") String guildName) {

}

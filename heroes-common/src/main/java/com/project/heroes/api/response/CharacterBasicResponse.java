package com.project.heroes.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public record CharacterBasicResponse(@JsonProperty("character_name") String characterName,
                                     @JsonProperty("character_date_create") LocalDateTime characterDateCreate,
                                     @JsonProperty("character_date_last_login") LocalDateTime characterDateLastLogin,
                                     @JsonProperty("character_date_last_logout") LocalDateTime characterDateLastLogout,
                                     @JsonProperty("character_class_name") String characterClassName,
                                     @JsonProperty("character_gender") String characterGender,
                                     @JsonProperty("character_exp") Long characterExp,
                                     @JsonProperty("character_level") Long characterLevel,
                                     @JsonProperty("cairde_name") String cairdeName,
                                     @JsonProperty("title_count") Long titleCount,
                                     @JsonProperty("id_title_count") Long idTitleCount,
                                     @JsonProperty("total_title_count") Long totalTitleCount,
                                     @JsonProperty("title_stat") List<TitleStatOption> titleStats,
                                     @JsonProperty("skill_awakening") List<SkillAwakening> skillAwakenings,
                                     @JsonProperty("dress_point") DressPoint dressPoint) {

    public record TitleStatOption(@JsonProperty("stat_name") String statName,
                                  @JsonProperty("stat_value") String statValue) {

    }

    public record SkillAwakening(@JsonProperty("skill_name") String skillName,
                                 @JsonProperty("item_name") String itemName) {

    }

    public record DressPoint(@JsonProperty("total_point") Long totalPoint,
                             @JsonProperty("avatar_point") Long avatarPoint,
                             @JsonProperty("back_point") Long backPoint,
                             @JsonProperty("tail_point") Long tailPoint,
                             @JsonProperty("object_point") Long objectPoint) {

    }

}

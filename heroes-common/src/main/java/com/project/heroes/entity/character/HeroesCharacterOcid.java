package com.project.heroes.entity.character;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeroesCharacterOcid {

    @Id
    private String ocid;
    private String characterName;
    private LocalDateTime createdAt;

    @Builder
    public HeroesCharacterOcid(String ocid,
                               String characterName) {
        this.ocid = ocid;
        this.characterName = characterName;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "HeroesCharacterOcid{" +
            "ocid='" + ocid + '\'' +
            ", characterName='" + characterName + '\'' +
            '}';
    }
}

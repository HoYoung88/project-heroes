package com.project.heroes.entity.ranking;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RankingHallOfHonor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rankingType;
    private Long ranking;
    private String characterName;
    private Long score;
    private LocalDateTime createdAt;

    @Builder
    public RankingHallOfHonor(String rankingType, Long ranking, String characterName, Long score) {
        this.rankingType = rankingType;
        this.ranking = ranking;
        this.characterName = characterName;
        this.score = score;
        this.createdAt = LocalDateTime.now().withHour(9).withMinute(0).withSecond(0);
    }
}

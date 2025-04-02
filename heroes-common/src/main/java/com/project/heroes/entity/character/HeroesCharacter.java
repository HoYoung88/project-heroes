package com.project.heroes.entity.character;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Slf4j
public class HeroesCharacter {

    @Id
    private String ocid;
    private String characterName;
    private String characterGuildName;
    private LocalDateTime characterDateCreate;
    private LocalDateTime characterDateLastLogin;
    private LocalDateTime characterDateLastLogout;
    private String characterClassName;
    private String characterGender;
    private Long characterExp;
    private Long characterLevel;
    private String cairdeName;
    private Long titleCount;
    private Long idTitleCount;
    private Long totalTitleCount;

    private Long dressPointTotalPoint;
    private Long dressPointAvatarPoint;
    private Long dressPointBackPoint;
    private Long dressPointTailPoint;
    private Long dressPointObjectPoint;

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private HeroesCharacterStat stat;

    @OneToOne(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private HeroesCharacterTitleStat titleStat;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<HeroesCharacterSkillAwakening> skillAwakenings;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy
    private Set<HeroesCharacterItemEquipment> itemEquipments;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public HeroesCharacter(String ocid, String characterName,
                           String characterGuildName,
                           LocalDateTime characterDateCreate,
                           LocalDateTime characterDateLastLogin, LocalDateTime characterDateLastLogout,
                           String characterClassName, String characterGender, Long characterExp, Long characterLevel,
                           String cairdeName, Long titleCount, Long idTitleCount, Long totalTitleCount,
                           Long dressPointTotalPoint,
                           Long dressPointAvatarPoint,
                           Long dressPointBackPoint,
                           Long dressPointTailPoint,
                           Long dressPointObjectPoint) {
        this.ocid = ocid;
        this.characterName = characterName;
        this.characterGuildName = characterGuildName;
        this.characterDateCreate = characterDateCreate;
        this.characterDateLastLogin = characterDateLastLogin;
        this.characterDateLastLogout = characterDateLastLogout;
        this.characterClassName = characterClassName;
        this.characterGender = characterGender;
        this.characterExp = characterExp;
        this.characterLevel = characterLevel;
        this.cairdeName = cairdeName;
        this.titleCount = titleCount;
        this.idTitleCount = idTitleCount;
        this.totalTitleCount = totalTitleCount;
        this.skillAwakenings = new LinkedHashSet<>();
        this.dressPointTotalPoint = dressPointTotalPoint;
        this.dressPointAvatarPoint = dressPointAvatarPoint;
        this.dressPointBackPoint = dressPointBackPoint;
        this.dressPointTailPoint = dressPointTailPoint;
        this.dressPointObjectPoint = dressPointObjectPoint;
        this.itemEquipments = new LinkedHashSet<>();

    }

    public void addStat(HeroesCharacterStat stat) {
        this.stat = stat;
        stat.setCharacter(this);
    }

    public void addTitleStat(HeroesCharacterTitleStat titleStat) {
        this.titleStat = titleStat;
        titleStat.setCharacter(this);
    }

    public void addSkillAwakening(HeroesCharacterSkillAwakening skillAwakening) {
        this.skillAwakenings.add(skillAwakening);
        skillAwakening.setCharacter(this);
    }

    public void addItemEquipment(HeroesCharacterItemEquipment itemEquipment) {
        this.itemEquipments.add(itemEquipment);
        itemEquipment.setCharacter(this);
    }

    public Set<HeroesCharacterSkillAwakening> getSkillAwakenings() {
        return Collections.unmodifiableSet(skillAwakenings);
    }

    public Set<HeroesCharacterItemEquipment> getItemEquipments() {
        return Collections.unmodifiableSet(itemEquipments);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HeroesCharacter that = (HeroesCharacter) o;
        return Objects.equals(ocid, that.ocid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ocid);
    }

    @Override
    public String toString() {
        return "HeroesCharacter{" +
            "ocid='" + ocid + '\'' +
            ", characterName='" + characterName + '\'' +
            ", createdAt=" + createdAt +
            '}';
    }

}

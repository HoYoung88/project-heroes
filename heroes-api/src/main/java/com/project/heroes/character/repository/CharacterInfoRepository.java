package com.project.heroes.character.repository;

import com.project.heroes.entity.character.HeroesCharacter;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterInfoRepository extends JpaRepository<HeroesCharacter, Long> {

    @EntityGraph(attributePaths = {"stat", "titleStat", "skillAwakenings", "itemEquipments", "itemEquipments.itemOption"})
    @Query(value = """
        SELECT c
        FROM HeroesCharacter c
        WHERE c.ocid = :ocid
        """)
    Optional<HeroesCharacter> findByOcid(String ocid);
}

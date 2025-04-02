package com.project.heroes.character.repository;

import com.project.heroes.entity.character.HeroesCharacterOcid;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterOcidRepository extends JpaRepository<HeroesCharacterOcid, String> {

    Optional<HeroesCharacterOcid> findByCharacterName(String characterName);

}

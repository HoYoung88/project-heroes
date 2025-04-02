package com.project.heroes.character.repository;

import com.project.heroes.entity.character.HeroesCharacterOcid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterOcidRepository extends JpaRepository<HeroesCharacterOcid, Long> {

}

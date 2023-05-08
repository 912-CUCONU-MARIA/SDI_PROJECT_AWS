package com.example.lab1.repository;

import com.example.lab1.model.PlayerCharacter;
import com.example.lab1.model.PlayerCharacterItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerCharacterItemRepository extends JpaRepository<PlayerCharacterItem,Long> {

    Page<PlayerCharacterItem> findAllByPlayerCharacterId(Long playerCharacterId, Pageable pageable);

}

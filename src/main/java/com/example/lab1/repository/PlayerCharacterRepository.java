package com.example.lab1.repository;

import com.example.lab1.model.PlayerCharacter;
import com.example.lab1.model.PlayerCharacterItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter,Long> {

    Page<PlayerCharacter> findByLevelGreaterThan(Long level, Pageable pageable);
}
package com.example.lab1.repository;

import com.example.lab1.model.PlayerCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerCharacterRepository extends JpaRepository<PlayerCharacter,Long> {

    List<PlayerCharacter> findByLevelGreaterThan(Long level);
}
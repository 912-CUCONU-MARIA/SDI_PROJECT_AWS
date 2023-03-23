package com.example.lab1.repository;

import com.example.lab1.model.GameUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameUserRepository extends JpaRepository<GameUser,Long> {
}

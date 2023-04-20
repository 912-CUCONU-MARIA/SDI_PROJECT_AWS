package com.example.lab1.repository;

import com.example.lab1.model.GameUser;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameUserRepository extends JpaRepository<GameUser,Long> {

    @Query(value = "SELECT g.id, g.active_status, g.email_address, g.first_name, g.last_name, g.password, g.username, AVG(pc.level) AS average_level " +
            "FROM game_user g " +
            "JOIN player_character pc ON g.id = pc.game_user_id " +
            "GROUP BY g.id " +
            "ORDER BY average_level DESC",
            countQuery = "SELECT COUNT(DISTINCT g.id) FROM game_user g JOIN player_character pc ON g.id = pc.game_user_id",
            nativeQuery = true)
    Page<Object[]> getGameUsersOrderedByAverageLevelOfPlayerCharacters(Pageable pageable);

    

}

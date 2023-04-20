package com.example.lab1.repository;

import com.example.lab1.model.GameUser;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameUserRepository extends JpaRepository<GameUser,Long> {

    //v1
//    @Query(value = "SELECT g.id, g.active_status, g.email_address, g.first_name, g.last_name, g.password, g.username, AVG(pc.level) AS average_level " +
//            "FROM game_user g " +
//            "JOIN player_character pc ON g.id = pc.game_user_id " +
//            "GROUP BY g.id " +
//            "ORDER BY average_level DESC",
//            countQuery = "SELECT COUNT(DISTINCT g.id) FROM game_user g JOIN player_character pc ON g.id = pc.game_user_id",
//            nativeQuery = true)
//    Page<Object[]> getGameUsersOrderedByAverageLevelOfPlayerCharacters(Pageable pageable);

    @Query(value = "WITH avg_levels AS (" +
            "  SELECT pc.game_user_id, AVG(pc.level) OVER (PARTITION BY pc.game_user_id) as avg_level " +
            "  FROM player_character pc " +
            "  GROUP BY pc.game_user_id, pc.level " +
            ") " +
            "SELECT g.id, g.active_status, g.email_address, g.first_name, g.last_name, g.password, g.username, al.avg_level " +
            "FROM game_user g " +
            "JOIN avg_levels al ON g.id = al.game_user_id " +
            "GROUP BY g.id, al.avg_level " +
            "ORDER BY al.avg_level DESC",
            countQuery = "SELECT COUNT(DISTINCT g.id) FROM game_user g JOIN player_character pc ON g.id = pc.game_user_id",
            nativeQuery = true)
    Page<Object[]> getGameUsersOrderedByAverageLevelOfPlayerCharacters(Pageable pageable);


}

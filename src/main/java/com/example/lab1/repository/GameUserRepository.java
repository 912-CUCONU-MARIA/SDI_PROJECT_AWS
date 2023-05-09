package com.example.lab1.repository;

import com.example.lab1.model.GameUser;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameUserRepository extends JpaRepository<GameUser,Long> {

    //this is goood

//    @Query(value = "SELECT g.id, g.first_name, g.last_name, AVG(pc.level) AS average_level " +
//            "FROM game_user g " +
//            "JOIN player_character pc ON g.id = pc.game_user_id " +
//            "GROUP BY g.id " +
//            "ORDER BY average_level DESC",
//            countQuery = "SELECT COUNT(DISTINCT g.id) FROM game_user g LEFT JOIN player_character pc ON g.id = pc.game_user_id",
//            nativeQuery = true)
//
//    Page<Object[]> getGameUsersOrderedByAverageLevelOfPlayerCharacters(Pageable pageable);



    //change this to have less stuff in select
//    @Query(value = "SELECT g.id, g.first_name, g.last_name, AVG(pc.level) AS average_level " +
//            "FROM game_user g " +
//            "JOIN player_character pc ON g.id = pc.game_user_id " +
//            "GROUP BY g.id " +
//            "ORDER BY average_level DESC",
//            countQuery = "SELECT COUNT(DISTINCT g.id) FROM game_user g JOIN player_character pc ON g.id = pc.game_user_id",
//            nativeQuery = true)
//    Page<Object[]> getGameUsersOrderedByAverageLevelOfPlayerCharacters(Pageable pageable);

    Page<GameUser>findAllByOrderByAveragePlayerCharacterLevelAsc(Pageable pageable);

}

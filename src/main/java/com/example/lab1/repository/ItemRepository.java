package com.example.lab1.repository;

import com.example.lab1.model.Item;
import com.example.lab1.model.PlayerCharacter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Long> {

//    Optional<Item> findByitemNameanditemEffect(String itemName, String itemEffect);
        List<Item> findByItemNameContaining(String query);

//        @Query(value = "SELECT i.id, i.item_name, i.number_of_copies, AVG(pc.level) AS average_level "+
//                "FROM item i " +
//                "JOIN player_character_item pci ON i.id = pci.item_id " +
//                "JOIN player_character pc ON pci.playercharacter_id = pc.id " +
//                "GROUP BY i.id " +
//                "ORDER BY average_level DESC",
//                countQuery = "SELECT COUNT(DISTINCT i.id) FROM Item i JOIN player_character_item pci ON i.id = pci.item_id",
//                nativeQuery = true)
//        Page<Object[]> getItemsOrderedByAverageLevelOfPlayerCharacters(Pageable pageable);

        Page<Item> findAllByOrderByNumberOfCopiesDesc(Pageable pageable);

        Page<Item>findAllByOrderByIdAsc(Pageable pageable);

}

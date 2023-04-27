package com.example.lab1.repository;

import com.example.lab1.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query(value="SELECT i.id, i.itemName, i.itemRarity, i.itemType, i.itemLevel, COUNT(pci)" +
            "FROM Item i" +
            "LEFT JOIN player_character_item pci ON i.id=pci.item_id" +
            "GROUP BY i.id",
            countQuery = "SELECT COUNT(DISTINCT i.id) FROM item i LEFT JOIN player_character_item pci ON i.id=pci.item_id",
            nativeQuery = true)
    Page<Object[]> findAllWithPlayerCharacterItemCount(Pageable pageable);

    //no effect or description
}

package com.example.lab1.repository;

import com.example.lab1.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query("SELECT i.id, i.itemName, i.itemRarity, i.itemType, i.itemLevel, COUNT(pci) FROM Item i LEFT JOIN i.playerCharacterItemSet pci GROUP BY i")
    Page<Object[]> findAllWithPlayerCharacterItemCount(Pageable pageable);

    //no effect or description
}

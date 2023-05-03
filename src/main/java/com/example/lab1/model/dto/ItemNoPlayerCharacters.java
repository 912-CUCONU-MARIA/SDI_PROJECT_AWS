package com.example.lab1.model.dto;

import com.example.lab1.model.Item;
import lombok.Data;

@Data
public class ItemNoPlayerCharacters {

    private Long id;

    private String itemName;

    private String itemRarity;

    private String itemType;

    private Long itemLevel;

    private Long numberOfCopies;

    public static ItemNoPlayerCharacters from(Item item){

        ItemNoPlayerCharacters itemDto=new ItemNoPlayerCharacters();
        itemDto.setId(item.getId());
        itemDto.setItemName(item.getItemName());
        itemDto.setItemRarity(item.getItemRarity());
        itemDto.setItemType(item.getItemType());
        itemDto.setItemLevel(item.getItemLevel());
        itemDto.setNumberOfCopies(item.getNumberOfCopies());

        return itemDto;
    }

}

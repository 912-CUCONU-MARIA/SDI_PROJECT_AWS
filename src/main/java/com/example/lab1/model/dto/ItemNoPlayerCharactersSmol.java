package com.example.lab1.model.dto;

import com.example.lab1.model.Item;
import lombok.Data;

@Data
public class ItemNoPlayerCharactersSmol {

    private Long id;

    private String itemName;

    private Long itemLevel;

    private Long numberOfCopies;

    public static ItemNoPlayerCharactersSmol from(Item item){

        ItemNoPlayerCharactersSmol itemDto=new ItemNoPlayerCharactersSmol();
        itemDto.setId(item.getId());
        itemDto.setItemName(item.getItemName());
        itemDto.setItemLevel(item.getItemLevel());
        itemDto.setNumberOfCopies(item.getNumberOfCopies());

        return itemDto;
    }

}

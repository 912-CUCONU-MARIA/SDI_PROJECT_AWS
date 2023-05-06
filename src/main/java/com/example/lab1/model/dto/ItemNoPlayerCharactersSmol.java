package com.example.lab1.model.dto;

import com.example.lab1.model.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemNoPlayerCharactersSmol {

    private Long id;

    private String itemName;

    private Long itemLevel;

    private Long numberOfCopies;

    public static ItemNoPlayerCharactersSmol from(Item item){

//        ItemNoPlayerCharactersSmol itemDto=new ItemNoPlayerCharactersSmol();
//        itemDto.setId(item.getId());
//        itemDto.setItemName(item.getItemName());
//        itemDto.setItemLevel(item.getItemLevel());
//        itemDto.setNumberOfCopies(item.getNumberOfCopies());

        return ItemNoPlayerCharactersSmol.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .itemLevel(item.getItemLevel())
                .numberOfCopies(item.getNumberOfCopies())
                .build();
    }

}

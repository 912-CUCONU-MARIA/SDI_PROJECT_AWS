package com.example.lab1.model.dto;

import com.example.lab1.model.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemStatisticPlayerCharacterDto {

    private Long id;
    private String itemName;
    private String itemRarity;
    private Long itemLevel;
    private Long numberOfCopies;

    public static ItemStatisticPlayerCharacterDto from(Item item){
        return ItemStatisticPlayerCharacterDto.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .itemRarity(item.getItemRarity())
                .itemLevel(item.getItemLevel())
                .numberOfCopies(item.getNumberOfCopies())
                .build();
    }


}

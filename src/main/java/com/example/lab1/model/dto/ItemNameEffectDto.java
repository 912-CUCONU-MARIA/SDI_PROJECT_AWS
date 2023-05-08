package com.example.lab1.model.dto;

import com.example.lab1.model.Item;
import lombok.Data;

@Data
public class ItemNameEffectDto {

    private Long id;

    private String itemName;

    private String itemEffect;

    public static ItemNameEffectDto from(Item item) {

        ItemNameEffectDto itemDto = new ItemNameEffectDto();
        itemDto.setId(item.getId());
        itemDto.setItemName(item.getItemName());
        itemDto.setItemEffect(item.getItemEffect());
        return itemDto;
    }

}

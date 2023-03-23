package com.example.lab1.model.dto;

import com.example.lab1.model.Item;
import com.example.lab1.model.PlayerCharacterItem;
import lombok.Data;

@Data
public class ItemDtoWPlayerChItemObject extends ItemDto {

    private PlayerCharacterItemDto playerCharacterItemDto;

    public static ItemDtoWPlayerChItemObject from(Item item, PlayerCharacterItem playerCharacterItem){
        ItemDtoWPlayerChItemObject itemDto=new ItemDtoWPlayerChItemObject();
        itemDto.setId(item.getId());
        itemDto.setItemName(item.getItemName());
        itemDto.setItemRarity(item.getItemRarity());
        itemDto.setItemType(item.getItemType());
        itemDto.setItemEffect(item.getItemEffect());
        itemDto.setItemLevel(item.getItemLevel());
        itemDto.setPlayerCharacterItemDto(PlayerCharacterItemDto.from(playerCharacterItem));

        return itemDto;
    }

}

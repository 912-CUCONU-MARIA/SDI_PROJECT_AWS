package com.example.lab1.model.dto;

import com.example.lab1.model.Item;
import com.example.lab1.model.PlayerCharacterItem;
import lombok.Data;

@Data
public class ItemDtoWPlayerChItemObject {

    private Long id;

    private String itemName;

    private Boolean isEquipped;

    private Long upgradeTier;


    public static ItemDtoWPlayerChItemObject from(Item item, PlayerCharacterItem playerCharacterItem){
        ItemDtoWPlayerChItemObject itemDto=new ItemDtoWPlayerChItemObject();
        itemDto.setId(playerCharacterItem.getId()); //grija cu idul sa nu fie al itemului original!
        itemDto.setItemName(item.getItemName());
        //itemDto.setPlayerCharacterItemDto(PlayerCharacterItemDto.from(playerCharacterItem));
        itemDto.setIsEquipped(playerCharacterItem.getEquipped());
        itemDto.setUpgradeTier(playerCharacterItem.getUpgradeTier());


        return itemDto;
    }

}

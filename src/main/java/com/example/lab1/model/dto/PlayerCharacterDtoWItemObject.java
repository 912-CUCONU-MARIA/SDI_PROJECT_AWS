package com.example.lab1.model.dto;

import com.example.lab1.model.Item;
import com.example.lab1.model.PlayerCharacter;
import com.example.lab1.model.PlayerCharacterItem;
import lombok.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class PlayerCharacterDtoWItemObject {

//    private Long id;
//
//    private String pc_name;
//    private Long level;
//    private Long experience;
//    private Long classId;
//
//    private Long gameUserId;

    private PlayerCharacterDto playerCharacterDto;

    //private Set<ItemDto> playerCharacterItems;

    private Set<ItemDtoWPlayerChItemObject> itemDtoWPlayerChItemObjects;

    //private PlayerCharacterItemDto playerCharacterItemDto;


    //NEW DTO COMPOSED OF playerDTO AND list<itemDTO> + playerCharacterItemDto
    public static PlayerCharacterDtoWItemObject from(PlayerCharacter pc, Set<PlayerCharacterItem> playerCharacterItems){

        PlayerCharacterDtoWItemObject playerCharacterDtoWItemObject=new PlayerCharacterDtoWItemObject();

        playerCharacterDtoWItemObject.setPlayerCharacterDto(PlayerCharacterDto.from(pc));
        playerCharacterDtoWItemObject.setItemDtoWPlayerChItemObjects(
                playerCharacterItems.stream()
                                    .map(playerCharacterItem -> ItemDtoWPlayerChItemObject.from(playerCharacterItem.getItem(), playerCharacterItem))
                                    .collect(Collectors.toSet())
                                                                    );


        //playerCharacterDtoWItemObject.setPlayerCharacterItemsAndAdditionalInfo();

        //playerCharacterDtoWItemObject.setPlayerCharacterItems(items.stream().map(ItemDto::from).collect(Collectors.toSet()));
        //playerCharacterDtoWItemObject.setPlayerCharacterItemDto(PlayerCharacterItemDto.from(playerCharacterItem));

//        playerCharacterDto.setId(pc.getId());
//        playerCharacterDto.setPc_name(pc.getPc_name());
//        playerCharacterDto.setLevel(pc.getLevel());
//        playerCharacterDto.setExperience(pc.getExperience());
//        playerCharacterDto.setClassId(pc.getClassId());
//        playerCharacterDto.setGameUserId(pc.getGameUser().getId());
//        playerCharacterDto.setPlayerCharacterItems(items.stream().map(ItemDto::from).collect(Collectors.toSet()));



        return playerCharacterDtoWItemObject;

    }
    
}

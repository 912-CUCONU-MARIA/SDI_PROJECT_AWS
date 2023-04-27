package com.example.lab1.model.dto;
import com.example.lab1.model.Item;
import lombok.Data;

@Data
public class ItemDto {

    private Long id;

    private String itemName;

    private String itemRarity;

    private String itemType;

    private String itemEffect;

    private Long itemLevel;

    private String description;

    private Long numberOfPlayerCharactersOwning;

    public static ItemDto from(Item item){

        ItemDto itemDto=new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setItemName(item.getItemName());
        itemDto.setItemRarity(item.getItemRarity());
        itemDto.setItemType(item.getItemType());
        itemDto.setItemEffect(item.getItemEffect());
        itemDto.setItemLevel(item.getItemLevel());
        itemDto.setDescription(item.getDescription());
        itemDto.setNumberOfPlayerCharactersOwning((long) item.getPlayerCharacterItemSet().size());

        return itemDto;
    }

}

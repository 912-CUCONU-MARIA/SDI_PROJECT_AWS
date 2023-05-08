package com.example.lab1.model.dto;
import com.example.lab1.model.Item;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemDto {

    private Long id;

    @NotBlank(message = "itemName should not be blank")
    private String itemName;

    @NotBlank(message = "itemRarity should not be blank")
    private String itemRarity;

    @NotBlank(message = "itemType should not be blank")
    private String itemType;

    @NotBlank(message = "itemEffect should not be blank")
    private String itemEffect;

    @Min(value = 0, message = "Level should not be less than 0")
    private Long itemLevel;

    private String description;

    private Long numberOfCopies;

    public static ItemDto from(Item item){

        ItemDto itemDto=new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setItemName(item.getItemName());
        itemDto.setItemRarity(item.getItemRarity());
        itemDto.setItemType(item.getItemType());
        itemDto.setItemEffect(item.getItemEffect());
        itemDto.setItemLevel(item.getItemLevel());
        itemDto.setDescription(item.getDescription());
        itemDto.setNumberOfCopies((long) item.getNumberOfCopies());

        return itemDto;
    }

}

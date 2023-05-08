package com.example.lab1.model.dto;

import com.example.lab1.model.GameUser;
import com.example.lab1.model.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemAveragePlayerCharacterLevelDto {

    private Long id;
    private String itemName;
    private Long numberOfCopies;

    private Long averageLevel;

    public static ItemAveragePlayerCharacterLevelDto from(Item item, Long averageLevel){
        return ItemAveragePlayerCharacterLevelDto.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .numberOfCopies(item.getNumberOfCopies())
                .averageLevel(averageLevel)
                .build();
    }


}

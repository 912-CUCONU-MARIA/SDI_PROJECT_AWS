package com.example.lab1.model.dto;

import com.example.lab1.model.GameUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameUserAveragePlayerCharacterLevelDto {

    private Long id;

    private String firstName;
    private String lastName;
    private Long averageLevel;

    public static GameUserAveragePlayerCharacterLevelDto from(GameUser gameUser, Long averageLevel){
        return GameUserAveragePlayerCharacterLevelDto.builder()
                .id(gameUser.getId())
                .firstName(gameUser.getFirstName())
                .lastName(gameUser.getLastName())
                .averageLevel(averageLevel)
                .build();
    }


}

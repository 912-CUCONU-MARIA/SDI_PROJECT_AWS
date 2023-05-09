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
    private Float averageLevel;
    private Long numberOfPlayerCharacters;

    public static GameUserAveragePlayerCharacterLevelDto from(GameUser gameUser){
        return GameUserAveragePlayerCharacterLevelDto.builder()
                .id(gameUser.getId())
                .firstName(gameUser.getFirstName())
                .lastName(gameUser.getLastName())
                .averageLevel(gameUser.getAveragePlayerCharacterLevel())
                .numberOfPlayerCharacters((long) gameUser.getPlayerCharacterSet().size())
                .build();
    }


}

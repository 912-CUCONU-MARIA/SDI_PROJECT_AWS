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
//    private GameUserDtoMinified gameUserDtoMinified;
    private Long averageLevel;

    public static GameUserAveragePlayerCharacterLevelDto from(GameUser gameUser, Long averageLevel){
//        GameUserAveragePlayerCharacterLevelDto gameUserAveragePlayerCharacterLevelDto =new GameUserAveragePlayerCharacterLevelDto();
////        gameUserAveragePlayerCharacterLevelDto.setGameUserDtoMinified(GameUserDtoMinified.from(gameUser));
//        gameUserAveragePlayerCharacterLevelDto.setId(gameUser.getId());
//        gameUserAveragePlayerCharacterLevelDto.setFirstName(gameUser.getFirstName());
//        gameUserAveragePlayerCharacterLevelDto.setLastName(gameUser.getLastName());
//        gameUserAveragePlayerCharacterLevelDto.setAverageLevel(averageLevel);
//
//        return gameUserAveragePlayerCharacterLevelDto;
        return GameUserAveragePlayerCharacterLevelDto.builder()
                .id(gameUser.getId())
                .firstName(gameUser.getFirstName())
                .lastName(gameUser.getLastName())
                .averageLevel(averageLevel)
                .build();
    }


}

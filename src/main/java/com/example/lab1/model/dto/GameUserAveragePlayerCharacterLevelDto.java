package com.example.lab1.model.dto;

import com.example.lab1.model.GameUser;
import lombok.Data;

@Data
public class GameUserAveragePlayerCharacterLevelDto {

    private GameUserDto gameUserDto;
    private Long averageLevel;

    public static GameUserAveragePlayerCharacterLevelDto from(GameUser gameUser, Long averageLevel){
        GameUserAveragePlayerCharacterLevelDto gameUserAveragePlayerCharacterLevelDto =new GameUserAveragePlayerCharacterLevelDto();

        gameUserAveragePlayerCharacterLevelDto.setGameUserDto(GameUserDto.from(gameUser));
        gameUserAveragePlayerCharacterLevelDto.setAverageLevel(averageLevel);

        return gameUserAveragePlayerCharacterLevelDto;
    }


}

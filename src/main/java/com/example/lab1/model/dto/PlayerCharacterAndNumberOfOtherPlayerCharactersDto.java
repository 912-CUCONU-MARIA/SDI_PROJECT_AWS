package com.example.lab1.model.dto;

import com.example.lab1.model.PlayerCharacter;
import lombok.Data;

@Data
public class PlayerCharacterAndNumberOfOtherPlayerCharactersDto {

    private PlayerCharacterDto playerCharacterDto;
    private Long numberOfOtherPlayerCharacters;

    public static PlayerCharacterAndNumberOfOtherPlayerCharactersDto from(PlayerCharacter playerCharacter, Long numberOfOtherPlayerCharacters){
        PlayerCharacterAndNumberOfOtherPlayerCharactersDto playerCharacterAndNumberOfOtherPlayerCharactersDto =new PlayerCharacterAndNumberOfOtherPlayerCharactersDto();
        playerCharacterAndNumberOfOtherPlayerCharactersDto.setPlayerCharacterDto(PlayerCharacterDto.from(playerCharacter));
        playerCharacterAndNumberOfOtherPlayerCharactersDto.setNumberOfOtherPlayerCharacters(numberOfOtherPlayerCharacters);

        return playerCharacterAndNumberOfOtherPlayerCharactersDto;
    }

}

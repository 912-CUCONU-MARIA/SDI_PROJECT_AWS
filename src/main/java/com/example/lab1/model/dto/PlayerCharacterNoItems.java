package com.example.lab1.model.dto;

import com.example.lab1.model.PlayerCharacter;
import lombok.Data;

@Data
public class PlayerCharacterNoItems {

    private Long id;

    private String pc_name;
    private Long level;
    private Long experience;
    private Long classId;
    private Long gameUserId;
    private Long numberOfItemsOwned;

    public static PlayerCharacterNoItems from(PlayerCharacter pc){

        PlayerCharacterNoItems playerCharacterDto=new PlayerCharacterNoItems();
        playerCharacterDto.setId(pc.getId());
        playerCharacterDto.setPc_name(pc.getPc_name());
        playerCharacterDto.setLevel(pc.getLevel());
        playerCharacterDto.setExperience(pc.getExperience());
        playerCharacterDto.setClassId(pc.getClassId());
        playerCharacterDto.setGameUserId(pc.getGameUser().getId());
        playerCharacterDto.setNumberOfItemsOwned(pc.getNumberOfItemsOwned());

        return playerCharacterDto;

    }

}

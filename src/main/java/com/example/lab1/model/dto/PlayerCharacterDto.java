package com.example.lab1.model.dto;

import com.example.lab1.model.GameUser;
import com.example.lab1.model.PlayerCharacter;
import lombok.Builder;
import lombok.Data;

@Data

public class PlayerCharacterDto {

    private Long id;

    private String pc_name;
    private Long level;
    private Long experience;
    private Long classId;

    //private GameUserDto gameUserDto;
    private Long gameUserId;

    private Long numberOfItemsOwned;

    //this on we use for /playercharacters
    public static PlayerCharacterDto from(PlayerCharacter pc){

        PlayerCharacterDto playerCharacterDto=new PlayerCharacterDto();
        playerCharacterDto.setId(pc.getId());
        playerCharacterDto.setPc_name(pc.getPc_name());
        playerCharacterDto.setLevel(pc.getLevel());
        playerCharacterDto.setExperience(pc.getExperience());
        playerCharacterDto.setClassId(pc.getClassId());
        //playerCharacterDto.setGameUserDto(GameUserDto.from(pc.getGameUser()));
        playerCharacterDto.setGameUserId(pc.getGameUser().getId());
        playerCharacterDto.setNumberOfItemsOwned(pc.getNumberOfItemsOwned());

        return playerCharacterDto;

    }


}

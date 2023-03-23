package com.example.lab1.model.dto;


import com.example.lab1.model.PlayerCharacter;
import lombok.Data;

@Data
public class PlayerCharacterDtoWUserObject {

    private Long id;

    private String pc_name;
    private Long level;
    private Long experience;
    private Long classId;

    private GameUserDto gameUserDto;


    //this on we use for /playercharacters/id
    public static PlayerCharacterDtoWUserObject from(PlayerCharacter pc){

        PlayerCharacterDtoWUserObject playerCharacterDtoWUserObject=new PlayerCharacterDtoWUserObject();
        playerCharacterDtoWUserObject.setId(pc.getId());
        playerCharacterDtoWUserObject.setPc_name(pc.getPc_name());
        playerCharacterDtoWUserObject.setLevel(pc.getLevel());
        playerCharacterDtoWUserObject.setExperience(pc.getExperience());
        playerCharacterDtoWUserObject.setClassId(pc.getClassId());
        playerCharacterDtoWUserObject.setGameUserDto(GameUserDto.from(pc.getGameUser()));
        //playerCharacterDtoWUserObject.setGameUserId(pc.getGameUser().getId());

        return playerCharacterDtoWUserObject;

    }


}

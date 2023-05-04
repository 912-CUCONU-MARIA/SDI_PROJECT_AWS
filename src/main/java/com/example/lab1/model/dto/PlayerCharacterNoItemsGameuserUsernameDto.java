package com.example.lab1.model.dto;

import com.example.lab1.model.PlayerCharacter;
import lombok.Data;

@Data
public class PlayerCharacterNoItemsGameuserUsernameDto {

    private Long id;

    private String pc_name;
    private Long level;
    private Long experience;
    private Long classId;
    private String gameUserUsername;
    private Long numberOfItemsOwned;

    public static PlayerCharacterNoItemsGameuserUsernameDto from(PlayerCharacter pc){

        PlayerCharacterNoItemsGameuserUsernameDto playerCharacterDto=new PlayerCharacterNoItemsGameuserUsernameDto();
        playerCharacterDto.setId(pc.getId());
        playerCharacterDto.setPc_name(pc.getPc_name());
        playerCharacterDto.setLevel(pc.getLevel());
        playerCharacterDto.setExperience(pc.getExperience());
        playerCharacterDto.setClassId(pc.getClassId());
        playerCharacterDto.setGameUserUsername(pc.getGameUser().getUsername());
        playerCharacterDto.setNumberOfItemsOwned(pc.getNumberOfItemsOwned());

        return playerCharacterDto;

    }


}

package com.example.lab1.model.dto;

import com.example.lab1.model.GameUser;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GameUserDtoWPlayerChObject {
    private Long id;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private Boolean activeStatus;
    private String username;
    private String password;

    //private Set<Long> playerCharacterSetIdsDto;
    private Set<PlayerCharacterDto> playerCharacterDtos;

    //this one we use for /users/id
    public static GameUserDtoWPlayerChObject from(GameUser gameUser){
        GameUserDtoWPlayerChObject gameUserDtoWPlayerChObject=new GameUserDtoWPlayerChObject();
        gameUserDtoWPlayerChObject.setId(gameUser.getId());
        gameUserDtoWPlayerChObject.setFirstName(gameUser.getFirstName());
        gameUserDtoWPlayerChObject.setLastName(gameUser.getLastName());
        gameUserDtoWPlayerChObject.setEmailAddress(gameUser.getEmailAddress());
        gameUserDtoWPlayerChObject.setActiveStatus(gameUser.getActiveStatus());
        gameUserDtoWPlayerChObject.setUsername(gameUser.getUsername());
        gameUserDtoWPlayerChObject.setPassword(gameUser.getPassword());
        //gameUserDto.setPlayerCharacterSetIdsDto(gameUser.getPlayerCharacterSet().stream()
        //                                                        .map(PlayerCharacter::getId)
        //                                                        .collect(Collectors.toSet()));
        gameUserDtoWPlayerChObject.setPlayerCharacterDtos(gameUser.getPlayerCharacterSet().stream().map(PlayerCharacterDto::from).collect(Collectors.toSet()));


        return gameUserDtoWPlayerChObject;
    }
}

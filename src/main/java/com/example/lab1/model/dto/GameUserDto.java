package com.example.lab1.model.dto;

import com.example.lab1.model.GameUser;
import com.example.lab1.model.PlayerCharacter;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GameUserDto {

    private Long id;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private Boolean activeStatus;
    private String username;
    private String password;

    //private Set<Long> playerCharacterSetIdsDto;
    ////private Set<PlayerCharacterDto> playerCharacterDtos;
    private Long numberOfPlayerCharacters;

    //this one we use for /users
    public static GameUserDto from(GameUser gameUser){
        GameUserDto gameUserDto=new GameUserDto();
        gameUserDto.setId(gameUser.getId());
        gameUserDto.setFirstName(gameUser.getFirstName());
        gameUserDto.setLastName(gameUser.getLastName());
        gameUserDto.setEmailAddress(gameUser.getEmailAddress());
        gameUserDto.setActiveStatus(gameUser.getActiveStatus());
        gameUserDto.setUsername(gameUser.getUsername());
        gameUserDto.setPassword(gameUser.getPassword());
        //gameUserDto.setPlayerCharacterSetIdsDto(gameUser.getPlayerCharacterSet().stream()
        //                                                        .map(PlayerCharacter::getId)
        //                                                        .collect(Collectors.toSet()));
        ////gameUserDto.setPlayerCharacterDtos(gameUser.getPlayerCharacterSet().stream().map(PlayerCharacterDto::from).collect(Collectors.toSet()));
        gameUserDto.setNumberOfPlayerCharacters(gameUser.getNumberOfPlayerCharacters());

        return gameUserDto;
    }

}

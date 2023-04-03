package com.example.lab1.controller;

import com.example.lab1.exception.MyException;
import com.example.lab1.model.dto.GameUserAveragePlayerCharacterLevelDto;
import com.example.lab1.model.dto.GameUserDto;
import com.example.lab1.model.dto.GameUserDtoWPlayerChObject;
import com.example.lab1.service.GameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/gameusers")
public class GameUserController {

    private final GameUserService gameUserService;

    @Autowired
    public GameUserController(GameUserService gameUserService) {this.gameUserService = gameUserService;}

    //@GetMapping("/gameusers")
    //get only gameusers info
    @GetMapping()
    public ResponseEntity<List<GameUserDto>> getAllGameUsers(){
        //List<GameUser> gameUsers=gameUserService.getGameUsers();
        //List<GameUserDto> gameUserDtos=gameUsers.stream().map(GameUserDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(gameUserService.getGameUsersDto());
    }

    //get gameusers with all playercharacter info
    @GetMapping("/{id}")
    public ResponseEntity<GameUserDtoWPlayerChObject> getGameUserById(@PathVariable(value = "id") Long id) throws MyException {
        GameUserDtoWPlayerChObject gameUserDtoResponse=gameUserService.getGameUserDtoWPlayerChObjectByID(id);
        return ResponseEntity.ok(gameUserDtoResponse);
    }

    @PostMapping()
    public ResponseEntity<GameUserDto> createGameUser(@RequestBody final GameUserDto gameUserDtoRequest) {
        GameUserDto gameUserDtoResponse=gameUserService.addGameUser(gameUserDtoRequest);
        return ResponseEntity.ok(gameUserDtoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameUserDto> updateGameUser(@PathVariable(value = "id") Long id,
                                                   @RequestBody GameUserDto newGameUserDto) throws MyException {
        GameUserDto gameUserDtoResponse=gameUserService.updateGameUser(id,newGameUserDto);
        return ResponseEntity.ok(gameUserDtoResponse);
    }

    @DeleteMapping("/{id}")
    void deleteGameUser(@PathVariable(value = "id") Long id) throws MyException {
        gameUserService.deleteGameUserByID(id);
    }

    @DeleteMapping()
    void deleteAllGameUsers(){
        gameUserService.deleteGameUsers();
    }

    @GetMapping("/averageplayercharacterlevel")
    //player character and average
    //sorted(Comparator.comparing(author->author.getNoBooks()))
    //Show all Gameusers ordered by the average level of their PlayerCharacters
    public ResponseEntity<List<GameUserAveragePlayerCharacterLevelDto>> getGameUsersOrderedByAverageLevelOfPlayerCharacters(){
        return ResponseEntity.ok(gameUserService.getGameUsersOrderedByAverageLevelOfPlayerCharacters());
    }

}
/*
 */
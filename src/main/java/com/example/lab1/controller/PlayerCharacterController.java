package com.example.lab1.controller;

import com.example.lab1.exception.MyException;
import com.example.lab1.model.PlayerCharacter;
import com.example.lab1.model.dto.*;
import com.example.lab1.repository.PlayerCharacterRepository;
import com.example.lab1.service.PlayerCharacterService;
//import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
//@RequestMapping("/playercharacters")
@RequestMapping("/api")
public class PlayerCharacterController {

    private final PlayerCharacterService playerCharacterService;

    @Autowired//Spring injects the repositories when the service is created.
    public PlayerCharacterController(PlayerCharacterService playerCharacterService) {
        this.playerCharacterService = playerCharacterService;
    }

    @GetMapping("/playercharacters")
    public ResponseEntity<Page<PlayerCharacterNoItems>> getAllPlayerCharacters(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "direction", required = false) String direction) {

        Pageable pageable;
        if (sort != null && direction != null) {
            pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
        } else {
            pageable = PageRequest.of(page, size);
        }
        return ResponseEntity.ok(playerCharacterService.getPlayerCharactersDto(pageable));
    }

//    @GetMapping("/playercharacters")
//    //with user_id only
//    //@GetMapping()
//    public ResponseEntity<List<PlayerCharacterDto>> getAllPlayerCharacters(){
//        //List<PlayerCharacter> playerCharacters=playerCharacterService.getPlayerCharacters();
//        //List<PlayerCharacterDto> playerCharacterDtos=playerCharacters.stream().map(PlayerCharacterDto::from).collect(Collectors.toList());
//        return ResponseEntity.ok(playerCharacterService.getPlayerCharactersDto());
//    }

    //with user object
    @GetMapping("/playercharacters/{id}")
    public ResponseEntity<PlayerCharacterNoItemsGameuserUsernameDto> getPlayerCharacterById(@PathVariable(value = "id") Long id) throws MyException {
        PlayerCharacterNoItemsGameuserUsernameDto playerCharacterDtoResponse=playerCharacterService.getPlayerCharacterDtoByID(id);
        return ResponseEntity.ok(playerCharacterDtoResponse);
    }

    @GetMapping("/playercharacters/levelGreaterThan/{level}")
    public ResponseEntity<List<PlayerCharacterDto>> getPlayerCharacterBiggerLevel(@PathVariable(value = "level") Long level) throws MyException {
        return ResponseEntity.ok(playerCharacterService.getPlayerCharactersLevelGreaterThan(level));
    }

    @PostMapping("/playercharacters")
    public ResponseEntity<PlayerCharacterDto> createPlayerCharacter(@RequestBody final PlayerCharacterDto playerCharacterDtoRequest) {
        PlayerCharacterDto playerCharacterDtoResponse=playerCharacterService.addPlayerCharacter(playerCharacterDtoRequest);
        return ResponseEntity.ok(playerCharacterDtoResponse);
    }

    @PutMapping("/playercharacters/{id}")
    public ResponseEntity<PlayerCharacterDto> updatePlayerCharacter(@PathVariable(value = "id") Long id,
                                                   @RequestBody final PlayerCharacterDto newPlayerCharacterDto) throws MyException {

        PlayerCharacterDto playerCharacterDtoResponse=playerCharacterService.updatePlayerCharacter(id,newPlayerCharacterDto);
        return ResponseEntity.ok(playerCharacterDtoResponse);
    }

    @DeleteMapping("/playercharacters/{id}")
    void deletePlayerCharacter(@PathVariable(value = "id") Long id) throws MyException {
        playerCharacterService.deletePlayerCharacterByID(id);
    }

    @DeleteMapping("/playercharacters")
    void deleteAllPlayerCharacters(){
        playerCharacterService.deletePlayerCharacters();
    }

    @GetMapping("/playercharacters/sortednootherplayercharactersitems")
    public ResponseEntity<List<PlayerCharacterAndNumberOfOtherPlayerCharactersDto>> playerCharacterAndNumberOfOtherPlayerCharactersDtoResponseEntity(){
        return ResponseEntity.ok(playerCharacterService.getPlayerCharactersSortedByNumberOfOtherPlayerCharacters());
    }

    //.reversed for comparator order
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }

    @PutMapping("/playercharacters")
    void addCount(){
        playerCharacterService.setCount();
    }

}

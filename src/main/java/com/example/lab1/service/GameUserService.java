package com.example.lab1.service;

import com.example.lab1.exception.MyException;
import com.example.lab1.model.GameUser;
import com.example.lab1.model.PlayerCharacter;
import com.example.lab1.model.dto.GameUserAveragePlayerCharacterLevelDto;
import com.example.lab1.model.dto.GameUserDto;
import com.example.lab1.model.dto.GameUserDtoWPlayerChObject;
import com.example.lab1.repository.GameUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameUserService {

    private final GameUserRepository gameUserRepository;

    @Autowired//Spring injects the repositories when the service is created.
    public GameUserService(GameUserRepository gameUserRepository) {
        this.gameUserRepository = gameUserRepository;
    }

    public GameUserDto addGameUser(GameUserDto gameUserDto)
    {
        return GameUserDto.from(gameUserRepository.save(GameUser.from(gameUserDto)));
    }

    public List<GameUserDto> getGameUsersDto(){
        return gameUserRepository.findAll().stream().map(GameUserDto::from).collect(Collectors.toList());
    }

    public List<GameUserDtoWPlayerChObject> getGameUsersDtoWPlayerChObject(){
        return gameUserRepository.findAll().stream().map(GameUserDtoWPlayerChObject::from).collect(Collectors.toList());
    }

    public GameUserDto getGameUserDtoByID(Long id) throws MyException {
        return GameUserDto.from(gameUserRepository.findById(id).orElseThrow(() -> new MyException(id.toString())));
    }

    public GameUserDtoWPlayerChObject getGameUserDtoWPlayerChObjectByID(Long id) throws MyException {
        return GameUserDtoWPlayerChObject.from(gameUserRepository.findById(id).orElseThrow(() -> new MyException(id.toString())));
    }

    public void deleteGameUserByID(Long id) throws MyException {
     gameUserRepository.delete(gameUserRepository.findById(id).orElseThrow(() -> new MyException(id.toString())));
    }

    public void deleteGameUsers(){
        gameUserRepository.deleteAll();
    }

    @Transactional
    public GameUserDto updateGameUser(Long id, GameUserDto newGameUserDto) throws MyException {
        GameUser gameUserToUpdate=gameUserRepository.findById(id).orElseThrow(()-> new MyException(id.toString()));

        gameUserToUpdate.setFirstName(newGameUserDto.getFirstName());
        gameUserToUpdate.setLastName(newGameUserDto.getLastName());
        gameUserToUpdate.setEmailAddress(newGameUserDto.getEmailAddress());
        gameUserToUpdate.setActiveStatus(newGameUserDto.getActiveStatus());
        gameUserToUpdate.setUsername(newGameUserDto.getUsername());
        gameUserToUpdate.setPassword(newGameUserDto.getPassword());
        return GameUserDto.from(gameUserToUpdate);
    }


    public List<GameUserAveragePlayerCharacterLevelDto> getGameUsersOrderedByAverageLevelOfPlayerCharacters(){
        List<GameUser> gameUsers=gameUserRepository.findAll();
        return gameUsers.stream()
                .sorted(Comparator.comparing(gameUser->gameUser.getPlayerCharacterSet().stream()
                                                                                        .mapToLong(PlayerCharacter::getLevel)
                                                                                        .average()
                                                                                        .orElse(0)))
                            .map(gameUser -> GameUserAveragePlayerCharacterLevelDto.from(gameUser, (long) gameUser.getPlayerCharacterSet().stream()
                                                                                                                                        .mapToLong(PlayerCharacter::getLevel)
                                                                                                                                        .average()
                                                                                                                                        .orElse(0))
                                )
                .collect(Collectors.toList());
    }

}

        /*
        List<Pair<PlayerCharacter, Integer>> sortedPlayerCharacters =
    playerCharacters.stream()
        .map(pc -> new Pair<>(pc, countOtherPlayers(pc)))
        .sorted(Comparator.comparingInt(p -> p.getValue()))
        .collect(Collectors.toList());

private int countOtherPlayers(PlayerCharacter playerCharacter) {
    Set<PlayerCharacterItem> items = playerCharacter.getPlayerCharacterItems();
    Set<PlayerCharacter> otherPlayers = items.stream()
        .flatMap(item -> item.getItem().getPlayerCharacterItems().stream())
        .filter(pci -> !pci.getPlayerCharacter().equals(playerCharacter))
        .map(PlayerCharacterItem::getPlayerCharacter)
        .collect(Collectors.toSet());
    return otherPlayers.size();
         */


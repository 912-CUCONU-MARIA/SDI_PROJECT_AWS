package com.example.lab1.service;

import com.example.lab1.exception.MyException;
import com.example.lab1.model.GameUser;
import com.example.lab1.model.PlayerCharacter;
import com.example.lab1.model.PlayerCharacterItem;
import com.example.lab1.model.dto.*;
import com.example.lab1.repository.GameUserRepository;
import com.example.lab1.repository.PlayerCharacterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlayerCharacterService {

    private final PlayerCharacterRepository playerCharacterRepository;
    private final GameUserRepository gameUserRepository;

    @Autowired
    public PlayerCharacterService(PlayerCharacterRepository playerCharacterRepository, GameUserRepository gameUserRepository) {
        this.playerCharacterRepository = playerCharacterRepository;
        this.gameUserRepository = gameUserRepository;
    }

    @Transactional
    public PlayerCharacterDto addPlayerCharacter(PlayerCharacterDto playerCharacterDto){
        GameUser gameUser=gameUserRepository.findById(playerCharacterDto.getGameUserId()).orElseThrow();

        PlayerCharacter playerCharacter=PlayerCharacter.from(playerCharacterDto);
        playerCharacter.setGameUser(gameUser);
        PlayerCharacter addedPlayerCharacter=playerCharacterRepository.save(playerCharacter);

        gameUser.getPlayerCharacterSet().add(addedPlayerCharacter);
        gameUser.setNumberOfPlayerCharacters(gameUser.getNumberOfPlayerCharacters()+1);

        return PlayerCharacterDto.from(addedPlayerCharacter);
    }

    public Page<PlayerCharacterNoItems> getPlayerCharactersDto(Pageable pageable){
        return playerCharacterRepository.findAllByOrderByIdAsc(pageable)
                .map(PlayerCharacterNoItems::from);
    }

    public List<PlayerCharacterDto> getPlayerCharactersDto(){
        return playerCharacterRepository.findAll().stream().map(PlayerCharacterDto::from).collect(Collectors.toList());
    }

    public List<PlayerCharacterDtoWUserObject> getPlayerCharacterDtoWUserObject(){
        return playerCharacterRepository.findAll().stream().map(PlayerCharacterDtoWUserObject::from).collect(Collectors.toList());
    }

    public PlayerCharacterNoItemsGameuserUsernameDto getPlayerCharacterDtoByID(Long id) throws MyException {
        return PlayerCharacterNoItemsGameuserUsernameDto.from(playerCharacterRepository.findById(id).orElseThrow(() -> new MyException(id.toString())));
    }

    public PlayerCharacterDtoWUserObject getPlayerCharacterDtoWUserObjectByID(Long id) throws MyException {
        return PlayerCharacterDtoWUserObject.from(playerCharacterRepository.findById(id).orElseThrow(() -> new MyException(id.toString())));
    }

    public Page<PlayerCharacterNoItems> getPlayerCharactersLevelGreaterThan(Long level, Pageable pageable){
        return playerCharacterRepository.findByLevelGreaterThan(level,pageable)
                .map(PlayerCharacterNoItems::from);
    }

    @Transactional
    public void deletePlayerCharacterByID(Long id) throws MyException {
        PlayerCharacter playerCharacter=playerCharacterRepository.findById(id).orElseThrow();

        playerCharacterRepository.delete(playerCharacterRepository.findById(id).orElseThrow(() -> new MyException(id.toString())));

        GameUser gameUser=playerCharacter.getGameUser();
        gameUser.getPlayerCharacterSet().remove(playerCharacter);
        gameUser.setNumberOfPlayerCharacters(gameUser.getNumberOfPlayerCharacters()-1);

    }

    public void deletePlayerCharacters(){
        playerCharacterRepository.deleteAll();
    }

    @Transactional
    public PlayerCharacterDto updatePlayerCharacter(Long id, PlayerCharacterDto newPlayerCharacterDto) throws MyException {
        PlayerCharacter playerCharacterToUpdate=playerCharacterRepository.findById(id).orElseThrow(() -> new MyException("Invalid player id "+id.toString()));

        playerCharacterToUpdate.setPc_name(newPlayerCharacterDto.getPc_name());
        playerCharacterToUpdate.setLevel(newPlayerCharacterDto.getLevel());
        playerCharacterToUpdate.setExperience(newPlayerCharacterDto.getExperience());
        playerCharacterToUpdate.setClassId(newPlayerCharacterDto.getClassId());
        //GameUser gameUser=gameUserRepository.findById(newPlayerCharacterDto.getGameUserId()).orElseThrow(() -> new MyException("Invalid gameUser id"+newPlayerCharacterDto.getGameUserId().toString()));
        //playerCharacterToUpdate.setGameUser(gameUser);

        return PlayerCharacterDto.from(playerCharacterToUpdate);
    }

    //simpler show all playercharacters sorted by their number of items
    //the dto name is scuffed, I just reused it from the below function
    public List<PlayerCharacterAndNumberOfOtherPlayerCharactersDto> getPlayerCharacterSortedByNumberOfItems(){
        List<PlayerCharacterAndNumberOfOtherPlayerCharactersDto> playerCharacterSortedByNumberOfItemsDtos=playerCharacterRepository.findAll()
                .stream()
                .map(pc->PlayerCharacterAndNumberOfOtherPlayerCharactersDto.from(pc, (long) pc.getPlayerCharacterItemSet().size()))
                .sorted(Comparator.comparing(PlayerCharacterAndNumberOfOtherPlayerCharactersDto::getNumberOfOtherPlayerCharacters))
                .collect(Collectors.toList());

        return playerCharacterSortedByNumberOfItemsDtos;
    }


    //show all playercharacters sorted by number of other playercharacters its items belong to
    public List<PlayerCharacterAndNumberOfOtherPlayerCharactersDto> getPlayerCharactersSortedByNumberOfOtherPlayerCharacters(){

        List<PlayerCharacterAndNumberOfOtherPlayerCharactersDto> playerCharacterAndNumberOfOtherPlayerCharactersDtos=playerCharacterRepository.findAll()
                .stream()
                .map(pc->PlayerCharacterAndNumberOfOtherPlayerCharactersDto.from(pc,countOtherPlayers(pc)))
                .sorted(Comparator.comparing(p->p.getNumberOfOtherPlayerCharacters()))
                .collect(Collectors.toList());

        Collections.reverse(playerCharacterAndNumberOfOtherPlayerCharactersDtos);
        return playerCharacterAndNumberOfOtherPlayerCharactersDtos;

        //get for each player its items; for each item count how many other playercharacters have them; associate that number with the player character;
        //sort the list of players by that

    }

    public static Long countOtherPlayers(PlayerCharacter playerCharacter){
        Set<PlayerCharacterItem> playerCharacterItems=playerCharacter.getPlayerCharacterItemSet();
        Set<PlayerCharacter> otherPlayers=playerCharacterItems.stream()
                                                              .flatMap(item-> item.getItem().getPlayerCharacterItemSet().stream())
                                                              .filter(pci->!pci.getPlayerCharacter().equals(playerCharacter))
                                                              .map(PlayerCharacterItem::getPlayerCharacter)
                                                              .collect(Collectors.toSet());
        return (long)otherPlayers.size();

    }

    public void setCount(){
        playerCharacterRepository.findAll().forEach(playerCharacter -> {
            playerCharacter.setNumberOfItemsOwned((long) playerCharacter.getPlayerCharacterItemSet().size());
            playerCharacterRepository.save(playerCharacter);
        });
    }


}

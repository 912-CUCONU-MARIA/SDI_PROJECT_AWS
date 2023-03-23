package com.example.lab1.service;

import com.example.lab1.exception.MyException;
import com.example.lab1.model.Item;
import com.example.lab1.model.PlayerCharacter;
import com.example.lab1.model.PlayerCharacterItem;
import com.example.lab1.model.dto.PlayerCharacterDto;
import com.example.lab1.model.dto.PlayerCharacterDtoWItemObject;
import com.example.lab1.model.dto.PlayerCharacterItemDto;
import com.example.lab1.repository.ItemRepository;
import com.example.lab1.repository.PlayerCharacterItemRepository;
import com.example.lab1.repository.PlayerCharacterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayerCharacterItemService {

    private final PlayerCharacterRepository playerCharacterRepository;
    private final PlayerCharacterItemRepository playerCharacterItemRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public PlayerCharacterItemService(PlayerCharacterRepository playerCharacterRepository, PlayerCharacterItemRepository playerCharacterItemRepository, ItemRepository itemRepository) {
        this.playerCharacterRepository = playerCharacterRepository;
        this.playerCharacterItemRepository = playerCharacterItemRepository;
        this.itemRepository = itemRepository;
    }

    public PlayerCharacterItemDto addItemToPlayerCharacter(PlayerCharacterItemDto playerCharacterItemDto,Long idPlayerCharacter, Long idItem){

        PlayerCharacter playerCharacter=playerCharacterRepository.findById(idPlayerCharacter).orElseThrow();
        Item item=itemRepository.findById(idItem).orElseThrow();

        PlayerCharacterItem playerCharacterItem=PlayerCharacterItem.from(playerCharacterItemDto);

        playerCharacterItem.setPlayerCharacter(playerCharacter);
        playerCharacterItem.setItem(item);

        PlayerCharacterItem addedPlayerCharacterItem=playerCharacterItemRepository.save(playerCharacterItem);
        playerCharacter.getPlayerCharacterItemSet().add(addedPlayerCharacterItem);
        item.getPlayerCharacterItemSet().add(addedPlayerCharacterItem);

        return PlayerCharacterItemDto.from(addedPlayerCharacterItem);

    }

    public List<PlayerCharacterItemDto> getPlayerCharacterItemDtos(){
        return playerCharacterItemRepository.findAll().stream().map(PlayerCharacterItemDto::from).collect(Collectors.toList());
    }

    /*
    show items that player 1 has:
GET /players/1/items //show player1 and his items ->RETURN A NEW DTO COMPOSED OF basic playerDTO attr AND list<itemDTO>
	go through its list<c_item>, get their associated items in a list<Items>
     */

    public PlayerCharacterDtoWItemObject getPlayerCharacterAllItems(Long idPlayerCharacter){
        PlayerCharacter playerCharacter=playerCharacterRepository.findById(idPlayerCharacter).orElseThrow();
        Set<PlayerCharacterItem> playerCharacterItems=playerCharacter.getPlayerCharacterItemSet();

        //Set<Item> items=playerCharacter.getPlayerCharacterItemSet().stream().map(PlayerCharacterItem::getItem).collect(Collectors.toSet());

        return PlayerCharacterDtoWItemObject.from(playerCharacter,playerCharacterItems);
    }

    @Transactional
    public PlayerCharacterItemDto updateItemOfPlayerCharacter(Long playerChId, Long pciId, PlayerCharacterItemDto newPlayerCharacterItemDto) throws MyException {
        PlayerCharacterItem playerCharacterItemToUpdate = playerCharacterItemRepository.findById(pciId).orElseThrow(() -> new MyException("Invalid playercharacteritem id "+pciId.toString()));

        if(Objects.equals(playerChId, playerCharacterItemToUpdate.getPlayerCharacter().getId()))
        {
            playerCharacterItemToUpdate.setEquipped(newPlayerCharacterItemDto.getIsEquipped());
            playerCharacterItemToUpdate.setUpgradeTier(newPlayerCharacterItemDto.getUpgradeTier());
        }

        //System.out.println(playerCharacterItemRepository);

        return PlayerCharacterItemDto.from(playerCharacterItemToUpdate);

    }

    public void deleteItemOfPlayerCharacter(Long playerChId,Long pciId) throws MyException {
        PlayerCharacterItem playerCharacterItemToDelete=playerCharacterItemRepository.findById(pciId).orElseThrow(() -> new MyException("Invalid playercharacteritem id "+pciId.toString()));
        if(Objects.equals(playerChId, playerCharacterItemToDelete.getPlayerCharacter().getId()))
            playerCharacterItemRepository.delete(playerCharacterItemToDelete);
    }

}

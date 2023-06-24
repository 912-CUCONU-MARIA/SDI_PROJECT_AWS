package com.example.lab1.service;

import com.example.lab1.exception.MyException;
import com.example.lab1.model.Item;
import com.example.lab1.model.PlayerCharacter;
import com.example.lab1.model.PlayerCharacterItem;
import com.example.lab1.model.dto.*;
import com.example.lab1.repository.ItemRepository;
import com.example.lab1.repository.PlayerCharacterItemRepository;
import com.example.lab1.repository.PlayerCharacterRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Transactional
    public PlayerCharacterItemDto addItemToPlayerCharacter(PlayerCharacterItemDto playerCharacterItemDto,Long idPlayerCharacter, Long idItem){

        PlayerCharacter playerCharacter=playerCharacterRepository.findById(idPlayerCharacter).orElseThrow();
        Item item=itemRepository.findById(idItem).orElseThrow();

        PlayerCharacterItem playerCharacterItem=PlayerCharacterItem.from(playerCharacterItemDto);

        playerCharacterItem.setPlayerCharacter(playerCharacter);
        playerCharacterItem.setItem(item);

        PlayerCharacterItem addedPlayerCharacterItem=playerCharacterItemRepository.save(playerCharacterItem);
        playerCharacter.getPlayerCharacterItemSet().add(addedPlayerCharacterItem);
        playerCharacter.setNumberOfItemsOwned(playerCharacter.getNumberOfItemsOwned()+1);

        item.getPlayerCharacterItemSet().add(addedPlayerCharacterItem);
        item.setNumberOfCopies(item.getNumberOfCopies()+1);//

        return PlayerCharacterItemDto.from(addedPlayerCharacterItem);

    }

//    @Transactional
//    public PlayerCharacterItemDto addItemToPlayerCharacter(PlayerCharacterItemDto playerCharacterItemDto,Long idPlayerCharacter, String itemName){
//
//        PlayerCharacter playerCharacter=playerCharacterRepository.findById(idPlayerCharacter).orElseThrow();
//        Item item=itemRepository.findByName(itemName).orElseThrow();
//
//        PlayerCharacterItem playerCharacterItem=PlayerCharacterItem.from(playerCharacterItemDto);
//
//        playerCharacterItem.setPlayerCharacter(playerCharacter);
//        playerCharacterItem.setItem(item);
//
//        PlayerCharacterItem addedPlayerCharacterItem=playerCharacterItemRepository.save(playerCharacterItem);
//        playerCharacter.getPlayerCharacterItemSet().add(addedPlayerCharacterItem);
//        playerCharacter.setNumberOfItemsOwned(playerCharacter.getNumberOfItemsOwned()+1);
//
//        item.getPlayerCharacterItemSet().add(addedPlayerCharacterItem);
//        item.setNumberOfCopies(item.getNumberOfCopies()+1);//
//
//        return PlayerCharacterItemDto.from(addedPlayerCharacterItem);
//
//    }

    public List<PlayerCharacterItemDto> addItemsToPlayerCharacter(List<PlayerCharacterItemDto> playerCharacterItemsDtos,Long idPlayerCharacter)
    {
        List<PlayerCharacterItemDto> returnedItemsList=new ArrayList<>();
        for(PlayerCharacterItemDto pcid:playerCharacterItemsDtos)
        {
            PlayerCharacterItem playerCharacterItem=PlayerCharacterItem.from(pcid);
            PlayerCharacterItemDto playerCharacterItemDto=PlayerCharacterItemDto.from(playerCharacterItem);
            PlayerCharacterItemDto playerCharacterItemDtoSaved=this.addItemToPlayerCharacter(playerCharacterItemDto, idPlayerCharacter,pcid.getId());
            //playerCharacterItemDtoSaved.setId(pcid.getId()); in case I want to see like in Response body
            returnedItemsList.add(playerCharacterItemDtoSaved);
        }
        return returnedItemsList;
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

    public Page<ItemDtoWPlayerChItemObject> getPlayerCharacterAllItems(Long playerChId, Pageable pageable) {
        Page<PlayerCharacterItem> playerCharacterItems = playerCharacterItemRepository.findAllByPlayerCharacterId(playerChId, pageable);
        return playerCharacterItems.map(playerCharacterItem -> ItemDtoWPlayerChItemObject.from(playerCharacterItem.getItem(), playerCharacterItem));
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

    @Transactional
    public void deleteItemOfPlayerCharacter(Long playerChId,Long pciId) throws MyException {
        PlayerCharacterItem playerCharacterItemToDelete=playerCharacterItemRepository.findById(pciId).orElseThrow(() -> new MyException("Invalid playercharacteritem id "+pciId.toString()));
        if(Objects.equals(playerChId, playerCharacterItemToDelete.getPlayerCharacter().getId())) {
            playerCharacterItemRepository.delete(playerCharacterItemToDelete);
            //the count deletion is handled in the db
        }
    }

}

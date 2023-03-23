package com.example.lab1.controller;


import com.example.lab1.exception.MyException;
import com.example.lab1.model.dto.PlayerCharacterDtoWItemObject;
import com.example.lab1.model.dto.PlayerCharacterItemDto;
import com.example.lab1.service.PlayerCharacterItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class PlayerCharacterItemController {

/*
POST /players/1/items/1 and in json specify additional attr: is_equipped and upgrade_tier
//create c_item with item1 and player1, additional attr and id=123
//add to list<c_item> in player 1 the c_item with id=123
//add to list<c_item> in item 1 the c_item with id=123
 */
        private final PlayerCharacterItemService playerCharacterItemService;

        @Autowired
        public PlayerCharacterItemController(PlayerCharacterItemService playerCharacterItemService){this.playerCharacterItemService=playerCharacterItemService;}

        @GetMapping("/playercharacteritems")
        public ResponseEntity<List<PlayerCharacterItemDto>> getAllPlayerCharacterItems(){
            return ResponseEntity.ok(playerCharacterItemService.getPlayerCharacterItemDtos());
        }

        @GetMapping("/playercharacters/{playerChId}/items")
        public ResponseEntity<PlayerCharacterDtoWItemObject> getPlayerCharacterAllItems(@PathVariable(value="playerChId") Long playerChId)
        {
            PlayerCharacterDtoWItemObject playerCharacterDtoWItemObjectResponse=playerCharacterItemService.getPlayerCharacterAllItems(playerChId);
            return ResponseEntity.ok(playerCharacterDtoWItemObjectResponse);
        }

        @PostMapping("/playercharacters/{playerChId}/items/{itemId}")
        public ResponseEntity<PlayerCharacterItemDto> addItemToPlayerCharacter(@PathVariable(value = "playerChId") Long playerChId,@PathVariable(value="itemId") Long itemId, @RequestBody final PlayerCharacterItemDto playerCharacterItemDtoRequest){
            PlayerCharacterItemDto playerCharacterItemDtoResponse=playerCharacterItemService.addItemToPlayerCharacter(playerCharacterItemDtoRequest,playerChId,itemId);
            return ResponseEntity.ok(playerCharacterItemDtoResponse);
        }

        @PutMapping("playercharacters/{playerChId}/items/{pciId}")
        public ResponseEntity<PlayerCharacterItemDto> updateItemOfPlayerCharacter(@PathVariable(value = "playerChId") Long playerChId,@PathVariable(value = "pciId") Long pciId, @RequestBody final PlayerCharacterItemDto playerCharacterItemDtoRequest) throws MyException {
            PlayerCharacterItemDto playerCharacterItemDtoResponse=playerCharacterItemService.updateItemOfPlayerCharacter(playerChId,pciId,playerCharacterItemDtoRequest);
            return ResponseEntity.ok(playerCharacterItemDtoResponse);
        }

        @DeleteMapping("playercharacters/{playerChId}/items/{pciId}")
        void deleteItemOfPlayerCharacter(@PathVariable(value = "playerChId") Long playerChId, @PathVariable(value = "pciId") Long pciId) throws MyException {
             playerCharacterItemService.deleteItemOfPlayerCharacter(playerChId,pciId);
        }


}

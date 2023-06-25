package com.example.lab1.controller;


import com.example.lab1.exception.MyException;
import com.example.lab1.model.dto.ItemDtoWPlayerChItemObject;
import com.example.lab1.model.dto.ItemNoPlayerCharacters;
import com.example.lab1.model.dto.PlayerCharacterDtoWItemObject;
import com.example.lab1.model.dto.PlayerCharacterItemDto;
import com.example.lab1.service.PlayerCharacterItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8080", "http://localhost:4200", "http://localhost:8080/swagger-ui.html"})
@RestController
@RequestMapping("/api")
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

//        @GetMapping("/playercharacters/{playerChId}/items")
//        public ResponseEntity<PlayerCharacterDtoWItemObject> getPlayerCharacterAllItems(@PathVariable(value="playerChId") Long playerChId)
//        {
//            PlayerCharacterDtoWItemObject playerCharacterDtoWItemObjectResponse=playerCharacterItemService.getPlayerCharacterAllItems(playerChId);
//            return ResponseEntity.ok(playerCharacterDtoWItemObjectResponse);
//        }
        @GetMapping("/playercharacters/{playerChId}/items")
        public ResponseEntity<Page<ItemDtoWPlayerChItemObject>> getPlayerCharacterAllItems(
                @PathVariable(value="playerChId") Long playerChId,
                @RequestParam(name = "page", defaultValue = "0") int page,
                @RequestParam(name = "size", defaultValue = "10") int size,
                @RequestParam(name = "sort",required = false) String sort,
                @RequestParam(name = "direction",required = false) String direction) {

            Pageable pageable;
            if (sort != null && direction != null) {
                pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), sort);
            } else {
                pageable = PageRequest.of(page, size);
            }
            return ResponseEntity.ok(playerCharacterItemService.getPlayerCharacterAllItems(playerChId,pageable));
        }


        @PostMapping("/playercharacters/{playerChId}/items/{itemId}")
        public ResponseEntity<PlayerCharacterItemDto> addItemToPlayerCharacter(@PathVariable(value = "playerChId") Long playerChId,@PathVariable(value="itemId") Long itemId, @RequestBody final PlayerCharacterItemDto playerCharacterItemDtoRequest){
            PlayerCharacterItemDto playerCharacterItemDtoResponse=playerCharacterItemService.addItemToPlayerCharacter(playerCharacterItemDtoRequest,playerChId,itemId);
            return ResponseEntity.ok(playerCharacterItemDtoResponse);
        }

//        @PostMapping("/playercharacters/{playerChId}/items/{itemName}")
//        public ResponseEntity<PlayerCharacterItemDto> addItemToPlayerCharacter(@PathVariable(value = "playerChId") Long playerChId,@PathVariable(value="itemName") String itemName, @RequestBody final PlayerCharacterItemDto playerCharacterItemDtoRequest){
//            PlayerCharacterItemDto playerCharacterItemDtoResponse=playerCharacterItemService.addItemToPlayerCharacter(playerCharacterItemDtoRequest,playerChId,itemName);
//            return ResponseEntity.ok(playerCharacterItemDtoResponse);
//        }


        //add items to player character in bulk
        //id item, its extra stuff from intermediary table
        //id item, its extra stuff from intermediary table
        @PostMapping("/playercharacters/{playerChId}/items")
        public ResponseEntity<List<PlayerCharacterItemDto>> addItemsToPlayerCharacter(@PathVariable(value = "playerChId") Long playerChId, @RequestBody final List<PlayerCharacterItemDto> playerCharacterItemsDtoRequest){
            //PlayerCharacterItemDto playerCharacterItemDtoResponse=playerCharacterItemService.addItemToPlayerCharacter(playerCharacterItemDtoRequest,playerChId,itemId);
            List<PlayerCharacterItemDto> playerCharacterItemsDtoResponse=playerCharacterItemService.addItemsToPlayerCharacter(playerCharacterItemsDtoRequest,playerChId);
            return ResponseEntity.ok(playerCharacterItemsDtoResponse);
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

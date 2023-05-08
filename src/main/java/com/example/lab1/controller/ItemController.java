package com.example.lab1.controller;

import com.example.lab1.exception.MyException;
import com.example.lab1.model.dto.*;
import com.example.lab1.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {this.itemService=itemService;}

    @GetMapping()
    public ResponseEntity<Page<ItemNoPlayerCharacters>> getAllItems(
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
        return ResponseEntity.ok(itemService.getItemsDto(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable(value = "id") Long id) throws MyException {
        ItemDto itemDtoResponse=itemService.getItemDtoByID(id);
        return ResponseEntity.ok(itemDtoResponse);
    }

    @PostMapping()
    public ResponseEntity<ItemDto> createItem(@RequestBody @Valid final ItemDto itemDtoRequest) {
        ItemDto itemDtoResponse=itemService.addItem(itemDtoRequest);
        return ResponseEntity.ok(itemDtoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable(value = "id") Long id,
                                                      @RequestBody ItemDto newItemDto) throws MyException {
        ItemDto itemDtoResponse=itemService.updateItem(id,newItemDto);
        return ResponseEntity.ok(itemDtoResponse);
    }

    @DeleteMapping("/{id}")
    void deleteItem(@PathVariable(value = "id") Long id) throws MyException {
        itemService.deleteItemByID(id);
    }

    @DeleteMapping()
    void deleteAllItems(){itemService.deleteItems();}

    @PutMapping()
    void addCount(){
        itemService.setCount();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemNameEffectDto>> searchItems(@RequestParam String name) {
        List<ItemNameEffectDto> items = itemService.searchItemsByName(name);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/averageplayercharacterlevel")
    //sorted(Comparator.comparing(author->author.getNoBooks()))
    //Show all Items ordered by the average level of the PlayerCharacters that own them
    public ResponseEntity<Page<ItemAveragePlayerCharacterLevelDto>> getItemsOrderedByAverageLevelOfPlayerCharacters(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(itemService.getItemsOrderedByAverageLevelOfPlayerCharacters(pageable));
    }

}

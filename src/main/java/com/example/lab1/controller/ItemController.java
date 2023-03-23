package com.example.lab1.controller;

import com.example.lab1.exception.MyException;
import com.example.lab1.model.dto.ItemDto;
import com.example.lab1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {this.itemService=itemService;}

    @GetMapping()
    public ResponseEntity<List<ItemDto>> getAllItems(){
        return ResponseEntity.ok(itemService.getItemsDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable(value = "id") Long id) throws MyException {
        ItemDto itemDtoResponse=itemService.getItemDtoByID(id);
        return ResponseEntity.ok(itemDtoResponse);
    }

    @PostMapping()
    public ResponseEntity<ItemDto> createItem(@RequestBody final ItemDto itemDtoRequest) {
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

}

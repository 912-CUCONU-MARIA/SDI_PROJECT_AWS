package com.example.lab1.controller;

import com.example.lab1.exception.MyException;
import com.example.lab1.model.dto.GameUserDto;
import com.example.lab1.model.dto.ItemDto;
import com.example.lab1.model.dto.ItemNoPlayerCharacters;
import com.example.lab1.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        return ResponseEntity.ok(itemService.getItemsDto(PageRequest.of(page, size)));
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

    @PutMapping()
    void addCount(){
        itemService.setCount();
    }

}

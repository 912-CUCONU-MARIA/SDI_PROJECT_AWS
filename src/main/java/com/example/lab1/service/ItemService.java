package com.example.lab1.service;

import com.example.lab1.exception.MyException;
import com.example.lab1.model.Item;
import com.example.lab1.model.dto.GameUserDto;
import com.example.lab1.model.dto.ItemDto;
import com.example.lab1.model.dto.ItemNoPlayerCharacters;
import com.example.lab1.model.dto.ItemNoPlayerCharactersSmol;
import com.example.lab1.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired//Spring injects the repositories when the service is created.
    public ItemService(ItemRepository itemRepository){this.itemRepository=itemRepository;}

    public ItemDto addItem(ItemDto itemDto){
        return ItemDto.from(itemRepository.save(Item.from(itemDto)));
    }

//    public Page<ItemNoPlayerCharacters> getItemsDto(Pageable pageable){
//        return itemRepository.findAll(pageable)
//                .map(ItemNoPlayerCharacters::from);
//    }

    public Page<ItemNoPlayerCharactersSmol> getItemsDto(Pageable pageable) {
        Page<Object[]> results = itemRepository.getAllItems(pageable);

        List<ItemNoPlayerCharactersSmol> itemDtos = results.stream()
                .map(result -> ItemNoPlayerCharactersSmol.builder()
                        .id(((Number) result[0]).longValue())
                        .itemName((String) result[1])
                        .itemLevel(((Number) result[2]).longValue())
                        .numberOfCopies(((Number) result[3]).longValue())
                        .build())
                .collect(Collectors.toList());

        return new PageImpl<>(itemDtos, pageable, results.getTotalElements());
    }

    public List<ItemDto> getItemsDto(){
        return itemRepository.findAll().stream().map(ItemDto::from).collect(Collectors.toList());
    }

    public ItemDto getItemDtoByID(Long id) throws MyException{
        return ItemDto.from(itemRepository.findById(id).orElseThrow(()->new MyException(id.toString())));
    }

    public void deleteItemByID(Long id) throws MyException{
        itemRepository.delete(itemRepository.findById(id).orElseThrow(()->new MyException(id.toString())));
    }

    public void deleteItems(){itemRepository.deleteAll();}

    @Transactional
    public ItemDto updateItem(Long id, ItemDto newItemDto) throws MyException{
        Item itemToUpdate=itemRepository.findById(id).orElseThrow(()-> new MyException(id.toString()));

        itemToUpdate.setItemName(newItemDto.getItemName());
        itemToUpdate.setItemName(newItemDto.getItemName());
        itemToUpdate.setItemRarity(newItemDto.getItemRarity());
        itemToUpdate.setItemType(newItemDto.getItemType());
        itemToUpdate.setItemEffect(newItemDto.getItemEffect());
        itemToUpdate.setItemLevel(newItemDto.getItemLevel());
        itemToUpdate.setDescription(newItemDto.getDescription());

        return ItemDto.from(itemToUpdate);
    }

    public void setCount(){
        itemRepository.findAll().forEach(item -> {
            item.setNumberOfCopies((long) item.getPlayerCharacterItemSet().size());
            itemRepository.save(item);
        });
    }



}

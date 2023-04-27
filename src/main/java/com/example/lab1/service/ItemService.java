package com.example.lab1.service;

import com.example.lab1.exception.MyException;
import com.example.lab1.model.Item;
import com.example.lab1.model.dto.GameUserDto;
import com.example.lab1.model.dto.ItemDto;
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


//    public Page<ItemDto> getItemsDto(Pageable pageable){
//        return itemRepository.findAll(pageable)
//                .map(ItemDto::from);
//    }

    public Page<ItemDto> getItemsDto(Pageable pageable) {
        Page<Object[]> results = itemRepository.findAllWithPlayerCharacterItemCount(pageable);
        List<ItemDto> itemDtos = results.getContent().stream()
                .map(record -> {
                    ItemDto itemDto = new ItemDto();
                    itemDto.setId((Long) record[0]);
                    itemDto.setItemName((String) record[1]);
                    itemDto.setItemRarity((String) record[2]);
                    itemDto.setItemType((String) record[3]);
                    itemDto.setItemLevel((Long) record[4]);
                    itemDto.setNumberOfPlayerCharactersOwning((Long) record[5]);
                    return itemDto;
                })
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




}

package com.example.lab1.model;

import com.example.lab1.model.dto.ItemDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    private String itemName;

    private String itemRarity;

    private String itemType;

    private String itemEffect;

    private Long itemLevel;

    private String description;


    /*
        @OneToMany(mappedBy="gameUser" ,cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<PlayerCharacter> playerCharacterSet;
     */
    @OneToMany(mappedBy="item" ,cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<PlayerCharacterItem> playerCharacterItemSet;

    private Long numberOfCopies;


    public Item() {
    }

    public Item(String itemName, String itemRarity, String itemType, String itemEffect, Long itemLevel, String description) {
        this.itemName = itemName;
        this.itemRarity = itemRarity;
        this.itemType = itemType;
        this.itemEffect = itemEffect;
        this.itemLevel = itemLevel;
        this.description=description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemRarity() {
        return itemRarity;
    }

    public void setItemRarity(String itemRarity) {
        this.itemRarity = itemRarity;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemEffect() {
        return itemEffect;
    }

    public void setItemEffect(String itemEffect) {
        this.itemEffect = itemEffect;
    }

    public Long getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(Long itemLevel) {
        this.itemLevel = itemLevel;
    }

    public Set<PlayerCharacterItem> getPlayerCharacterItemSet() {
        return playerCharacterItemSet;
    }

    public void setPlayerCharacterItemSet(Set<PlayerCharacterItem> playerCharacterItemSet) {
        this.playerCharacterItemSet = playerCharacterItemSet.stream().collect(Collectors.toSet());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(Long numberOfPlayerCharactersOwning) {
        this.numberOfCopies = numberOfPlayerCharactersOwning;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemRarity='" + itemRarity + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemEffect='" + itemEffect + '\'' +
                ", itemLevel=" + itemLevel +
                ", description='" + description + '\'' +
                ", playerCharacterItemSet=" + playerCharacterItemSet +
                ", numberOfCopies=" + numberOfCopies +
                '}';
    }


    public static Item from(ItemDto itemDto){
        Item item=new Item();
        item.setItemName(itemDto.getItemName());
        item.setItemRarity(itemDto.getItemRarity());
        item.setItemType(itemDto.getItemType());
        item.setItemEffect(itemDto.getItemEffect());
        item.setItemLevel(itemDto.getItemLevel());
        item.setDescription(itemDto.getDescription());
        item.setNumberOfCopies(0L);
        item.setPlayerCharacterItemSet(new HashSet<>());
        return item;
    }

}

package com.example.lab1.model;

import com.example.lab1.model.dto.PlayerCharacterItemDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class PlayerCharacterItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //char_id
    //item_id
    //is_equipped

    private Boolean isEquipped;

    private Long upgradeTier;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="playercharacter_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private PlayerCharacter playerCharacter;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="item_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Item item;

    public PlayerCharacterItem() {
    }

    public PlayerCharacterItem(Boolean isEquipped, Long upgradeTier, PlayerCharacter playerCharacter, Item item) {
        this.isEquipped = isEquipped;
        this.upgradeTier = upgradeTier;
        this.playerCharacter = playerCharacter;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEquipped() {
        return isEquipped;
    }

    public void setEquipped(Boolean equipped) {
        isEquipped = equipped;
    }

    public Long getUpgradeTier() {
        return upgradeTier;
    }

    public void setUpgradeTier(Long upgradeTier) {
        this.upgradeTier = upgradeTier;
    }

    public PlayerCharacter getPlayerCharacter() {
        return playerCharacter;
    }

    public void setPlayerCharacter(PlayerCharacter playerCharacter) {
        this.playerCharacter = playerCharacter;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "PlayerCharacterItem{" +
                "id=" + id +
                ", isEquipped=" + isEquipped +
                ", upgradeTier=" + upgradeTier +
                '}';
    }

    public static PlayerCharacterItem from(PlayerCharacterItemDto playerCharacterItemDto){
        PlayerCharacterItem playerCharacterItem=new PlayerCharacterItem();
        playerCharacterItem.setEquipped(playerCharacterItemDto.getIsEquipped());
        playerCharacterItem.setUpgradeTier(playerCharacterItemDto.getUpgradeTier());

        return playerCharacterItem;
    }

}

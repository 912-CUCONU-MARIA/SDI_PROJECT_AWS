package com.example.lab1.model.dto;

import com.example.lab1.model.PlayerCharacterItem;
import lombok.Data;

@Data
public class PlayerCharacterItemDto {

    private Long id;

    private Boolean isEquipped;

    private Long upgradeTier;


    public static PlayerCharacterItemDto from(PlayerCharacterItem pci){
        PlayerCharacterItemDto playerCharacterItemDto=new PlayerCharacterItemDto();
        playerCharacterItemDto.setId(pci.getId());
        playerCharacterItemDto.setIsEquipped(pci.getEquipped());
        playerCharacterItemDto.setUpgradeTier(pci.getUpgradeTier());

        return playerCharacterItemDto;
    }

}

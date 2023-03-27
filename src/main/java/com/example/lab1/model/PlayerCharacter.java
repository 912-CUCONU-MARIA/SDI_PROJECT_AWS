package com.example.lab1.model;

import com.example.lab1.model.dto.PlayerCharacterDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;


@Entity
public class PlayerCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name should not be blank")
    private String pc_name;

    @Min(value = 0, message = "Level should not be less than 0")
    @Max(value = 150, message = "Level should not be greater than 99999")
    private Long level;

    @Min(value = 0, message = "Experience should not be less than 0")
    private Long experience;

    private Long classId;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="gameUser_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private GameUser gameUser;

    @OneToMany(mappedBy="playerCharacter" ,cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<PlayerCharacterItem> playerCharacterItemSet;


    public PlayerCharacter() {
    }

    public PlayerCharacter(String pc_name, Long level, Long experience, Long classId, GameUser gameUser) {
        this.pc_name = pc_name;
        this.level = level;
        this.experience = experience;
        this.classId = classId;
        this.gameUser=gameUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPc_name() {
        return pc_name;
    }

    public void setPc_name(String pc_name) {
        this.pc_name = pc_name;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public GameUser getGameUser() {
        return gameUser;
    }

    public void setGameUser(GameUser gameUser) {
        this.gameUser = gameUser;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Set<PlayerCharacterItem> getPlayerCharacterItemSet() {
        return playerCharacterItemSet;
    }

    public void setPlayerCharacterItemSet(Set<PlayerCharacterItem> playerCharacterItemSet) {
        this.playerCharacterItemSet = playerCharacterItemSet;
    }

    @Override
    public String toString() {
        return "PlayerCharacter{" +
                "id=" + id +
                ", pc_name='" + pc_name + '\'' +
                ", level=" + level +
                ", experience=" + experience +
                ", classId=" + classId +
                ", gameUser=" + gameUser +
                '}';
    }

    public static PlayerCharacter from(PlayerCharacterDto playerCharacterDto){

        PlayerCharacter playerCharacter=new PlayerCharacter();
        //playerCharacter.setId(playerCharacterDto.getId());
        playerCharacter.setPc_name(playerCharacterDto.getPc_name());
        playerCharacter.setLevel(playerCharacterDto.getLevel());
        playerCharacter.setExperience(playerCharacterDto.getExperience());
        playerCharacter.setClassId(playerCharacterDto.getClassId());

        return playerCharacter;

    }



}

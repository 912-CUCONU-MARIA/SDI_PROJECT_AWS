package com.example.lab1.model;

import com.example.lab1.model.dto.GameUserDto;
import com.example.lab1.model.dto.GameUserDtoWPlayerChObject;
import com.example.lab1.model.dto.PlayerCharacterDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class GameUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;
    private String lastName;

    private String emailAddress;

    private Boolean activeStatus;

    private String username;

    private String password;


    @OneToMany(mappedBy="gameUser" ,cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<PlayerCharacter> playerCharacterSet;

    private Float averagePlayerCharacterLevel;

    private Long numberOfPlayerCharacters;

    public GameUser() {
    }

    // Long id
    public GameUser(String firstName, String lastName, String emailAddress, Boolean activeStatus, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.activeStatus = activeStatus;
        this.username = username;
        this.password = password;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmailAddress() {return emailAddress;}
//
//    public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress;}
//
//    public Boolean getActiveStatus() {
//        return activeStatus;
//    }
//
//    public void setActiveStatus(Boolean activeStatus) {
//        this.activeStatus = activeStatus;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Set<PlayerCharacter> getPlayerCharacterSet() {
//        return playerCharacterSet;
//    }
//
//    public void setPlayerCharacterSet(Set<PlayerCharacter> playerCharacterSet) {
//        this.playerCharacterSet = playerCharacterSet.stream().collect(Collectors.toSet());
//    }
//
//    public Float getAveragePlayerCharacterLevel() {
//        return averagePlayerCharacterLevel;
//    }
//
//    public void setAveragePlayerCharacterLevel(Float averagePlayerCharacterLevel) {
//        this.averagePlayerCharacterLevel = averagePlayerCharacterLevel;
//    }


    @Override
    public String toString() {
        return "GameUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", activeStatus=" + activeStatus +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", playerCharacterSet=" + playerCharacterSet +
                ", averagePlayerCharacterLevel=" + averagePlayerCharacterLevel +
                ", numberOfPlayerCharacters=" + numberOfPlayerCharacters +
                '}';
    }

    public static GameUser from(GameUserDto gameUserDto){
        GameUser gameUser=new GameUser();
        gameUser.setFirstName(gameUserDto.getFirstName());
        gameUser.setLastName(gameUserDto.getLastName());
        gameUser.setEmailAddress(gameUserDto.getEmailAddress());
        gameUser.setActiveStatus(gameUserDto.getActiveStatus());
        gameUser.setUsername(gameUserDto.getUsername());
        gameUser.setPassword(gameUserDto.getPassword());
        gameUser.setPlayerCharacterSet(new HashSet<>());
        gameUser.setAveragePlayerCharacterLevel(0F);
        gameUser.setNumberOfPlayerCharacters(0L);
        return gameUser;
    }

//    public static GameUser from(GameUserDtoWPlayerChObject gameUserDtoWPlayerChObject){
//        GameUser gameUser=new GameUser();
//        gameUser.setFirstName(gameUserDtoWPlayerChObject.getFirstName());
//        gameUser.setLastName(gameUserDtoWPlayerChObject.getLastName());
//        gameUser.setEmailAddress(gameUserDtoWPlayerChObject.getEmailAddress());
//        gameUser.setActiveStatus(gameUserDtoWPlayerChObject.getActiveStatus());
//        gameUser.setUsername(gameUserDtoWPlayerChObject.getUsername());
//        gameUser.setPassword(gameUserDtoWPlayerChObject.getPassword());
//
//        return gameUser;
//    }


}

//package com.example.lab1;
//
//import com.example.lab1.model.GameUser;
//import com.example.lab1.model.Item;
//import com.example.lab1.model.PlayerCharacter;
//import com.example.lab1.model.PlayerCharacterItem;
//import com.example.lab1.model.dto.PlayerCharacterAndNumberOfOtherPlayerCharactersDto;
//import com.example.lab1.model.dto.PlayerCharacterDto;
//import com.example.lab1.repository.PlayerCharacterRepository;
//import com.example.lab1.service.PlayerCharacterService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(MockitoExtension.class)
//public class PlayerCharacterServiceTest {
//
//    @Mock
//    private static PlayerCharacterRepository playerCharacterRepository;
//
//    @InjectMocks
//    private PlayerCharacterService playerCharacterService;
//
//    @Test
//    public void getPlayerCharacterSortedByNumberOfItems(){
//        GameUser gameUser1=new GameUser("fName1","lName1","email1",true,"username1","pass1");
//        gameUser1.setId(1L);
//        gameUser1.setPlayerCharacterSet(new HashSet<>());
//
//        Item item1=new Item("item1","legendary","sword","wow effect",100L);
//        item1.setId(1L);
//
//        PlayerCharacter playerCharacter1=new PlayerCharacter("pc1",100L,15352L,1L,gameUser1);
//        playerCharacter1.setId(1L);
//        playerCharacter1.setPlayerCharacterItemSet(new HashSet<>());
//        gameUser1.getPlayerCharacterSet().add(playerCharacter1);
//
//        PlayerCharacterItem pci1=new PlayerCharacterItem(true,1L,playerCharacter1,item1);
//        pci1.setId(1L);
//        playerCharacter1.getPlayerCharacterItemSet().add(pci1);
//        PlayerCharacterItem pci2=new PlayerCharacterItem(false,2L,playerCharacter1,item1);
//        pci1.setId(2L);
//        playerCharacter1.getPlayerCharacterItemSet().add(pci2);
//        PlayerCharacterItem pci3=new PlayerCharacterItem(true,5L,playerCharacter1,item1);
//        pci1.setId(3L);
//        playerCharacter1.getPlayerCharacterItemSet().add(pci3);
//
//        PlayerCharacter playerCharacter2 = new PlayerCharacter("pc2", 120L, 20000L, 3L, gameUser1);
//        playerCharacter2.setId(2L);
//        playerCharacter2.setPlayerCharacterItemSet(new HashSet<>());
//        gameUser1.getPlayerCharacterSet().add(playerCharacter2);
//
//        PlayerCharacterItem pci4=new PlayerCharacterItem(true,4L,playerCharacter2,item1);
//        pci1.setId(4L);
//        playerCharacter2.getPlayerCharacterItemSet().add(pci4);
//        PlayerCharacterItem pci5=new PlayerCharacterItem(false,6L,playerCharacter2,item1);
//        pci1.setId(5L);
//        playerCharacter2.getPlayerCharacterItemSet().add(pci5);
//
//
//
//        PlayerCharacter playerCharacter3 = new PlayerCharacter("pc3", 90L, 13500L, 2L, gameUser1);
//        playerCharacter3.setId(3L);
//        playerCharacter3.setPlayerCharacterItemSet(new HashSet<>());
//        gameUser1.getPlayerCharacterSet().add(playerCharacter3);
//
//        PlayerCharacterItem pci6=new PlayerCharacterItem(true,1L,playerCharacter3,item1);
//        pci1.setId(6L);
//        playerCharacter3.getPlayerCharacterItemSet().add(pci6);
//
////        ArrayList<GameUser>gameUsersList=new ArrayList<>();
////        gameUsersList.add(gameUser1); gameUsersList.add(gameUser2); gameUsersList.add(gameUser3);
//
//        ArrayList<PlayerCharacter> playerCharacterList=new ArrayList<>();
//        playerCharacterList.add(playerCharacter1); playerCharacterList.add(playerCharacter2); playerCharacterList.add(playerCharacter3);
//
//        when(playerCharacterRepository.findAll()).thenReturn(playerCharacterList);
//
//        //expected and actual
//        List<PlayerCharacterAndNumberOfOtherPlayerCharactersDto> expected=new ArrayList<>();
//        expected.add(PlayerCharacterAndNumberOfOtherPlayerCharactersDto.from(playerCharacter3,1L));
//        expected.add(PlayerCharacterAndNumberOfOtherPlayerCharactersDto.from(playerCharacter2,2L));
//        expected.add(PlayerCharacterAndNumberOfOtherPlayerCharactersDto.from(playerCharacter1,3L));
//
//        ArrayList<PlayerCharacterAndNumberOfOtherPlayerCharactersDto> actual= new ArrayList<>();
//        actual=(ArrayList<PlayerCharacterAndNumberOfOtherPlayerCharactersDto>) playerCharacterService.getPlayerCharacterSortedByNumberOfItems();
//
//        Assertions.assertEquals(expected,actual);
//
//    }
//
//    @Test
//    public void getPlayerCharactersLevelGreaterThan(){
//        GameUser gameUser1=new GameUser("fName1","lName1","email1",true,"username1","pass1");
//        gameUser1.setId(1L);
//        gameUser1.setPlayerCharacterSet(new HashSet<>());
//
//        PlayerCharacter playerCharacter1=new PlayerCharacter("pc1",100L,15352L,1L,gameUser1);
//        playerCharacter1.setId(1L);
//        gameUser1.getPlayerCharacterSet().add(playerCharacter1);
//
//        PlayerCharacter playerCharacter2 = new PlayerCharacter("pc2", 120L, 20000L, 3L, gameUser1);
//        playerCharacter2.setId(2L);
//        gameUser1.getPlayerCharacterSet().add(playerCharacter2);
//
//        PlayerCharacter playerCharacter3 = new PlayerCharacter("pc3", 90L, 13500L, 2L, gameUser1);
//        playerCharacter3.setId(3L);
//        gameUser1.getPlayerCharacterSet().add(playerCharacter3);
//
//        ArrayList<PlayerCharacter> playerCharacterList=new ArrayList<>();
//        playerCharacterList.add(playerCharacter1); playerCharacterList.add(playerCharacter2);
////        when(playerCharacterRepository.findAll()).thenReturn(playerCharacterList);
//
//        when(playerCharacterRepository.findByLevelGreaterThan(95L)).thenReturn(playerCharacterList);
//
//        //expected and actual
//        List<PlayerCharacterDto> expected=new ArrayList<>();
//        expected.add(PlayerCharacterDto.from(playerCharacter1));
//        expected.add(PlayerCharacterDto.from(playerCharacter2));
//        ArrayList<PlayerCharacterDto> actual= new ArrayList<>();
//        actual=(ArrayList<PlayerCharacterDto>) playerCharacterService.getPlayerCharactersLevelGreaterThan(95L);
//
//        Assertions.assertEquals(expected,actual);
//
//    }
//
//}

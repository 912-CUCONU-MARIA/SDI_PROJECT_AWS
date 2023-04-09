//package com.example.lab1;
//
//import com.example.lab1.model.GameUser;
//import com.example.lab1.model.PlayerCharacter;
//import com.example.lab1.model.dto.GameUserAveragePlayerCharacterLevelDto;
//import com.example.lab1.repository.GameUserRepository;
//import com.example.lab1.repository.PlayerCharacterRepository;
//import com.example.lab1.service.GameUserService;
//import com.example.lab1.service.PlayerCharacterService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.*;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
////@RunWith(MockitoJUnitRunner.class)
//public class GameUserServiceTest {
//
//    @Mock
//    private GameUserRepository gameUserRepository;
//
//    @InjectMocks
//    private GameUserService gameUserService;
//
//
//    //
//    @Test
//    public void getGameUsersOrderedByAverageLevelOfPlayerCharacters(){
//
//        GameUser gameUser1=new GameUser("fName1","lName1","email1",true,"username1","pass1");
//        gameUser1.setId(1L);
//        gameUser1.setPlayerCharacterSet(new HashSet<>());
//
//        GameUser gameUser2=new GameUser("fName2","lName2","email2",false,"username2","pass2");
//        gameUser2.setId(2L);
//        gameUser2.setPlayerCharacterSet(new HashSet<>());
//
//        GameUser gameUser3=new GameUser("fName3","lName3","email3",true,"username3","pass3");
//        gameUser3.setId(3L);
//        gameUser3.setPlayerCharacterSet(new HashSet<>());
//
//        PlayerCharacter playerCharacter1=new PlayerCharacter("pc1",100L,15352L,1L,gameUser1);
//        playerCharacter1.setId(1L);
//        gameUser1.getPlayerCharacterSet().add(playerCharacter1);
//        PlayerCharacter playerCharacter2 = new PlayerCharacter("pc2", 120L, 20000L, 3L, gameUser1);
//        playerCharacter2.setId(2L);
//        gameUser1.getPlayerCharacterSet().add(playerCharacter2);
//        PlayerCharacter playerCharacter3 = new PlayerCharacter("pc3", 80L, 10000L, 2L, gameUser1);
//        playerCharacter3.setId(3L);
//        gameUser1.getPlayerCharacterSet().add(playerCharacter3);
//
//
//        PlayerCharacter playerCharacter4 = new PlayerCharacter("pc4", 90L, 13500L, 2L, gameUser2);
//        playerCharacter4.setId(4L);
//        gameUser2.getPlayerCharacterSet().add(playerCharacter4);
//        PlayerCharacter playerCharacter5 = new PlayerCharacter("pc5", 110L, 18000L, 4L, gameUser2);
//        playerCharacter5.setId(5L);
//        gameUser2.getPlayerCharacterSet().add(playerCharacter5);
//        PlayerCharacter playerCharacter6 = new PlayerCharacter("pc6", 70L, 9000L, 3L, gameUser2);
//        playerCharacter6.setId(6L);
//        gameUser2.getPlayerCharacterSet().add(playerCharacter6);
//
//
//        PlayerCharacter playerCharacter7 = new PlayerCharacter("pc7", 3000L, 14500L, 3L, gameUser3);
//        playerCharacter7.setId(7L);
//        gameUser3.getPlayerCharacterSet().add(playerCharacter7);
//        PlayerCharacter playerCharacter8 = new PlayerCharacter("pc8", 100L, 17000L, 2L, gameUser3);
//        playerCharacter8.setId(8L);
//        gameUser3.getPlayerCharacterSet().add(playerCharacter8);
//        PlayerCharacter playerCharacter9 = new PlayerCharacter("pc9", 200L, 9500L, 4L, gameUser3);
//        playerCharacter9.setId(9L);
//        gameUser3.getPlayerCharacterSet().add(playerCharacter9);
//
//        ArrayList<GameUser>gameUsersList=new ArrayList<>();
//        gameUsersList.add(gameUser1); gameUsersList.add(gameUser2); gameUsersList.add(gameUser3);
//
////        ArrayList<PlayerCharacter>playerCharacterList=new ArrayList<>();
////        playerCharacterList.add(playerCharacter1); playerCharacterList.add(playerCharacter2); playerCharacterList.add(playerCharacter3); playerCharacterList.add(playerCharacter4); playerCharacterList.add(playerCharacter5); playerCharacterList.add(playerCharacter6); playerCharacterList.add(playerCharacter7); playerCharacterList.add(playerCharacter8); playerCharacterList.add(playerCharacter9);
//
//        when(gameUserRepository.findAll()).thenReturn(gameUsersList);
//        //playerrepo maybe
//
//        //expected and actual
//        List<GameUserAveragePlayerCharacterLevelDto> expectedGameUserByAverageList=new ArrayList<>();
//        expectedGameUserByAverageList.add(GameUserAveragePlayerCharacterLevelDto.from(gameUser2,90L));
//        expectedGameUserByAverageList.add(GameUserAveragePlayerCharacterLevelDto.from(gameUser1,100L));
//        expectedGameUserByAverageList.add(GameUserAveragePlayerCharacterLevelDto.from(gameUser3,1100L));
//
//        ArrayList<GameUserAveragePlayerCharacterLevelDto> actual = new ArrayList<>();
//            actual= (ArrayList<GameUserAveragePlayerCharacterLevelDto>) gameUserService.getGameUsersOrderedByAverageLevelOfPlayerCharacters();
//
//        Assertions.assertEquals(expectedGameUserByAverageList,actual);
//
//    }
//
//
//}

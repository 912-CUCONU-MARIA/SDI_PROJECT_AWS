package com.example.lab1;

import com.example.lab1.model.GameUser;
import com.example.lab1.model.PlayerCharacter;
import com.example.lab1.repository.GameUserRepository;
import com.example.lab1.repository.PlayerCharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(GameUserRepository gu_repository, PlayerCharacterRepository pc_repository ) {

        return args -> {
//            log.info("Preloading " + repository.save(new Customer("fname1","lname1","073435623",true,"username1","pass1")));
//            log.info("Preloading " + repository.save(new Customer("fname2","lname2","073934911",false,"username2","pass2")));

//            log.info("Preloading " + gu_repository.save(new GameUser("fname1","lname1","email@1",true,"username1","pass1")));
//            log.info("Preloading " + gu_repository.save(new GameUser("fname2","lname2","email@2",false,"username2","pass2")));
//
//            log.info("Preloading " + pc_repository.save(new PlayerCharacter("Brostorm",66L, 45004L, 1L,null)));
//            log.info("Preloading " + pc_repository.save(new PlayerCharacter("Altair",22L, 10030L, 2L,null)));


        };
    }

}
package com.beck.mcb.telegrambot_v3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
//@EnableRedisRepositories
public class TelegrambotV3Application {

    public static void main( String[] args ) {
        ApiContextInitializer.init();
        SpringApplication.run(TelegrambotV3Application.class , args);
    }

}

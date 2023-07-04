package com.example.udon_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class UdonServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UdonServerApplication.class, args);
    }

}

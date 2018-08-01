package com.springboot.shoehome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class ShoehomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoehomeApplication.class, args);
    }
}

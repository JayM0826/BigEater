package com.bigeater;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@MapperScan(basePackages = "com.bigeater.dao")
public class DoNothingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoNothingApplication.class, args);
    }

}


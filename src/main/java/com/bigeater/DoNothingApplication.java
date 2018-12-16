package com.bigeater;

import com.bigeater.dao.UserPoMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.bigeater.dao")
public class DoNothingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoNothingApplication.class, args);
    }
}


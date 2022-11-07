package com.hussain.demo;

import com.hussain.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaSpecificationApplication implements CommandLineRunner {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(JpaSpecificationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //userService.getAllUsers();
        //userService.getUsers();
        userService.getUsersDTOs().forEach(System.out::println);
    }
}

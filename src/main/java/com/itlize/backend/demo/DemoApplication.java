package com.itlize.backend.demo;

import com.itlize.backend.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

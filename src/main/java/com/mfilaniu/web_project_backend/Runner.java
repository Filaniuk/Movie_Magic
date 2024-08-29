package com.mfilaniu.web_project_backend;

import com.mfilaniu.web_project_backend.service.ImageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class Runner {

    public static void main(String[] args) {

        var context = SpringApplication.run(Runner.class, args);

    }

}

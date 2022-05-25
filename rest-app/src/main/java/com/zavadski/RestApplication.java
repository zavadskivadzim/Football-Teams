package com.zavadski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RestApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}

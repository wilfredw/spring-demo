package com.wei.spring.app.testweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring-config.xml"})
public class TestWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestWebApplication.class, args);
    }
}

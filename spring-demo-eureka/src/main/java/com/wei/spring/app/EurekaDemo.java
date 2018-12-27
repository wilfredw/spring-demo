package com.wei.spring.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaDemo {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaDemo.class).web(true).run(args);
    }
}

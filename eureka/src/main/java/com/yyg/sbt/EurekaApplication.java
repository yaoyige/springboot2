package com.yyg.sbt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//启用注册中心
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
    @Value("${api.test}")
    private  String test;
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}

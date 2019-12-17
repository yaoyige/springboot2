package com.yyg.task;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableSwaggerBootstrapUI
@SpringBootApplication
@ComponentScan(basePackages = {"com.yyg.*"})
@MapperScan(basePackages={"com.yyg.**.store.mapper"})
public class SpringbootTaskApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringbootTaskApplication.class,args);
    }
}
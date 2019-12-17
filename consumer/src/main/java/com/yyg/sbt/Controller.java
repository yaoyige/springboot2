package com.yyg.sbt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/buy")
    public String buyTicket(String  name){
        String ticket = restTemplate.getForObject("http://provider/ticket", String.class);
        return name+"购买了："+ticket;
    }
}
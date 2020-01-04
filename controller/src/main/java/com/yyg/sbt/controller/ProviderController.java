package com.yyg.sbt.controller;

import com.yyg.sbt.activemq.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

/**
 * @Auther: yyg
 * @Date: 2019/12/3 09:19
 * @Description:  队列传递消息
 */
@RestController
public class ProviderController {

    @Autowired
    JmsProducer jmsProducer;
    //注入存放消息的队列，用于下列方法一
    @Autowired
    private Queue queue;

    //注入springboot封装的工具类
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("send")
    public void send(String name) {
        //规定消费队列
//        Destination destination = new ActiveMQQueue("publish.queue");
        //方法一：添加消息到消息队列
        jmsMessagingTemplate.convertAndSend(queue, name);
        //方法二：这种方式不需要手动创建queue，系统会自行创建名为test的队列
        //jmsMessagingTemplate.convertAndSend("test", name);
    }
    @RequestMapping("send1")
    @Scheduled(fixedDelay = 3000) //每隔3秒生产一个消息
    public void send1() {
        String name = "yaoyige";
        //发送当前时间
        jmsProducer.delaySend("pushFiveMinutes", name, 5 * 60 * 1000L);
    }

}

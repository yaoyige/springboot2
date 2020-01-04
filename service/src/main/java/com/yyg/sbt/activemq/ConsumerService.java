package com.yyg.sbt.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @Auther: yyg
 * @Date: 2019/12/3 09:33
 * @Description:
 */
@Component
public class ConsumerService {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = "publish.queue", containerFactory = "jmsListenerContainerFactory")
//    @JmsListener(destination = "ActiveMQQueue")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo("SQueue")
    public String handleMessage(String name) {
        System.out.println("成功接受Name" + name);
        return "成功接受Name" + name;
    }
    /**
     *
     * 功能描述:  消费者消费的消息根据生产者返回来的信息具体定制返回值
     * @param:
     * @return:
     * @auther: yyg
     * @date: 2019/12/3 10:32
     */
    // JmsListener  中的 destination :指定消费者消费哪一个队列中的消息
    @JmsListener(destination = "pushFiveMinutes", containerFactory = "jmsListenerContainerFactory")
    // SendTo 会将此方法返回的数据, 写入到 SQueue 中去.
    @SendTo("SQueue")
    public String handleMessage1(String name) {
        System.out.println("成功接受Name" + name);
        return "成功接受Name" + name;
    }

}

package com.yyg.sbt.store.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix="email") //接收application.yml中的myProps下面的属性
@Data
public class Mail {
    @Value(value = "${email.host}")
    private String host;
    @Value(value = "${email.port}")
    public String port;
    @Value(value = "${email.form}")
    public String form;
    @Value(value = "${email.username}")
    public String username;
    @Value(value = "${email.password}")
    public String password;
}

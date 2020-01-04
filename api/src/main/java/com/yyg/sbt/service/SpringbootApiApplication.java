package com.yyg.sbt.service;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * @EnableSwaggerBootstrapUI :让swagger展示doc格式
 */
@EnableSwaggerBootstrapUI  //swagger注解显示doc格式
@SpringBootApplication
@ComponentScan(basePackages = {"com.yyg.*"}) //全局扫描
@MapperScan(basePackages={"com.yyg.**.store.mapper"})  //mapper扫描
@EnableRetry //启动 重试 注解
@EnableJms //启动activeMq
@EnableScheduling // spring自带的定时任务功能, 使用注解@Scheduled(cron="0/5 * *  * * ? ") 时,下面的方法不能有参数
public class SpringbootApiApplication {
      public static void main(String[] args){
          SpringApplication.run(SpringbootApiApplication.class,args);
      }
}

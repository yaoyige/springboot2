package com.yyg.sbt.controller;


import com.google.gson.JsonObject;
import com.yyg.sbt.impl.MailServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yaoyige
 * @since 2019-05-29
 */
@Api(description = "学生控制层",tags = "xuehseng")
@RestController
@RequestMapping("/student")
public class StudentController{
    private static Logger logger =  LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private MailServiceImpl mailService;

    @GetMapping("/sendEmail")
    @ApiOperation(value = "查询学生",notes = "查询学生")
    public void sendEmail(){
        String someone="chao521it@163.com";
        String subject="提示";
        String content="哈喽你好！";
        mailService.sendPersonEmail( someone,subject,content);
    }
}

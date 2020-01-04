package com.yyg.sbt.service.impl;

import com.yyg.sbt.BaseTest;
import com.yyg.sbt.service.common.util.JSONResult;
import com.yyg.sbt.test.UpdateStudentThread;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @Auther: yyg
 * @Date: 2019/12/9 16:44
 * @Description: 学生表修改测试
 */
public class UpdateStudentThreadTest extends BaseTest {
    @Autowired
    UpdateStudentThread updateStudentThread;

    @Test
    public void updateStudent(){
        String studentId ="1";
        BigDecimal aCount = new BigDecimal("100");
        updateStudentThread.updateStudent(studentId,aCount);
        System.out.println("更新完成");
    }
}

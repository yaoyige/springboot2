package com.yyg.sbt.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyg.sbt.BaseTest;
import com.yyg.sbt.impl.MailServiceImpl;
import com.yyg.sbt.impl.StudentServiceImpl;
import com.yyg.sbt.store.domain.Student;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public class StudentServiceImplTest extends BaseTest {

    @Autowired
     StudentServiceImpl studentService;
    @Autowired
     RedisTemplate redisTemplate;
    @Autowired
    MailServiceImpl mailService;

    @Test
    public void insertStudent() {
        Student student = new Student();
        student.setStuName("姚伊歌ya");
        student.setStuSex("1");
        LocalDateTime localDateTime = LocalDateTime.now();
        student.setStuBirdaty(localDateTime);
        student.setStuDepId(1L);
        student.setStuClassId(2L);
        int i = studentService.insertStudent(student);

    }
    @Test
    @Transactional(noRollbackFor =ArithmeticException.class )
    public void test() {

        try{
            int i =1;
            int j=0;
            int a = i/j;
            System.out.print("try里面的内容"+a);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            insert();
        }

    }
    @Test
    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public void insert(){
        Student student = new Student();
        student.setStuName("姚伊歌1");
        student.setStuSex("1");
        LocalDateTime localDateTime = LocalDateTime.now();
        student.setStuBirdaty(localDateTime);
        student.setStuDepId(1L);
        student.setStuClassId(2L);
        int i = studentService.insertStudent(student);
        System.out.print("插入数据库"+i);
    }
    @Test
    public void Testa()throws Exception{
        Student student = new Student();
        student.setStuName("姚伊歌1");
        student.setStuSex("1");
        LocalDateTime localDateTime = LocalDateTime.now();
        student.setStuBirdaty(localDateTime);
        student.setStuDepId(1L);
        student.setStuClassId(2L);
        studentService.aTest(student);

    }
    @Test
    public void sendEmail() {
        String content ="哈喽你好！";
        mailService.sendPersonEmail("chao521it@163.com", "提示", content);
    }
    @Test
    public void selectStudent(){
        Page page = new Page();
        page.setCurrent(1);
        page.setPages(10);
        List<Student> students = studentService.queryStudent(page);

    }

    @Test
    public void insertStudentId(){
        int i = studentService.insertStudentId();

    }
}

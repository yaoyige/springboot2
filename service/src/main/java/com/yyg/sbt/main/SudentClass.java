package com.yyg.sbt.main;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyg.sbt.store.domain.Student;
import com.yyg.sbt.store.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: yyg
 * @Date: 2019/12/25 13:50
 * @Description:
 */
@Service
public class SudentClass {
    @Autowired
    StudentMapper studentMapper;

    public List<Student> getStudents(String name){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_name",name);
        return studentMapper.selectList(queryWrapper);

    }

    public Integer getInteger(String name){
        Integer integer  = 10;
        return integer;
    }
}

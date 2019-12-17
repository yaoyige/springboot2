package com.yyg.sbt.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.sbt.service.StudentService;
import com.yyg.sbt.store.domain.Student;
import com.yyg.sbt.store.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class ClassServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 插入学生
     * @param student 学生
     * @return 学生
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
    public int insertStudent(Student student){
        int insert = studentMapper.insert(student);
        log.error("nihap ");
        return insert;
    }

    /**
     * 测试
     */
    public void testPet(){
             Map<String,String> map= new HashMap<>();
             Map<String, Object> objectMap = new ConcurrentHashMap<>();

    }
}

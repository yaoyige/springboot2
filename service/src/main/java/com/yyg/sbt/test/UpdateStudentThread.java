package com.yyg.sbt.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.gson.JsonObject;
import com.yyg.sbt.service.StudentService;
import com.yyg.sbt.service.common.util.JSONResult;
import com.yyg.sbt.store.domain.Student;
import com.yyg.sbt.store.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Auther: yyg
 * @Date: 2019/12/9 16:14
 * @Description:  修改的时候保证线程安全
 */
@Service
public class UpdateStudentThread {

    @Autowired
    StudentMapper studentMapper;
    /**
     * @Description:
     * @param studentId  学生id
     * @param aCount   算数
     * @return: com.yyg.sbt.service.common.util.JSONResult
     * @auther: yyg
     * @date: 2019/12/9 16:16
     */
    public JSONResult updateStudent(String studentId, BigDecimal aCount){
        //根据学生编号去查询学生的基本信息,先获取版本version
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id",studentId);
        Student student = studentMapper.selectOne(queryWrapper);
        int version = student.getVersion();

        //根据条件进行信息更改
        Student student1 = new Student();
        student1.setCount(student.getCount().add(aCount));
        if(version==0){
            student1.setVersion((student.getVersion())+1);
        }else {
            student1.setVersion((student.getVersion()) - 1);
        }
        QueryWrapper<Student> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("stu_id",studentId).eq("version",version);
        int update = studentMapper.update(student1, queryWrapper1);
       /* //根据条件修改信息
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        //根据学生id和version字段进行修改
        updateWrapper.eq("stu_id",studentId).eq("version",version);
        int update = studentMapper.update(student1, updateWrapper);*/
        if(update<=0){
            return JSONResult.errorMsg("更新失败,请重新进行操作！");
        }
        return JSONResult.ok();
    }
}

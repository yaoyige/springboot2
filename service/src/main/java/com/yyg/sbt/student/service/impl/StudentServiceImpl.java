package com.yyg.sbt.student.service.impl;

import com.yyg.sbt.student.entity.Student;
import com.yyg.sbt.student.mapper.StudentMapper;
import com.yyg.sbt.student.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-12-09
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}

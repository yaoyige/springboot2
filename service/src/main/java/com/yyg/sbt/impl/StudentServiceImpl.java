package com.yyg.sbt.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyg.sbt.service.StudentService;
import com.yyg.sbt.store.domain.Student;
import com.yyg.sbt.store.mapper.StudentMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yaoyige
 * @since 2019-05-29
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ClassServiceImpl classService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    ValueOperations valueOperations;



    /**
     * 将数据缓存加入到redis中并设置过期时间
     * @param key
     * @param value
     * @return
     */
    public boolean saveRedis(String key,Object value){
        valueOperations.set("1",1);
        Boolean expire = redisTemplate.expire("1",redisTemplate.getExpire("1"), TimeUnit.HOURS);
        return expire;
    }

    /**
     * 插入学生
     * @param student 学生
     * @return 学生
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertStudent(Student student){
        try{
            int i =1;
            int j=0;
            int x = i/j;
        }catch (Exception e){
            int insert = studentMapper.insert(student);
            System.out.print("你好："+e.getMessage());
        }finally {
            int i = classService.insertStudent(student);
            return i;        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void aTest(Student student1)throws Exception{
        studentMapper.insert(student1);
        bTest();
    }

    public void bTest() throws SQLException {

            int i = 1;
            int j = 0;
            int x = i/j;
      //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

    }

    public List<Student>  conditionCreate(Page page){
      QueryWrapper<Student> queryWrapper =   new QueryWrapper<>();
       queryWrapper.between("",12,13);
       if(page.descs()==null && page.ascs()==null){

       }
        IPage<Student> studentIPage = studentMapper.selectPage(page, queryWrapper);


        List<Student> records = studentIPage.getRecords();
        return records;
    }

    /**
     * 查询学生
     * @param page
     * @return
     */
    public List<Student> queryStudent(Page page){
        QueryWrapper<Student> queryWrapper =   new QueryWrapper<>();
        queryWrapper.eq("stu_name","姚伊歌").eq("stu_sex","1");
        if(page.descs()==null && page.ascs()==null){
            page.setAsc("aSD");
        }
        IPage iPage = studentMapper.selectPage(page, queryWrapper);
        List<Student> records = iPage.getRecords();
       if(CollectionUtils.isNotEmpty(records)){
           return null;
       }
        return records;
    }

    /**
     * 获取插入数据的主键
     */
    public int insertStudentId(){
        Student student = new Student();
        student.setStuName("熊超");
        student.setStuClassId(1L);
        student.setStuBirdaty(LocalDateTime.now());
        student.setStuSex("2");
        student.setStuDepId(1L);
        int insert = studentMapper.insert(student);
        Long stuId = student.getStuId();
        return insert;

    }


}

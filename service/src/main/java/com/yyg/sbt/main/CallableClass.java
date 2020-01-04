package com.yyg.sbt.main;

import com.yyg.sbt.store.domain.Student;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Auther: yyg
 * @Date: 2019/12/25 13:48
 * @Description:
 */
@Service
public class CallableClass implements Callable {
    public String name;

    public CallableClass(String name){
        this.name = name;
    }

    SudentClass studentClass = new SudentClass();

    @Override
    public Integer call() throws Exception {
        return studentClass.getInteger(name);
    }

}

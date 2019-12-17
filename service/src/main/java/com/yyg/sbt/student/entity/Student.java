package com.yyg.sbt.student.entity;

import java.math.BigDecimal;
//import com.yyg.sbt.service.common.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-12-09
 */
@Data
//@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Student  {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    private Integer stuId;

    /**
     * 学生姓名
     */
    private String stuName;

    /**
     * 学生性别
     */
    private String stuSex;

    /**
     * 班级id
     */
    private Integer stuClassId;

    /**
     * 学院id
     */
    private Integer stuDepId;

    /**
     * 学生生日
     */
    private LocalDateTime stuBirdaty;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 计算分数综合
     */
    private BigDecimal count;


}

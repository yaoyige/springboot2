package com.yyg.sbt.store.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yaoyige
 * @since 2019-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Student对象", description="student对象")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "stu_id", type = IdType.AUTO)
    private Long stuId;

    @ApiModelProperty(value = "姓名")
    private String stuName;

    @ApiModelProperty(value = "性别")
    private String stuSex;

    @ApiModelProperty(value = "班级id")
    private Long stuClassId;

    @ApiModelProperty(value = "院系id")
    private Long stuDepId;

    @ApiModelProperty(value = "生日")
    private LocalDateTime stuBirdaty;

    @ApiModelProperty(value = "版本号")
    private int version;

    @ApiModelProperty(value = "分数综合")
    private BigDecimal count;  //测试用到
}

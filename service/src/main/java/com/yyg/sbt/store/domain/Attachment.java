package com.yyg.sbt.store.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attachment")
@ApiModel(value="Attachment对象", description="附件表")
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务 类型")
    private String bizType;

    @ApiModelProperty(value = "业务id")
    private String bizId;

    @ApiModelProperty(value = " 业务名称")
    private String name;

    @ApiModelProperty(value = "链接地址")
    private String url;

    @ApiModelProperty(value = " 预览链接地址")
    private String previewUrl;

    @ApiModelProperty(value = " 说明")
    private String comment;

    @ApiModelProperty(value = " 上传人工号")
    private Long  uploadEmpNo;

    @ApiModelProperty(value = " 上传人姓名")
    private String  uploadEmpName;

    @ApiModelProperty(value = "上传时间")
    private LocalDateTime uploadTime;

    @ApiModelProperty(value = " 修改人工号")
    private Long updateEmpNo;

    @ApiModelProperty(value = " 修改时间")
    private LocalDateTime updateTime;
}

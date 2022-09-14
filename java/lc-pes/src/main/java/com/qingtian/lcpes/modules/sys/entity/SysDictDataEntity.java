package com.qingtian.lcpes.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据字典表
 * </p>
 *
 * @author zhangxq
 * @since 2022-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SYS_DICT_DATA")
@ApiModel(value = "SysDictDataEntity对象", description = "数据字典表")
@KeySequence("SEQ_SYS_DICT_DATA")
public class SysDictDataEntity implements Serializable {

    @ApiModelProperty(value = "SEQUENCE")
    @TableId(value = "TYPE_SID", type = IdType.INPUT)
    private Long typeSid;

    @ApiModelProperty(value = "父节点ID")
    @TableField("PARENT_SID")
    private Long parentSid;

    @ApiModelProperty(value = "类型ID")
    @TableField("TYPE_CODE")
    private String typeCode;

    @ApiModelProperty(value = "类型名称")
    @TableField("TYPE_NAME")
    private String typeName;

    @ApiModelProperty(value = "类型描述")
    @TableField("TYPE_DESC")
    private String typeDesc;

    @ApiModelProperty(value = "顺序号")
    @TableField("TYPE_SEQ")
    private Integer typeSeq;

    @ApiModelProperty(value = "类型级别")
    @TableField("TYPE_LEVEL")
    private Integer typeLevel;

    @ApiModelProperty(value = "预留字段")
    private String backup1;

    @ApiModelProperty(value = "预留字段")
    private String backup2;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    @TableField("CREATED_BY")
    private String createdBy;

    @ApiModelProperty(value = "创建人名称")
    @TableField("CREATED_BY_NAME")
    private String createdByName;

    @ApiModelProperty(value = "创建时间")
    @TableField("CREATED_DT")
    private LocalDateTime createdDt;

    @ApiModelProperty(value = "更新人")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ApiModelProperty(value = "更新人名称")
    @TableField("UPDATED_BY_NAME")
    private String updatedByName;

    @ApiModelProperty(value = "版本号")
    @Version
    private Integer version;

    @ApiModelProperty(value = "公司别主键")
    @TableField("COMPANY_SID")
    private String companySid;

    @ApiModelProperty(value = "公司别编码")
    @TableField("COMPANY_CODE")
    private String companyCode;

    @ApiModelProperty(value = "公司别名称")
    @TableField("COMPANY_NAME")
    private String companyName;

    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATED_DT")
    private LocalDateTime updatedDt;

    /**
     * 备注
     */
    @TableField(exist = false)
    private List<SysDictDataEntity> children = new ArrayList<>();

    @TableField(exist = false)
    private boolean leafFlag =false;

    /* 操作动作 I 为插入、U 为更新、D 为删除 */
    @TableField(exist = false)
    private String opt;

    @TableField(exist = false)
    private String order;
}

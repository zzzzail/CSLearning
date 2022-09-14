package com.qingtian.lcpes.modules.authpre.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qingtian.lcpes.base.facade.ModelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 组织机构
 * </p>
 *
 * @author <a href="qingtian@shougang.com.cn">晴天</a>
 * @since 2022-05-17
 */
@Data
@TableName("SADP_AC_ORGNIZATION")
@ApiModel(value = "AcOrgnizationEntity对象", description = "组织机构")
@KeySequence(value = "SEQ_SADP_AC_ORGNIZATION")
@ToString
public class AcOrgnizationEntity implements Serializable {

    @ApiModelProperty(value = "SEQUENCE")
    private Long sid;

    @ApiModelProperty(value = "上级组织SID")
    @TableField("PARENT_SID")
    private Long parentSid;

    @ApiModelProperty(value = "上级组织编码")
    @TableField("PARENT_ORG_ID")
    private String parentOrgId;

    @ApiModelProperty(value = "组织编码")
    @TableId("ORG_ID")
    private String orgId;

    @ApiModelProperty(value = "组织全称")
    @TableField("ORG_FULL_NAME")
    private String orgFullName;

    @ApiModelProperty(value = "组织简称")
    @TableField("ORG_SHORT_NAME")
    private String orgShortName;

    @ApiModelProperty(value = "组织序号")
    @TableField("ORG_SEQ")
    private Integer orgSeq;

    @ApiModelProperty(value = "组织级别")
    @TableField("ORG_LEVEL")
    private Integer orgLevel;

    @ApiModelProperty(value = "乐观锁版本号")
    @Version
    private Integer version;

    @ApiModelProperty(value = "数据来源类型 1-下传 2-手工创建")
    @TableField("DATA_TYPE")
    private String dataType;

    @ApiModelProperty(value = "组织类型 10-部门 20-公司")
    @TableField("ORG_TYPE")
    private String orgType;

    @ApiModelProperty(value = "公司编码")
    @TableField("COMPANY_CODE")
    private String companyCode;

    @ApiModelProperty(value = "组织简称2")
    @TableField("ORG_SHORT_NAME2")
    private String orgShortName2;

    @ApiModelProperty(value = "产销组织编码")
    @TableField("CX_ORG_ID")
    private String cxOrgId;

    @ApiModelProperty(value = "主数据组织编码")
    @TableField("MD_ORG_ID")
    private String mdOrgId;


    @ModelField(fieldDesc = "创建人ID")
    @TableField("CREATED_BY")
    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ModelField(fieldDesc = "创建时间")
    @TableField(value = "CREATED_DT", fill = FieldFill.INSERT)
    private LocalDateTime createdDt;

    @ModelField(fieldDesc = "更新人ID")
    @TableField("UPDATED_BY")
    private String updatedBy;

    @ModelField(fieldDesc = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "UPDATED_DT", fill = FieldFill.UPDATE)
    private LocalDateTime updatedDt;

    /**
     * 作业部标识   0否  |    1是   |   2 不区分
     */
    @ModelField(fieldDesc = "作业部标识")
    @TableField("ORG_FLAG")
    private Integer orgFlag;
    /**
     * 子节点
     */
    @TableField(exist = false)
    private List<AcOrgnizationEntity> childNode;



    @TableField(exist = false)
    private int pageNum = 1;
    @TableField(exist = false)
    private int pageSize = 10;
}

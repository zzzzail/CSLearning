package com.qingtian.lcpes.modules.authpre.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qingtian.lcpes.base.facade.ModelField;
import com.qingtian.lcpes.base.facade.QueryCondition;
import com.qingtian.lcpes.base.facade.QueryConditionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 岗位表
 * </p>
 *
 * @author <a href="qingtian@shougang.com.cn">晴天</a>
 * @since 2022-05-20
 */
@Data
@TableName("SADP_AC_GROUP")
@ApiModel(value = "AcGroupEntity对象", description = "岗位表")
@KeySequence(value = "SEQ_SADP_AC_GROUP")
@ToString
public class AcGroupEntity implements Serializable {

    @ApiModelProperty(value = "SEQUENCE")
    @TableId(value = "SID", type = IdType.INPUT)
    private Long sid;

    @ApiModelProperty(value = "岗位编码")
    @TableField("GROUP_ID")
    @QueryCondition(value = QueryConditionEnum.LIKE)
    private String groupId;

    @ApiModelProperty(value = "岗位名称")
    @TableField("GROUP_NAME")
    @QueryCondition(value = QueryConditionEnum.LIKE)
    private String groupName;

    @ApiModelProperty(value = "岗位描述")
    @TableField("GROUP_DESC")
    private String groupDesc;

    @ApiModelProperty(value = "所属组织机构")
    @TableField("ORG_SID")
    private Long orgSid;

    @ApiModelProperty(value = "乐观锁版本号")
    @Version
    private Integer version;

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


    @TableField(exist = false)
    private int pageNum = 1;
    @TableField(exist = false)
    private int pageSize = 10;

    @TableField(exist = false)
    private Long parentOrgSid;

    @TableField(exist = false)
    private String orgName;


}

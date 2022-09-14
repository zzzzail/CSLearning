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

/**
 * <p>
 *
 * </p>
 *
 * @author <a href="qingtian@shougang.com.cn">晴天</a>
 * @since 2022-05-17
 */
@Data
@TableName("SADP_AC_USER")
@ApiModel(value = "AcUserEntity对象", description = "")
@KeySequence(value = "SEQ_SADP_AC_USER")
@ToString
public class AuthUserEntity implements Serializable {

    @ApiModelProperty(value = "SEQUENCE")
    @TableId(value = "SID", type = IdType.INPUT)
    private Long sid;

    @ApiModelProperty(value = "登录ID")
    @TableField("LOGIN_ID")
    private String loginId;

    @ApiModelProperty(value = "姓名全拼")
    @TableField("USER_FULL_NAME_PY")
    private String userFullNamePy;

    @ApiModelProperty(value = "中文姓名")
    @TableField("USER_NAME_CN")
    private String userNameCn;

    @ApiModelProperty(value = "用户类型")
    @TableField("USER_TYPE")
    private String userType;

    @ApiModelProperty(value = "身份证号")
    @TableField("IDENTITY_ID")
    private String identityId;

    @ApiModelProperty(value = "组织机构ID")
    @TableField("ORG_ID")
    private String orgId;

    @ApiModelProperty(value = "组织机构SID")
    @TableField("ORG_SID")
    private Long orgSid;

    @ApiModelProperty(value = "岗位标识")
    @TableField("USER_DUTY")
    private String userDuty;

    @ApiModelProperty(value = "岗位Sid")
    @TableField("USER_POST_SID")
    private Long userPostSid;

    /*@ApiModelProperty(value = "岗位Id")
    @TableField("USER_POST_ID")*/
    @TableField(exist = false)
    private String userPostId;

    @ApiModelProperty(value = "岗位名称")
    @TableField("USER_POST_NAME")
    private String userPostName;

    @ApiModelProperty(value = "邮箱")
    @TableField("EMAIL_ADDR")
    private String emailAddr;

    @ApiModelProperty(value = "手机号")
    @TableField("MOBILE_PHONE")
    private String mobilePhone;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "有效标志 0-禁用 1-有效")
    @TableField("VALID_FLAG")
    private String validFlag;

    @ApiModelProperty(value = "数据来源类型 1-下传 2-手工创建")
    @TableField("DATA_TYPE")
    private String dataType;

    @ApiModelProperty(value = "班时")
    @TableField("SHIFT_ID")
    private String shiftId;

    @ApiModelProperty(value = "班组")
    @TableField("GROUP_ID")
    private String groupId;

    @ApiModelProperty(value = "主题类型")
    @TableField("THEME_TYPE")
    private String themeType;

    @ApiModelProperty(value = "乐观锁版本号")
    @Version
    private Integer version;

    @ApiModelProperty(value = "职务职级代码")
    @TableField("LEVEL_CODE")
    private String levelCode;

    @ApiModelProperty(value = "职务职级")
    @TableField("LEVEL_NAME")
    private String levelName;

    @ApiModelProperty(value = "启用/禁用标识 0-禁用 1-启用")
    @TableField("IDENTITY_FLAG")
    private String identityFlag;

    @ApiModelProperty(value = "冻结/解冻时间")
    @TableField("IDENTITY_DT")
    private LocalDateTime identityDt;

    @ApiModelProperty(value = "有效/无效时间")
    @TableField("VALID_DT")
    private LocalDateTime validDt;

    @ApiModelProperty(value = "1-人力 2-主数据")
    @TableField("FROM_FLAG")
    private String fromFlag;


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


}

package com.qingtian.lcpes.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("SYS_LOG")
@ApiModel(value = "SysLogEntity对象", description = "系统日志表")
@KeySequence("SEQ_SYS_LOG")
public class SysLogEntity implements Serializable {

    @TableId(value = "LOG_ID", type = IdType.INPUT)
    private String logId;

    @TableField("LOG_TYPE")
    private String logType;

    @TableField("LOG_CONTENT")
    private String logContent;

    @TableField("OPERATE_TYPE")
    private String operateType;

    @TableField("USER_ID")
    private String userId;

    private String ip;

    private String method;

    @TableField("USER_AGENT")
    private String userAgent;

    @TableField("CLIENT_ID")
    private String clientId;

    @TableField("REQUEST_URL")
    private String requestUrl;

    @TableField("REQUEST_PARAM")
    private String requestParam;

    @TableField("REQUEST_TYPE")
    private String requestType;

    @TableField("OPERATE_RESULT")
    private String operateResult;

    @TableField("COST_TIME")
    private BigDecimal costTime;

    @TableField("CREATED_BY")
    private String createdBy;

    @TableField("CREATED_BY_NAME")
    private String createdByName;

    @TableField("CREATED_DT")
    private LocalDateTime createdDt;

    @TableField("UPDATED_BY")
    private String updatedBy;

    @TableField("UPDATED_BY_NAME")
    private String updatedByName;

    @TableField("UPDATED_DT")
    private LocalDateTime updatedDt;

    @TableField("RESULT_MSG")
    private String resultMsg;


}

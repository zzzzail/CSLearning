package com.qingtian.lcpes.modules.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qingtian.lcpes.base.facade.BaseVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysLogEntity vo对象", description = "系统日志表")
public class SysLogVO extends BaseVO {
    private String logId;

    private String logType;

    private String logContent;

    private String operateType;

    private String userId;

    private String ip;

    private String method;

    private String userAgent;

    private String clientId;

    private String requestUrl;

    private String requestParam;

    private String requestType;

    private String operateResult;

    private BigDecimal costTime;

    private String createdBy;

    private String createdByName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDt;

    private String updatedBy;

    private String updatedByName;

    private LocalDateTime updatedDt;

    private String resultMsg;


}

package com.qingtian.lcpes.modules.mq.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qingtian.lcpes.base.facade.BaseVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息历史表
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MqMsgHEntity vo对象", description = "消息历史表")
public class MqMsgHVO extends BaseVO {
    private BigDecimal sid;

    private BigDecimal msgSid;

    private String msgId;

    private Integer msgState;

    private String msg;

    private String exception;

    private String messageId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime sendDt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime receiveDt;

    private String createdBy;

    private String createdByName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDt;

    private String updatedBy;

    private String updatedByName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedDt;

    private Integer version;

    private Long msgNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime retryDt;

    private String msgName;

}

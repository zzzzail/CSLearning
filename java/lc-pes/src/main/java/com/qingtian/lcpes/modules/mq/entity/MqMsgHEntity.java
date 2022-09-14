package com.qingtian.lcpes.modules.mq.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
@TableName("MQ_MSG_H")
@ApiModel(value = "MqMsgHEntity对象", description = "消息历史表")
@KeySequence("SEQ_MQ_MSG_H")
public class MqMsgHEntity implements Serializable {

    @TableId(value = "SID", type = IdType.INPUT)
    private Long sid;

    @TableField("MSG_SID")
    private Long msgSid;

    @TableField("MSG_ID")
    private String msgId;

    @TableField("MSG_STATE")
    private Integer msgState;

    private String msg;

    private String exception;

    @TableField("MESSAGE_ID")
    private String messageId;

    @TableField("SEND_DT")
    private LocalDateTime sendDt;

    @TableField("RECEIVE_DT")
    private LocalDateTime receiveDt;

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

    @Version
    private Integer version;

    @TableField("MSG_NUM")
    private Long msgNum;

    @TableField("RETRY_DT")
    private LocalDateTime retryDt;

    @TableField("MSG_NAME")
    private String msgName;


}

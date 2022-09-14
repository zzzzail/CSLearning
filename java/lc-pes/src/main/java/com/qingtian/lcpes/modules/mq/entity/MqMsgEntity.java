package com.qingtian.lcpes.modules.mq.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MQ_MSG")
@ApiModel(value = "MqMsgEntity对象", description = "消息表")
@KeySequence("SEQ_MQ_MSG")
public class MqMsgEntity implements Serializable {

    @TableId(value = "SID", type = IdType.INPUT)
    private Long sid;

    @TableField("MSG_ID")
    private String msgId;

    @TableField("MESSAGE_ID")
    private String messageId;

    @TableField("SEND_DT")
    private LocalDateTime sendDt;

    @TableField("RECEIVE_DT")
    private LocalDateTime receiveDt;

    private String msg;

    @TableField("MSG_STATE")
    private Integer msgState;

    @TableField("MSG_NUM")
    private Long msgNum;

    @TableField("RETRY_DT")
    private LocalDateTime retryDt;

    @TableField("REMARK_03")
    private String remark03;

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

    private String exception;

    @TableField("MSG_NAME")
    private String msgName;


}

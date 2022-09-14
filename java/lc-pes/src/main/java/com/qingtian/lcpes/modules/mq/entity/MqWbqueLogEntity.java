package com.qingtian.lcpes.modules.mq.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 运单消息队列日志表
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MQ_WBQUE_LOG")
@ApiModel(value = "MqWbqueLogEntity对象", description = "运单消息队列日志表")
@KeySequence("SEQ_MQ_WBQUE_LOG")
public class MqWbqueLogEntity implements Serializable {

    @TableId(value = "SID", type = IdType.INPUT)
    private Long sid;

    @TableField("MSG_TYPE_ID")
    private String msgTypeId;

    @TableField("MSG_DT")
    private LocalDateTime msgDt;

    @TableField("MSG_STATUS")
    private Integer msgStatus;

    @TableField("RETRY_NUM")
    private Integer retryNum;

    @TableField("NEXT_RETRY_DT")
    private LocalDateTime nextRetryDt;

    private String msg;

    private String result;

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

    @TableField("REMOVE_STATUS")
    @TableLogic
    private Integer removeStatus;

    private String remark;

    private String remark01;

    private String remark02;

    private String remark03;

    private String remark04;

    private String remark05;

    @TableField("NEED_RETRY")
    private Integer needRetry;

    @TableField("QUEUE_NAME")
    private String queueName;

    @TableField("MESSAGE_ID")
    private String messageId;

    @TableField("DL_REASON")
    private String dlReason;

    @TableField("EXCHANGE_NAME")
    private String exchangeName;

    @TableField("RETRY_LAST_TIME")
    private LocalDateTime retryLastTime;


}

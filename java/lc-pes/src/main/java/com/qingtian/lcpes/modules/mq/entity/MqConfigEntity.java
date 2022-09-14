package com.qingtian.lcpes.modules.mq.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息配置表
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MQ_CONFIG")
@ApiModel(value = "MqConfigEntity对象", description = "消息配置表")
@KeySequence("SEQ_MQ_CONFIG")
public class MqConfigEntity implements Serializable {

    @TableId(value = "SID", type = IdType.INPUT)
    private BigDecimal sid;

    @TableField("MSG_ID")
    private String msgId;

    @TableField("MSG_NAME")
    private String msgName;

    @TableField("MSG_TYPE")
    private Integer msgType;

    @TableField("MSG_SENDER")
    private String msgSender;

    @TableField("MSG_RECEIVE")
    private String msgReceive;

    private String queue;

    @TableField("SERVICE_ID")
    private String serviceId;

    private String uri;

    @TableField("CLASS_NAME")
    private String className;

    private String police;

    @TableField("HOOK_ADDRESS")
    private String hookAddress;

    @TableField("REMARK_01")
    private String remark01;

    @TableField("REMARK_02")
    private String remark02;

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


}

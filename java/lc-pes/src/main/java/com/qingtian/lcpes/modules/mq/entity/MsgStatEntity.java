package com.qingtian.lcpes.modules.mq.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 消息统计表
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("MSG_STAT")
@ApiModel(value = "MsgStatEntity对象", description = "消息统计表")
@KeySequence("SEQ_MSG_STAT")
public class MsgStatEntity implements Serializable {

    @TableId(value = "SID", type = IdType.INPUT)
    private Long sid;

    @TableField("PLATFORM_TYPE")
    private String platformType;

    @TableField("STATISTICS_TYPE")
    private String statisticsType;

    @TableField("MESSAGE_STATUS")
    private Integer messageStatus;

    @TableField("DATE_NUM")
    private Long dateNum;

    private Long num;

    @TableField("CREATED_DT")
    private LocalDateTime createdDt;

    @TableField("UPDATED_DT")
    private LocalDateTime updatedDt;


}

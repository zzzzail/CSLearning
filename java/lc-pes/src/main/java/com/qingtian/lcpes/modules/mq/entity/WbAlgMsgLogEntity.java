package com.qingtian.lcpes.modules.mq.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 算法接口日志表
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("WB_ALG_MSG_LOG")
@ApiModel(value = "WbAlgMsgLogEntity对象", description = "算法接口日志表")
@KeySequence("SEQ_WB_ALG_MSG_LOG")
public class WbAlgMsgLogEntity implements Serializable {

    @TableId(value = "SID", type = IdType.INPUT)
    private Long sid;

    @TableField("MSG_API")
    private String msgApi;

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


}

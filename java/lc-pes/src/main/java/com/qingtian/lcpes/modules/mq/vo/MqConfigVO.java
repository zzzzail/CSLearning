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
 * 消息配置表
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MqConfigEntity vo对象", description = "消息配置表")
public class MqConfigVO extends BaseVO {
    private BigDecimal sid;

    private String msgId;

    private String msgName;

    private Integer msgType;

    private String msgSender;

    private String msgReceive;

    private String queue;

    private String serviceId;

    private String uri;

    private String className;

    private String police;

    private String hookAddress;

    private String remark01;

    private String remark02;

    private String remark03;

    private String createdBy;

    private String createdByName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDt;

    private String updatedBy;

    private String updatedByName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedDt;

    private Integer version;


}

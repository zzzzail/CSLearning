package com.qingtian.lcpes.modules.mq.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qingtian.lcpes.base.facade.BaseVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MsgStatEntity vo对象", description = "消息统计表")
public class MsgStatVO extends BaseVO {

    public static final String MSG_ALL_STR = "所有消息";

    private Long sid;

    private String platformType;

    private String statisticsType;

    private Integer messageStatus;

    private Long dateNum;

    private Long num;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedDt;


}

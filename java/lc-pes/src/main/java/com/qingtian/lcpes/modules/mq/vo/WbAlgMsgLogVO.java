package com.qingtian.lcpes.modules.mq.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qingtian.lcpes.base.facade.BaseVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "WbAlgMsgLogEntity vo对象", description = "算法接口日志表")
public class WbAlgMsgLogVO extends BaseVO {
    private Long sid;

    private String msgApi;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime msgDt;

    private Integer msgStatus;

    private Integer retryNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime nextRetryDt;

    private String msg;

    private String result;

    private String createdBy;

    private String createdByName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdDt;

    private String updatedBy;

    private String updatedByName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedDt;

    private Integer version;

    private Integer removeStatus;

    private String remark;

    private String remark01;

    private String remark02;

    private String remark03;

    private String remark04;

    private String remark05;

    private Integer needRetry;


}

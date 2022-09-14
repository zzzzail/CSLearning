package com.qingtian.lcpes.modules.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qingtian.lcpes.base.facade.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 公告管理表
 * </p>
 *
 * @author zhangxq
 * @since 2022-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CommonNoticeEntity vo对象", description = "公告管理表")
public class CommonNoticeVO extends BaseVO {
    private Long sid;

    private Integer noticeType;

    private String title;

    private String content;

    private String sendTo;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime sendTime;

    private String remark;

    private String remark01;

    private String remark02;

    private String remark03;

    private String remark04;

    private String remark05;

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

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "指定时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime specifyTime;

    @ApiModelProperty(value = "指定规则")
    private Integer specifyRule;

}

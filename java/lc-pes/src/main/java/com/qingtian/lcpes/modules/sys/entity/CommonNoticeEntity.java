package com.qingtian.lcpes.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
@TableName("COMMON_NOTICE")
@ApiModel(value = "CommonNoticeEntity对象", description = "公告管理表")
@KeySequence("SEQ_COMMON_NOTICE")
public class CommonNoticeEntity implements Serializable {

    @TableId(value = "SID", type = IdType.INPUT)
    private Long sid;

    @TableField("NOTICE_TYPE")
    private Integer noticeType;

    private String title;

    private String content;

    @TableField("SEND_TO")
    private String sendTo;

    private Integer status;

    @TableField("SEND_TIME")
    private LocalDateTime sendTime;

    private String remark;

    private String remark01;

    private String remark02;

    private String remark03;

    private String remark04;

    private String remark05;

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


}

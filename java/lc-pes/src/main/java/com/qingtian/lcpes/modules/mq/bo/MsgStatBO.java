package com.qingtian.lcpes.modules.mq.bo;

import com.qingtian.lcpes.modules.mq.enums.MsgStatPlatformTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zhangxq
 * @date 2022/8/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class MsgStatBO {

    /* 小时 时间数格式化模版字符串 */
    public static final String HOUR_DNF_PATTERN_4ORACLE = "YYYYMMDDHH24";

    public static final String HOUR_DNF_PATTERN_4J = "yyyyMMddHH";

    /**
     * 平台分类【msg：消息平台；alg：算法平台】
     *
     * @see MsgStatPlatformTypeEnum
     */
    private String platformType;

    /* 消息状态【0：失败；1：成功；】 */
    private Integer messageStatus;

    /* 开始时间 */
    private LocalDateTime startTime;

    /* 结束时间 */
    private LocalDateTime endTime;

    /* 时间数开始 */
    private Long dateNumStart;

    /* 时间数结束*/
    private Long dateNumEnd;

}

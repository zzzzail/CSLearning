package com.qingtian.lcpes.modules.mq.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 消息统计简易统计报告
 *
 * @author zhangxq
 * @date 2022/8/24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class MsgStatSummaryStatisticsVO {

    // 消息总数量
    private Long sum;

    // 本周消息数
    private Long sumThisWeek;

    // 今日消息数
    private Long sumToday;

    // 今日消息发送成功率
    private Float successRateToday;

    // 死信总数量
    private Long sumDl;

    /* 预留字段 */
    private String remark01;

    /* 预留字段 */
    private String remark02;

    /* 预留字段 */
    private String remark03;

    /* 预留字段 */
    private String remark04;

    /* 预留字段 */
    private String remark05;


}

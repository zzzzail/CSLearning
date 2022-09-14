package com.qingtian.lcpes.modules.mq.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhangxq
 * @since 2022/9/5
 */
@Data
public class MsgStatCountBO {

    /* 小时 时间数格式化模版字符串 */
    public static final String HOUR_DNF_PATTERN = "YYYYMMDDHH24";

    /* 开始时间 */
    private LocalDateTime startTime;

    /* 结束时间 */
    private LocalDateTime endTime;

    /* 消息状态  1 未处理 |  2 失败 | 3 成功 */
    private Integer msgState;

    /* 时间数格式化模版 */
    private String dateNumFormatPattern;
}

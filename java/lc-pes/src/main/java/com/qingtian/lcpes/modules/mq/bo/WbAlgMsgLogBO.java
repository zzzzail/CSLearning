package com.qingtian.lcpes.modules.mq.bo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 算法消息统计 BO
 *
 * @author zhangxq
 * @date 2022/8/24
 */
@Data
public class WbAlgMsgLogBO {

    /* 小时 时间数格式化模版字符串 */
    public static final String DATA_NUM_PATTERN_FOR_ORACLE = "YYYYMMDDHH24";

    /* 开始时间 */
    private LocalDateTime startTime;

    /* 结束时间 */
    private LocalDateTime endTime;

    /* 消息状态：0-未处理；1-成功；2-失败；3-终止 */
    private Integer msgStatus;

    /* 时间数格式化模版 */
    private String dateNumFormatPattern;

}

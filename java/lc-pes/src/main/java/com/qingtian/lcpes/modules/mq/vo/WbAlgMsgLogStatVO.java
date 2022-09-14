package com.qingtian.lcpes.modules.mq.vo;

import lombok.Data;

/**
 * 消息分析 VO
 *
 * @author zhangxq
 * @date 2022/8/24
 */
@Data
public class WbAlgMsgLogStatVO {

    /* 时间数 */
    private Long dateNum;

    /* 消息状态：0-未处理；1-成功；2-失败；3-终止 */
    private Integer msgStatus;

    /* 统计数 */
    private Long count;

}

package com.qingtian.lcpes.modules.mq.vo;

import lombok.Data;

/**
 * @author zhangxq
 * @since 2022/9/5
 */
@Data
public class MsgStatCountVO {
    /* 消息状态【2：失败；3：成功；】*/
    private Integer msgState;

    /* 消息发送方 */
    private String msgSender;

    /* 消息接收方 */
    private String msgReceiver;

    /* 时间数 */
    private Long dateNum;

    /* 统计数 */
    private Long count;
}

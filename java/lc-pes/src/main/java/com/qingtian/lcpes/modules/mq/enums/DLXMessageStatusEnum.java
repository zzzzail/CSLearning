package com.qingtian.lcpes.modules.mq.enums;

import lombok.Getter;

/**
 * 死信队列消息的状态 枚举
 *
 * @author zhangxq
 * @date 2022/8/12
 */
@Getter
public enum DLXMessageStatusEnum {

    UNSEND(0, "未处理"),
    SEND_SUCCESS(1, "成功"),
    SEND_FAIL(2, "失败"),
    SEND_FINISHED(3, "终止"),
    SEND_REPEATED(4, "重复处理"),

    ;

    private final Integer code;

    private final String name;

    DLXMessageStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}

package com.qingtian.lcpes.modules.mq.enums;

import lombok.Getter;

/**
 * 消息状态枚举
 *
 * @author zhangxq
 * @date 2022/8/26
 */
@Getter
public enum MsgStateEnum {

    UNPROCESS(1, "未处理"),

    FAIL(2, "失败"),

    SUCCESS(3, "成功"),

    ;

    private final Integer code;

    private final String name;

    MsgStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}

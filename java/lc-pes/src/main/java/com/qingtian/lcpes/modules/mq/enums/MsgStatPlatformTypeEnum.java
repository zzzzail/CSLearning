package com.qingtian.lcpes.modules.mq.enums;

import lombok.Getter;

/**
 * 平台类型
 *
 * @author zhangxq
 * @date 2022/8/22
 */
@Getter
public enum MsgStatPlatformTypeEnum {

    MSG("msg", "消息平台"),

    ALG("alg", "算法平台"),

    ;

    private final String code;

    private final String name;

    MsgStatPlatformTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}

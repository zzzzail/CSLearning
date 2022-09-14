package com.qingtian.lcpes.base.facade;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;

import java.lang.reflect.Method;

/**
 * @author zhangxq
 * @since 2022/8/31
 */
public enum QueryConditionEnum {
    EQ("eq"),
    NE("ne"),
    GT("gt"),
    GE("ge"),
    LT("lt"),
    LE("le"),
    LIKE("like"),
    NOT_LIKE("notLike"),
    LIKE_LEFT("likeLeft"),
    LIKE_RIGHT("likeRight");

    private String name;
    private Method method;

    private QueryConditionEnum(String name) {
        this.name = name;
        try {
            Method method = AbstractWrapper.class.getDeclaredMethod(name, Boolean.TYPE, Object.class, Object.class);
            this.method = method;
        }
        catch (NoSuchMethodException e) {
        }
    }

    public Method getMethod() {
        return this.method;
    }
}

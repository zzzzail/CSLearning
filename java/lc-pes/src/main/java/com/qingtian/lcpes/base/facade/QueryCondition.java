package com.qingtian.lcpes.base.facade;

import java.lang.annotation.*;

/**
 * @author zhangxq
 * @since 2022/8/31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface QueryCondition {
    QueryConditionEnum value() default QueryConditionEnum.EQ;
    boolean isCondition() default true;
}

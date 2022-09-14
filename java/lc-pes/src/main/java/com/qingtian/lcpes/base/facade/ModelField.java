package com.qingtian.lcpes.base.facade;

/**
 * @author zhangxq
 * @since 2022/8/31
 */
public @interface ModelField {
    String fieldDesc() default "字段中文描述";
    String tableFieldName() default "数据表字段名";
}

package com.java.designpattern.interpreter;

/**
 * 用于处理自定义 xml 取值表达式的接口
 *
 * @author zhangxq
 * @since 2022/9/19
 */
public abstract class ReadXmlExpression {
    
    public abstract String[] interpret(Context context);

}

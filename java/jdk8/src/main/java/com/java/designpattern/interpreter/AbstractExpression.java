package com.java.designpattern.interpreter;

/**
 * @author zhangxq
 * @since 2022/9/19
 */
public abstract class AbstractExpression {
    
    /**
     * 解释操作
     *
     * @param context 上下文对象
     */
    public abstract void interpret(Context context);
}

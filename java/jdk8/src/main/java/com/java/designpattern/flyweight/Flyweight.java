package com.java.designpattern.flyweight;

/**
 * @author zhangxq
 * @since 2022/9/14
 */
public interface Flyweight {
    
    /**
     * 示例操作，传入外部状态
     * @param extrinsicState 示例参数，外部状态
     */
    void operation(String extrinsicState);
}

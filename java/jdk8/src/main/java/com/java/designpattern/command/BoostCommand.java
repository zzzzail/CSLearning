package com.java.designpattern.command;

public interface BoostCommand {
    
    /**
     * 启动方法
     *
     * @param args 自定义参数
     */
    void boost(String... args);
    
}
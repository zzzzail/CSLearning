package com.java.designpattern.command;

/**
 * @author zhangxq
 * @since 2022/9/13
 */
public class Main {
    
    public static void main(String[] args) {
        // 这一步是启动之前就定好的
        BoostCommand bc = new ABoostCommand();
        StartButton sb = new StartButton();
        sb.init(bc);
        
        // 点用户点击开始按钮就可以运行了
        sb.start();
    }
}

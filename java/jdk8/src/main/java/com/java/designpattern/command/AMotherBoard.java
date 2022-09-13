package com.java.designpattern.command;

/**
 * @author zhangxq
 * @since 2022/9/13
 */
public class AMotherBoard implements Receiver {
    @Override
    public void action() {
        System.out.println("A 品牌的主板接收到命令开始启动。。。");
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("启动完成。");
    }
}

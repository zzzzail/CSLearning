package com.java.designpattern.bridge;

/**
 * @author zhangxq
 * @since 2022/9/20
 */
public class EmailMessageSender implements MessageSender {
    @Override
    public boolean send(String message) {
        System.out.println("使用邮件的方式发送消息`" + message + "`");
        return true;
    }
}

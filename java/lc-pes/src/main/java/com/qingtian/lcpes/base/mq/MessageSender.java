package com.qingtian.lcpes.base.mq;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public interface MessageSender {

    boolean send(Message<?> message);

}

package com.qingtian.lcpes.base.mq;


/**
 * @author zhangxq
 * @since 2022/8/30
 */
public interface MessageReceiver {

    void receive(Message<?> message);

}

package com.java.designpattern.bridge;

/**
 * @author zhangxq
 * @since 2022/9/20
 */
public class UrgencyMessageSender extends AbstractMessageSenderBridge {
    public UrgencyMessageSender(MessageSender messageSender) {
        super(messageSender);
    }
    
    @Override
    public boolean sendMessage(String message) {
        message = "【加急】" + message;
        return super.sendMessage(message);
    }
    
    /**
     * 扩展自己的新功能，监控某消息的处理过程
     *
     * @param messageId
     * @return
     */
    public Object watch(String messageId) {
        // 获取相应的对象，组织成监控的数据对象，然后返回
        return null;
    }
}

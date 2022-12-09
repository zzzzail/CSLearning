package designpattern.bridge;

/**
 * @author zhangxq
 * @since 2022/9/20
 */
public abstract class AbstractMessageSenderBridge {
    
    private MessageSender messageSender;
    
    public AbstractMessageSenderBridge(MessageSender messageSender) {
        this.messageSender = messageSender;
    }
    
    /**
     * 发送消息（转掉实现的部分）
     *
     * @param message
     * @return
     */
    public boolean sendMessage(String message) {
        return messageSender.send(message);
    }
}

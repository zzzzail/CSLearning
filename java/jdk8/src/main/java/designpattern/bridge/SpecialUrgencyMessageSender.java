package designpattern.bridge;

/**
 * @author zhangxq
 * @since 2022/9/20
 */
public class SpecialUrgencyMessageSender extends AbstractMessageSenderBridge {
    public SpecialUrgencyMessageSender(MessageSender messageSender) {
        super(messageSender);
    }
    
    public void hurry(String messageId) {
        // 执行催促的业务，发出催促的消息
    }
    
    @Override
    public boolean sendMessage(String message) {
        message = "【特急】" + message;
        return super.sendMessage(message);
    }
}

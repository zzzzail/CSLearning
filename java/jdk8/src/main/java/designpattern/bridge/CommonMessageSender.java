package designpattern.bridge;

/**
 * @author zhangxq
 * @since 2022/9/20
 */
public class CommonMessageSender extends AbstractMessageSenderBridge {
    public CommonMessageSender(MessageSender messageSender) {
        super(messageSender);
    }
    
    @Override
    public boolean sendMessage(String message) {
        return super.sendMessage(message);
    }
}

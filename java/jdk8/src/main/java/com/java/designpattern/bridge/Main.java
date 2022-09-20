package com.java.designpattern.bridge;

/**
 * @author zhangxq
 * @since 2022/9/20
 */
public class Main {
    
    public static void main(String[] args) {
        MessageSender smsMessageSender = new SMSMessageSender();
        MessageSender emailMessageSender = new EmailMessageSender();
    
        AbstractMessageSenderBridge bridge1 = new CommonMessageSender(smsMessageSender);
        AbstractMessageSenderBridge bridge2 = new UrgencyMessageSender(smsMessageSender);
        AbstractMessageSenderBridge bridge3 = new SpecialUrgencyMessageSender(smsMessageSender);
        AbstractMessageSenderBridge bridge4 = new CommonMessageSender(emailMessageSender);
        AbstractMessageSenderBridge bridge5 = new UrgencyMessageSender(emailMessageSender);
        AbstractMessageSenderBridge bridge6 = new SpecialUrgencyMessageSender(emailMessageSender);
    
        bridge3.sendMessage("Hello 3");
        ((SpecialUrgencyMessageSender) bridge3).hurry("Hello 3");
        bridge5.sendMessage("Hello 6");
    }
}

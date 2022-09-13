package com.java.designpattern.command;

/**
 * @author zhangxq
 * @since 2022/9/13
 */
public class ConcreteCommand implements Command {
    
    private Receiver receiver;
    
    // 命令有相应的状态
    private String state;
    
    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    
    @Override
    public void exec() {
        receiver.action();
    }
}

package designpattern.command;

/**
 * @author zhangxq
 * @since 2022/9/13
 */
public class Invoker {
    
    private Command command;
    
    public Invoker(Command command) {
        this.command = command;
    }
    
    public void run() {
        command.exec();
    }
}

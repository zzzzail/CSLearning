package designpattern.command;

/**
 * @author zhangxq
 * @since 2022/9/13
 */
public class Client {
    
    public static Invoker INVOKER;
    
    public static void assemble() {
        Receiver receiver = new MyReceiver();
        Command command = new ConcreteCommand(receiver);
        INVOKER = new Invoker(command);
    }
    
    public static void assembleA() {
        Receiver receiver = new AMotherBoard();
        Command command = new ConcreteCommand(receiver);
        INVOKER = new Invoker(command);
    }
}

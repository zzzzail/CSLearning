package designpattern.command;

/**
 * @author zhangxq
 * @since 2022/9/13
 */
public class Main {
    
    public static void main(String[] args) {
        Client.assembleA(); // 组装电脑
        Invoker invoker = Client.INVOKER;
        // 开机
        invoker.run();
    }
}

package designpattern.command;

/**
 * @author zhangxq
 * @since 2022/9/13
 */
public class MyReceiver implements Receiver {
    @Override
    public void action() {
        System.out.println("MyReceiver 接收到动作请求");
        System.out.println("MyReceiver 开始启动。。。");
        try {
            Thread.sleep(10);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("MyReceiver 启动完成。");
    }
}

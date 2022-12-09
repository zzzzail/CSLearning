package designpattern.proxy.cglibproxy;

/**
 * @author zail
 * @date 2022/5/25
 */
public class Main {
    
    public static void main(String[] args) {
        AliSmsSender sender = CglibProxyFactory.getProxy(AliSmsSender.class);
        sender.send();
    }
}

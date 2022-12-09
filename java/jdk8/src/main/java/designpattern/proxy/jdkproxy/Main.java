package designpattern.proxy.jdkproxy;

public class Main {
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.sendMsg("java");
        
        System.out.println();
        
        // 这样代理更方便
        smsService = JdkProxyFactory.getProxyByClass(SmsServiceImpl.class);
        smsService.sendMsg("c++");
    }
}

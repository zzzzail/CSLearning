package designpattern.mediator;

/**
 * @author zhangxq
 * @since 2022/9/7
 */
public class ServiceImpl implements Service {
    
    private Object cpuService;
    
    private Object gpuService;
    
    @Override
    public void doSomething() {
        // cpuService.doSomething();
        // gpuService.doSomething();
        // ...
        // Service 服务就是一个 Mediator 中介者
    }
}

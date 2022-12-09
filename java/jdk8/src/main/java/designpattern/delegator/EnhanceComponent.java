package designpattern.delegator;

/**
 * @author zhangxq
 * @since 2022/9/19
 */
public class EnhanceComponent implements Delegator<Component> {
    
    private Component delegate;
    
    public EnhanceComponent(Component delegate) {
        this.delegate = delegate;
    }
    
    @Override
    public Component getDelegate() {
        return delegate;
    }
    
    @Override
    public void enhanceFun() {
        System.out.println("EnhanceComponent::enhanceFun");
        delegate.fun();
    }
}

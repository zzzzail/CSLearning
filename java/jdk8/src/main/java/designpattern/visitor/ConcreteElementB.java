package designpattern.visitor;

/**
 * @author zhangxq
 * @since 2022/9/20
 */
public class ConcreteElementB extends Element {
    
    private String str;
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementB(this);
    }
    
    public void operationB() {
        System.out.println("ConcreteElementB::operationB");
    }
}

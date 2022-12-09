package designpattern.visitor;

/**
 * @author zhangxq
 * @since 2022/9/20
 */
public class ConcreteElementA extends Element {
    
    private String str;
    
    @Override
    public void accept(Visitor visitor) {
        // 回调访问者对象的相应方法
        visitor.visitConcreteElementA(this);
    }
    
    public void operationA() {
        System.out.println("ConcreteElementA::operationA");
    }
}

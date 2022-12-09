package designpattern.visitor;

/**
 * 访问者接口
 *
 * @author zhangxq
 * @since 2022/9/20
 */
public interface Visitor {
    
    /**
     * 访问元素 A，相当于给元素 A 添加访问者的功能
     *
     * @param elementA 元素 A 对象
     */
    void visitConcreteElementA(ConcreteElementA elementA);
    
    /**
     * 访问元素 B，相当于给元素 B 添加访问者的功能
     *
     * @param elementB 元素 B 对象
     */
    void visitConcreteElementB(ConcreteElementB elementB);
}

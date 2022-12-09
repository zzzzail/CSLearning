package designpattern.visitor;

/**
 * @author zhangxq
 * @since 2022/9/20
 */
public abstract class Element {
    
    /**
     * 接收访问者的访问
     * @param visitor
     */
    public abstract void accept(Visitor visitor);
    
}

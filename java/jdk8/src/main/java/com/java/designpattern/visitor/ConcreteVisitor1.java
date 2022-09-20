package com.java.designpattern.visitor;

/**
 * @author zhangxq
 * @since 2022/9/20
 */
public class ConcreteVisitor1 implements Visitor {
    @Override
    public void visitConcreteElementA(ConcreteElementA elementA) {
        elementA.operationA();
    }
    
    @Override
    public void visitConcreteElementB(ConcreteElementB elementB) {
        elementB.operationB();
    }
}

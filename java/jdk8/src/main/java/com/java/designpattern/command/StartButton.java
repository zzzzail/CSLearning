package com.java.designpattern.command;

/**
 * @author zhangxq
 * @since 2022/9/13
 */
public class StartButton {
    
    private BoostCommand bc;
    
    void init(BoostCommand bc) {
        this.bc = bc;
    }
    
    void start() {
        bc.boost();
    }
}

package com.java.designpattern.command;

public class ABoostCommand implements BoostCommand {
    
    @Override
    public void boost(String... args) {
        System.out.println("A 品牌的主机开始启动。。。");
    }
    
}
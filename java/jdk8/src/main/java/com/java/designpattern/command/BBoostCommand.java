package com.java.designpattern.command;

public class BBoostCommand implements BoostCommand {
    
    @Override
    public void boost(String... args) {
        System.out.println("B 品牌的主机开始启动。。。");
    }
    
}
package com.designpattern.factorymethod;

/**
 * 工厂方法模式
 *
 * @author zail
 * @date 2022/5/26
 */
public class Main {
    
    public static void main(String[] args) {
        AbstractExporter exporter = new ExcelFileExporter();
        exporter.export("导出的数据");
    }
}

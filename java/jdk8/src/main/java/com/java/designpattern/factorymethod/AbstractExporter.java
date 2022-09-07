package com.java.designpattern.factorymethod;

/**
 * 抽象工厂类
 *
 * @author zail
 * @date 2022/5/26
 */
public abstract class AbstractExporter {
    
    /**
     * 工厂方法，创建导出文件对象的接口对象
     */
    protected abstract ExportFileApi factoryMethod();
    
    /**
     * 导出文件
     *
     * 父类在不知道具体实现的情况下，完成自身的功能调用；
     * 具体实现延迟到了子类来实现。
     */
    public boolean export(String data) {
        ExportFileApi api = factoryMethod();
        return api.export(data);
    }
    
}

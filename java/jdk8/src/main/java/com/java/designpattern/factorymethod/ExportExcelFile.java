package com.java.designpattern.factorymethod;

/**
 * @author zail
 * @date 2022/5/26
 */
public class ExportExcelFile implements ExportFileApi {
    @Override
    public boolean export(String data) {
        System.out.println("导出 excel 文件");
        return true;
    }
}

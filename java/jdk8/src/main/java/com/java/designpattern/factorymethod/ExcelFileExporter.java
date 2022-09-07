package com.java.designpattern.factorymethod;

/**
 * @author zail
 * @date 2022/5/26
 */
public class ExcelFileExporter extends AbstractExporter {
    @Override
    protected ExportFileApi factoryMethod() {
        return new ExportExcelFile();
    }
}

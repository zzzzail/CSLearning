package com.designpattern.factorymethod;

/**
 * @author zail
 * @date 2022/5/26
 */
public class TxtFileExporter extends AbstractExporter {
    @Override
    protected ExportFileApi factoryMethod() {
        return new ExportTxtFile();
    }
    
}

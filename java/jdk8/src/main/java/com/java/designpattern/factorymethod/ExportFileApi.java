package com.java.designpattern.factorymethod;

/**
 * 导出文件对象的接口
 *
 * @author zail
 * @date 2022/5/26
 */
public interface ExportFileApi {
    
    /**
     * 导出内容成为文件
     *
     * @param data 需要保存到文件的数据
     * @return 是否导出成功
     */
    boolean export(String data);
}

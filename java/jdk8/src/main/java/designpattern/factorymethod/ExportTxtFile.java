package designpattern.factorymethod;

/**
 * @author zail
 * @date 2022/5/26
 */
public class ExportTxtFile implements ExportFileApi {
    
    @Override
    public boolean export(String data) {
        System.out.println("导出 txt 文件");
        return true;
    }
}

package designpattern.facade;

/**
 * @author zail
 * @date 2022/5/26
 */
public class DAOGenerator {
    
    public void generate() {
        // 1. 从配置管理里获取相应的配置信息
        ConfigModel config = ConfigManager.getInstance().getConfigData();
        // 2. 按照配置文件生成相应的代码，保存成文件
        if (config.isNeedGenDAO()) {
            System.out.println("生成 DAO 层");
        }
    }
    
}

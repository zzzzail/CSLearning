package designpattern.facade;

/**
 * 配置管理类，负责读取配置文件，并将配置文件的内容设置到 Model 中去。
 * 此类是单例的。
 *
 * @author zail
 * @date 2022/5/26
 */
public class ConfigManager {
    
    private static ConfigManager manager = null;
    
    private static ConfigModel model = null;
    
    public static ConfigManager getInstance() {
        if (manager == null) {
            manager = new ConfigManager();
            model = new ConfigModel();
            // todo 读取配置文件，将值写入到 model 中去。这里省略了这一步
        }
        return manager;
    }
    
    /**
     * 获取配置数据
     *
     * @return 配置的数据
     */
    public ConfigModel getConfigData() {
        return model;
    }
    
}

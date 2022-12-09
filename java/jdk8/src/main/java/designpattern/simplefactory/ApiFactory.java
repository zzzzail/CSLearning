package designpattern.simplefactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Api 接口的工厂类，用于创建 Api 对象的实例
 *
 * @author zail
 * @date 2022/5/25
 */
public class ApiFactory {
    
    public static Api createApi() {
        return createApi(1);
    }
    
    /**
     * 具体创建 Api 对象的方法
     *
     * @param condition 从外部传入的条件
     * @return 创建好的 Api 对象，可以直接使用
     */
    public static Api createApi(int condition) {
        Api res;
        switch (condition) {
            case 1:
                res = new ApiImpl();
                break;
            case 2:
                res = new ApiImpl2();
                break;
            default:
                res = new ApiImpl(); // 默认实现
        }
        return res;
    }
    
    public static Api createApi(String className) {
        if (className == null) return null;
        
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return createApi(clazz);
    }
    
    public static Api createApi(Class<?> clazz) {
        try {
            return (Api) clazz.newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    private static final String DEFAULT_CONFIG_FILE_PATH = "api-factory.properties";
    private static final String DEFAULT_CONFIG_NAME = "impl-class";
    
    /**
     * 通过配置文件的方式创建对象，这样就不需要每次添加新的 Api 实现时，修改工厂方法的类了。
     * 还可以让客户自己实现自己的 Api 接口。
     */
    public static Api createByProperties() {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            // in = ApiFactory.class.getResourceAsStream(DEFAULT_CONFIG_FILE_PATH);
            in = ApiFactory.class.getClassLoader().getResourceAsStream(DEFAULT_CONFIG_FILE_PATH);
            properties.load(in);
        }
        catch (IOException e) {
            System.out.println("装载工厂配置文件出错：");
            e.printStackTrace();
            // throw new RuntimeException(e);
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    // throw new RuntimeException(e);
                }
            }
        }
        
        String className = properties.getProperty(DEFAULT_CONFIG_NAME);
        return createApi(className);
    }
}

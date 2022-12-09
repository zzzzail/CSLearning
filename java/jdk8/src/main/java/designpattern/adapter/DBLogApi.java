package designpattern.adapter;

import java.util.List;

/**
 * 定义操作日志的应用接口，
 *
 * 可以让日志管理同时支持数据库存储和文件存储吗？
 *
 * @author zail
 * @date 2022/5/26
 */
public interface DBLogApi {
    
    /**
     * 新增日志
     *
     * @param log
     */
    void createLog(LogModel log);
    
    /**
     * 修改日志
     *
     * @param log
     */
    void updateLog(LogModel log);
    
    /**
     * 删除日志
     *
     * @param log
     */
    void removeLog(LogModel log);
    
    /**
     * 获取所有日志
     *
     * @return
     */
    List<LogModel> getAllLog();
}

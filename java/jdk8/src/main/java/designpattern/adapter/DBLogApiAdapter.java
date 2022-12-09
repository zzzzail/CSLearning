package designpattern.adapter;

import java.util.List;

/**
 * 操作日志的适配器模式实现
 *
 * @author zail
 * @date 2022/5/26
 */
public class DBLogApiAdapter implements DBLogApi {
    
    // 持有需要被适配的对象
    private FileLogApiImpl logFileLogApiImpl;
    
    public DBLogApiAdapter(FileLogApiImpl logFileLogApiImpl) {
        this.logFileLogApiImpl = logFileLogApiImpl;
    }
    
    @Override
    public void createLog(LogModel log) {
        // 1. 读取文件的内容
        List<LogModel> logs = logFileLogApiImpl.readLogFile();
        // 2. 加入新的日志对象
        logs.add(log);
        // 3. 重新写入文件
        logFileLogApiImpl.writeLogFile(logs);
    }
    
    @Override
    public void updateLog(LogModel newLog) {
        // 1. 读取文件的内容
        List<LogModel> logs = logFileLogApiImpl.readLogFile();
        // 2. 修改日志对象
        for (int i = 0; i < logs.size(); i++) {
            if (logs.get(i).getLogId().equals(newLog.getLogId())) {
                logs.set(i, newLog);
                break;
            }
        }
        // 3. 重新写入文件
        logFileLogApiImpl.writeLogFile(logs);
    }
    
    @Override
    public void removeLog(LogModel log) {
        // 1. 读取文件的内容
        List<LogModel> logs = logFileLogApiImpl.readLogFile();
        // 2. 删除相应的日志对象
        logs.remove(log);
        // 3. 重新写入文件
        logFileLogApiImpl.writeLogFile(logs);
    }
    
    @Override
    public List<LogModel> getAllLog() {
        return logFileLogApiImpl.readLogFile();
    }
}

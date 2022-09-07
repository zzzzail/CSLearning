package com.designpattern.adapter;

import java.util.List;

/**
 * @author zhangxq
 * @since 2022/9/7
 */
public class FileLogApiAdapter implements FileLogApi {
    
    private DBLogApi dbLogApi;
    
    public FileLogApiAdapter(DBLogApi dbLogApi) {
        this.dbLogApi = dbLogApi;
    }
    
    @Override
    public List<LogModel> readLogFile() {
        return null;
    }
    
    @Override
    public void writeLogFile(List<LogModel> logs) {
    
    }
    
    @Override
    public void append(LogModel log) {
    
    }
}

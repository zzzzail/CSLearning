package com.java.designpattern.adapter;

import java.util.List;

/**
 * @author zail
 * @date 2022/5/26
 */
public interface FileLogApi {
    
    /**
     * 读取日志文件，在日志文件中获取日志模型对象
     *
     * @return 返回日志信息列表
     */
    List<LogModel> readLogFile();
    
    /**
     * 写将数据写入到日志文件中
     *
     * @param logs 要写入日志文件的数据列表
     */
    void writeLogFile(List<LogModel> logs);
    
    /**
     * 在文件末尾追加日志信息
     *
     * @param log
     */
    void append(LogModel log);
    
}

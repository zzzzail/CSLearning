package com.designpattern.adapter;

import java.io.*;
import java.util.List;

/**
 * 实现对日志文件的操作
 *
 * @author zail
 * @date 2022/5/26
 */
public class LogFileOperator implements LogFileOperateApi {
    
    private static final String DEFAULT_LOGFILE = "adapter-log.log";
    
    private String logfile;
    
    public LogFileOperator() {
        this.logfile = DEFAULT_LOGFILE;
    }
    
    public LogFileOperator(String logfile) {
        this.logfile = logfile;
    }
    
    @Override
    public List<LogModel> readLogFile() {
        List<LogModel> res = null;
        ObjectInputStream oin = null;
        try {
            File file = new File(this.logfile);
            if (file.exists()) {
                oin = new ObjectInputStream(
                        new BufferedInputStream(new FileInputStream(file)));
                res = (List<LogModel>) oin.readObject();
            }
        }
        catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (oin != null) {
                    oin.close();
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }
    
    @Override
    public void writeLogFile(List<LogModel> logs) {
        File file = new File(this.logfile);
        ObjectOutputStream oout = null;
        try {
            oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            oout.writeObject(logs);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (oout != null) {
                    oout.close();
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

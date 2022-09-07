package com.designpattern.adapter;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

/**
 * 实现对日志文件的操作
 *
 * @author zail
 * @date 2022/5/26
 */
public class FileLogApiImpl implements FileLogApi {
    
    private static final String DEFAULT_LOGFILE = "adapter-log.log";
    
    private String logfile;
    
    public FileLogApiImpl() {
        this.logfile = DEFAULT_LOGFILE;
    }
    
    public FileLogApiImpl(String logfile) {
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
            OutputStream os = Files.newOutputStream(file.toPath());
            oout = new ObjectOutputStream(new BufferedOutputStream(os));
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
    
    @Override
    public void append(LogModel log) {
        // 在文件最后追加日志信息
    }
}

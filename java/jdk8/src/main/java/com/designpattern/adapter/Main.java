package com.designpattern.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器 Adapter 设计模式
 * <p>
 * 将一个类的接口转换成客户希望的另外一个接口。适配器模式是的原本由于接口不兼容而
 * 不能一起工作的那些类可以一起工作。
 *
 * @author zail
 * @date 2022/5/26
 */
public class Main {
    
    public static void main(String[] args) {
        // test01();
        test02();
    }
    
    public static void test01() {
        List<LogModel> logs = new ArrayList<>();
        logs.add(LogModel.builder()
                .logId("001")
                .operateTime("2010-03-02 10:08:08")
                .operateUser("admin")
                .logContent("这是一个测试的日志")
                .build());
        // 创建日志操作对象
        FileLogApiImpl lfo = new FileLogApiImpl();
        // 将日志写入
        lfo.writeLogFile(logs);
        
        // 读日志
        List<LogModel> readLogs = lfo.readLogFile();
        System.out.println("读取到的日志: " + readLogs);
    }
    
    public static void test02() {
        // 创建日志操作对象
        FileLogApiImpl lfo = new FileLogApiImpl();
        DBLogApi logOperator = new DBLogApiAdapter(lfo);
        // 将日志写入
        logOperator.createLog(
                LogModel.builder()
                        .logId("002")
                        .operateTime("2010-03-02 10:08:08")
                        .operateUser("admin")
                        .logContent("这是一个测试的日志")
                        .build()
        );
        
        // 读日志
        List<LogModel> readLogs = logOperator.getAllLog();
        System.out.println("读取到的日志: " + readLogs);
    }
    
    public static void test03() {
        // 使用 FileLogAdapter 就能即使用 LogFileApi 的功能又使用 DBLogApi 的功能
        DBLogApi dbLogApi = new DBLogApiImpl();
        FileLogApiAdapter fileLog = new FileLogApiAdapter(dbLogApi);
        List<LogModel> logs = fileLog.readLogFile();
    }
}

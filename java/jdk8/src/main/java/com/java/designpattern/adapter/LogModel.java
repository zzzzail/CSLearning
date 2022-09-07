package com.java.designpattern.adapter;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 日志数据对象
 *
 * @author zail
 * @date 2022/5/26
 */
@Data
@Builder
public class LogModel implements Serializable {
    
    /* 日志编号 */
    private String logId;
    
    /* 操作人员 */
    private String operateUser;
    
    /* 操作时间 */
    private String operateTime;
    
    /* 日志内容 */
    private String logContent;
}

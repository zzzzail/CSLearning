package com.qingtian.lcpes.base.file.impl;

import com.qingtian.lcpes.base.file.AsyncExportFileService;
import com.qingtian.lcpes.base.file.excel.ExportExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

/**
 * @author zhangxq
 * @since 2022/8/31
 */
@Service
@Slf4j
public class AsyncExportFileServiceImpl implements AsyncExportFileService {

    @Async("asyncExportFileExecutor")
    @Override
    public void export(Long exportLogSid, ExportExcel exporter) {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(3000);
            // todo 异步导出文件
        }
        catch (Exception e) {
            log.error("【异步导出文件】错误：", e);
        }
        log.info("【异步导出文件】导出文件用时：{}", System.currentTimeMillis() - start);
    }
}

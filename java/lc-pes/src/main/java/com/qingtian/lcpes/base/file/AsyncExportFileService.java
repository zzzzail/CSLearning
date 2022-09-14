package com.qingtian.lcpes.base.file;

import com.qingtian.lcpes.base.file.excel.ExportExcel;

/**
 * @author zhangxq
 * @since 2022/8/31
 */
public interface AsyncExportFileService {

    void export(Long exportLogSid, ExportExcel exporter);

}

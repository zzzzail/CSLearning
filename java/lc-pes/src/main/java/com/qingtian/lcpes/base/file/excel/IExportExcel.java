package com.qingtian.lcpes.base.file.excel;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

public interface IExportExcel {

    /**
     * Excel工作簿中生成sheet(只有一页的时候)
     */
    ByteArrayOutputStream createExportXlsFileStream();

    /**
     * 压缩xls文件
     */
    ByteArrayOutputStream createZipFileStream(int num);

    /**
     * Excel工作簿中生成sheet(包含多页的时候，生成本地文件打包，供用户下载)
     */
    void createLocalExportFile(int i);

    void setLocalFileNamePattern(String localFileNamePattern);

    void setFilePath(String filePath);

    void setSysSeparator(String sysSeparator);

    void setBasePath(String basePath);

    void setDataStart(int dataStart);

    void setDataEnd(int dataEnd);

    String getStrUserName();

    void setStrUserName(String strUserName);

    void setFileName(String fileName);

    void setExcelTitle(String excelTitle);

    void setNeedFooter(boolean needFooter);

    void setNeedTitle(boolean needTitle);

    void setDataList(List<?> dataList);

    void setDictMap(Map<String, Map<String, String>> dictMap);

    void setLockHeader(int lockHeader);

    void setLockColumns(int lockColumns);

    void setColConfigs(List<ExportColumnConfig> colConfigs);
}

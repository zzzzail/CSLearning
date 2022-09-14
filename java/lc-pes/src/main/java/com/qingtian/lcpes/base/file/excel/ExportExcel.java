package com.qingtian.lcpes.base.file.excel;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxq
 * @since 2022/8/31
 */
@Data
public class ExportExcel {
    private static final Logger logger = LoggerFactory.getLogger(ExportExcel.class);
    private static ExportExcel instance = new ExportExcel();
    private String fileName;
    private String realPath;
    private String excelTitle;
    private int perFileRows = 10000;
    private String fileSurrfix;
    private String userName;
    private boolean needFooter = false;
    private boolean needTitle = false;
    private boolean needCompress = false;
    private List<?> dataList;
    private List<ExportColumnConfig> colConfigs;
    private Map<String, Map<String, String>> dictMap;
    private int lockHeader = 0;
    private int lockColumns = 0;

    public static ExportExcel getInstance() {
        return instance;
    }

    /**
     * 获取导出文件数据流
     */
    public ByteArrayOutputStream getExportStream() {
        long time1 = System.currentTimeMillis();
        String suffix = ".xlsx";
        IExportExcel exportExcelDefault = new ExportExcelDefault();

        int dataSize = this.getDataList().size();
        String fileSeparator = System.getProperty("file.separator");
        String fileName = this.getFileName() + suffix;
        fileName = this.getRealPath() + "exceltemplate" + fileSeparator + fileName;

        int num = (dataSize % perFileRows == 0) ? (int) Math.floor(dataSize / perFileRows) : (int) Math.floor(dataSize / perFileRows) + 1;

        exportExcelDefault.setNeedFooter(this.isNeedFooter());
        exportExcelDefault.setNeedTitle(this.isNeedTitle());
        exportExcelDefault.setExcelTitle(this.getExcelTitle());
        exportExcelDefault.setFileName(this.getFileName());
        exportExcelDefault.setDataList(this.getDataList());
        exportExcelDefault.setDictMap(this.getDictMap());
        exportExcelDefault.setLockHeader(this.getLockHeader());
        exportExcelDefault.setLockColumns(this.getLockColumns());
        exportExcelDefault.setColConfigs(this.getColConfigs());

        ByteArrayOutputStream os = null;
        if (!this.fileIsExist(fileName)) {
            //将数据传给导出Excel的工具类
            exportExcelDefault.setFileName(this.getFileName());
            exportExcelDefault.setStrUserName("张三");
            //多个sheet打zip包，一个sheet直接返回xsl
            if (this.isNeedCompress() && num > 1) {
                //生成本地文件
                for (int j = 0; j < num; j++) {
                    exportExcelDefault.setDataStart((j * perFileRows));
                    exportExcelDefault.setDataEnd(((j + 1) * perFileRows) > dataSize ? dataSize : ((j + 1) * perFileRows));
                    //创建工作簿
                    exportExcelDefault.createLocalExportFile(j);
                    this.setFileSurrfix(".zip");
                }
                //将本地文件打包
                os = exportExcelDefault.createZipFileStream(num);
            }
            else {
                exportExcelDefault.setDataStart(0);
                exportExcelDefault.setDataEnd(this.getDataList().size());
                exportExcelDefault.setDataList(this.getDataList());
                //创建工作簿
                os = exportExcelDefault.createExportXlsFileStream();
                this.setFileSurrfix(suffix);
            }
        }
        else {
            //采用模板导出时，在此实现
        }

        long time2 = System.currentTimeMillis();
        logger.info("数据量: " + dataSize + " .");
        logger.info("生成EXCEL耗时：" + (time2 - time1) + " ms");
        return os;
    }

    /**
     * 判断文件是否存在
     */
    private boolean fileIsExist(String fileName) {
        boolean isExist = false;
        File f = new File(fileName);
        if (f.exists()) {
            isExist = true;
        }
        return isExist;
    }
}

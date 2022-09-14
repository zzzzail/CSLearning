package com.qingtian.lcpes.base.file.excel;

import lombok.Data;

@Data
public class ExportColumnConfig {

    /* 列代码 */
    private String columnCode;

    /* 列名 */
    private String columnName;

    private Integer columnSeq;

    private String dictCode;

    private String dateColumnFormat;

    private String expType;

    private String expression;

    /* 颜色 */
    private String color;

}

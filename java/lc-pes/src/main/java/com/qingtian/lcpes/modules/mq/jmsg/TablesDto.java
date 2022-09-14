package com.qingtian.lcpes.modules.mq.jmsg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class TablesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Columns")
    private List<ColumnsInfo> columns;

    @JsonProperty("Rows")
    private List<Map<String, Object>> rows;
}

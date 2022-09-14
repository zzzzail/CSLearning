package com.qingtian.lcpes.modules.mq.jmsg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ColumnsInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Caption")
    private String caption;

    @JsonProperty("DataType")
    private String dataType;

    @Override
    public String toString() {
        return "ColumnsInfo [name=" + name + ", caption=" + caption + ", dataType=" + dataType + "]";
    }

}

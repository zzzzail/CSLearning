package com.qingtian.lcpes.modules.mq.jmsg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NodeInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("MessageHeader")
    private MsgHeaderDto messageHeader;
    @JsonProperty("Tables")
    private List<TablesDto> tables;

    @Override
    public String toString() {
        return "NodeInfo [messageHeader=" + messageHeader + ", tables=" + tables + "]";
    }

}

package com.qingtian.lcpes.modules.mq.jmsg;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MsgHeaderDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("UUID")
    private String UUID;

    @JsonProperty("SvcName")
    private String svcName;

    @JsonProperty("Sender")
    private String sender;

    @JsonProperty("Receiver")
    private String receiver;

    @JsonProperty("MessageId")
    private String messageId;

    @JsonProperty("Msg")
    private String msg;

    @JsonProperty("Flag")
    private String flag;

    @JsonProperty("MessageTypeId")
    private String messageTypeId;

    @JsonProperty("SendDate")
    private String sendDate;

    @JsonProperty("SendTime")
    private String sendTime;
}

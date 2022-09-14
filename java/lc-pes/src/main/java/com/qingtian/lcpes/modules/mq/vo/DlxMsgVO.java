package com.qingtian.lcpes.modules.mq.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author zail
 * @date 2022/8/1
 */
@Data
public class DlxMsgVO {

    // 消息类型 ID
    @JsonProperty("messageTypeId")
    private String messageTypeId;

}

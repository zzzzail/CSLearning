package com.qingtian.lcpes.modules.mq.jmsg;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseIXBUSModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String messageId;
	private String interfaceId;
	private String messageTypeId;
}

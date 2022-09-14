package com.qingtian.lcpes.base.web;

import javax.validation.Valid;
import java.io.Serializable;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class JsonRequest<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private JsonHeader reqHeader;
    private @Valid T reqBody;

    public JsonRequest() {
    }

    public JsonHeader getReqHeader() {
        return this.reqHeader;
    }

    public void setReqHeader(JsonHeader reqHeader) {
        this.reqHeader = reqHeader;
    }

    public T getReqBody() {
        return this.reqBody;
    }

    public void setReqBody(T reqBody) {
        this.reqBody = reqBody;
    }
}

package com.qingtian.lcpes.base.web;

import com.alibaba.fastjson.JSON;
import com.qingtian.lcpes.base.i18n.I18nResource;
import com.qingtian.lcpes.base.util.StringUtils;

import java.io.Serializable;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class ServiceResponse<T> implements Serializable {
    private static final long serialVersionUID = -6495911812654393168L;
    public static final String SUCCESS_CODE = "0";
    public static final String FAIL_CODE = "1";
    public static final String SUCCESS_CODE_TEMP = "1000";
    public static final String SUCCESS_MESSAGE = "OK";
    private String retCode = "0";
    private String retMessage = "OK";
    private boolean hasError = false;
    private Exception exception = null;
    private T retContent;

    public ServiceResponse() {
    }

    public boolean isHasError() {
        return this.hasError;
    }

    public String getErrorMessage() {
        return this.retMessage;
    }

    public void setException(Exception exception) {
        if (exception != null) {
            this.hasError = true;
            this.retMessage = exception.getMessage();
        }

        this.retCode = "1";
    }

    public String getRetCode() {
        return this.retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public void setResponseCode(String retCode) {
        if (StringUtils.isNotEmpty(retCode)) {
            this.setRetCode(retCode);
            if (!this.isSuccessful()) {
                this.retMessage = I18nResource.getMessage(this.retCode);
            }
        }

    }

    public void setRetCode(String retCode, Object[] args) {
        this.retCode = retCode;
        this.retMessage = I18nResource.getMessage(this.retCode, args);
    }

    public String getRetMessage() {
        return this.retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public T getRetContent() {
        return this.retContent;
    }

    public void setRetContent(T retContent) {
        this.retContent = retContent;
    }

    public boolean isSuccessful() {
        return "0".equals(this.getRetCode());
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}

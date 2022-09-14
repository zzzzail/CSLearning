package com.qingtian.lcpes.base.web;

import com.qingtian.lcpes.base.i18n.I18nResource;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class JsonResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String SUCCESS = "0000000";
    public static final String SYS_EXCEPTION = "500";
    public static final String SYS_ERROR_MSG = "系统异常";
    private String retCode;
    private String retDesc;
    private String timestamp;
    private T rspBody;

    public JsonResponse() {
        this(null);
    }

    public JsonResponse(T value) {
        this("0000000", "执行成功!", value);
    }

    public JsonResponse(String retCode, String retDesc, T rspBody) {
        this(retCode, retDesc, rspBody, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date()));
    }

    public JsonResponse(String retCode, String retDesc) {
        this(retCode, retDesc, null);
    }

    public JsonResponse(String retCode, String retDesc, T rspBody, String timestamp) {
        this.retCode = "0000000";
        this.retCode = retCode;
        this.retDesc = retDesc;
        this.rspBody = rspBody;
        this.timestamp = timestamp;
    }

    public static <T> JsonResponse<T> success() {
        return new JsonResponse("0000000", "执行成功", (Object) null);
    }

    public static <T> JsonResponse<T> success(T value) {
        return new JsonResponse("0000000", "执行成功", value);
    }

    public static <T> JsonResponse<T> error() {
        return new JsonResponse("500", "系统异常");
    }

    public static <T> JsonResponse<T> error(String retDesc) {
        return new JsonResponse("500", retDesc);
    }

    public static <T> JsonResponse<T> error(String retCode, String retDesc) {
        return new JsonResponse(retCode, retDesc);
    }

    public static <T> JsonResponse<T> error(String retCode, String retDesc, T value) {
        return new JsonResponse(retCode, retDesc, value);
    }

    public void setTimestamp(Date date) {
        this.timestamp = date == null ? this.timestamp : (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(date);
    }

    public String getRetCode() {
        return this.retCode;
    }

    public void setRetCode(String retCode) {
        if ("500".equals(retCode)) {
            this.retDesc = "系统异常";
        }
        else if (!"0000000".equals(retCode)) {
            this.retDesc = I18nResource.getMessage(retCode);
        }

        this.retCode = retCode;
    }

    public String getRetDesc() {
        return this.retDesc;
    }

    public void setRetDesc(String retDesc) {
        this.retDesc = retDesc;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public T getRspBody() {
        return this.rspBody;
    }

    public void setRspBody(T rspBody) {
        this.rspBody = rspBody;
    }

    public void setServiceResponse(ServiceResponse<T> serviceResponse) {
        if ("0".equals(serviceResponse.getRetCode())) {
            this.rspBody = serviceResponse.getRetContent();
            this.retDesc = serviceResponse.getRetMessage();
        }
        else if ("500".equals(serviceResponse.getRetCode())) {
            this.retCode = serviceResponse.getRetCode();
            this.retDesc = serviceResponse.getErrorMessage();
        }
        else {
            this.retCode = serviceResponse.getRetCode();
            if (null == serviceResponse.getErrorMessage()) {
                this.retDesc = serviceResponse.getRetMessage();
            }
            else {
                this.retDesc = serviceResponse.getErrorMessage();
            }
        }

    }
}
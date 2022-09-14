package com.qingtian.lcpes.base.exception;

import com.qingtian.lcpes.base.i18n.I18nResource;
import com.qingtian.lcpes.base.util.StringUtils;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class QTBusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code = "500";
    private String msg;

    public QTBusinessException() {
    }

    public QTBusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public QTBusinessException(String code, String msg, Object[] args) {
        super(msg);
        this.code = code;
        this.msg = msg;
        String i18n = I18nResource.getMessage(code, args);
        if (StringUtils.isNotEmpty(i18n)) {
            this.msg = i18n;
        }
    }

    public String resolverException() {
        return this.msg;
    }

    public String getCode() {
        return this.code;
    }
}

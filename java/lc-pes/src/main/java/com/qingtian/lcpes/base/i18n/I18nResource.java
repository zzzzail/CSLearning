package com.qingtian.lcpes.base.i18n;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.MissingResourceException;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class I18nResource {
    private static final ReloadableResourceBundleMessageSource i18nMessage = new ReloadableResourceBundleMessageSource();
    private static final ReloadableResourceBundleMessageSource i18nException;

    public I18nResource() {
    }

    public static String getMessage(String messageKey, Object[] messageArgs) {
        String message = null;

        try {
            message = i18nMessage.getMessage(messageKey, messageArgs, LocaleContextHolder.getLocale());
        }
        catch (MissingResourceException var4) {
            message = "i18nMessage is: " + messageKey + ", can't get the I18n properties";
        }
        catch (Throwable var5) {
            message = "i18nMessage is: " + messageKey + ", can't get the I18n properties";
        }

        return message;
    }

    public static String getException(String code, Object[] messageArgs) {
        String message = null;

        try {
            message = i18nException.getMessage(code, messageArgs, LocaleContextHolder.getLocale());
        }
        catch (MissingResourceException e) {
            message = code;
        }
        catch (Throwable throwable) {
            message = code;
        }

        return message;
    }

    public static String getMessage(String messageKey) {
        return getMessage(messageKey, null);
    }

    static {
        i18nMessage.setBasenames("i18n/message", "classpath:i18n/message");
        i18nException = new ReloadableResourceBundleMessageSource();
        i18nException.setBasenames("i18n/exception", "classpath:i18n/exception");
    }
}

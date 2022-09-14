package com.qingtian.lcpes.base;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class Constants {
    public static final String I18N_EXCEPTION = "i18n/exception";
    public static final String I18N_MESSAGE = "i18n/message";
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");
    public static final String STRING_TYPE = new String();
    public static final Integer INTEGER_TYPE = new Integer(0);
    public static final Short SHORT_TYPE = new Short("1");
    public static final Long LONG_TYPE = 1L;
    public static final Float FLOAT_TYPE = 1.0F;
    public static final Double DOUBLE_TYPE = 1.0;
    public static final Character CHARACTER_TYPE = 'C';
    public static final Boolean BOOLEAN_TYPE = true;
    public static final Byte BYTE_TYPE = new Byte("0");
    public static final Date DATE_TYPE = new Date();
    public static final BigDecimal BIGDECIMAL_TYPE = new BigDecimal(0);

    static String REDIS_KEY_MESSAGE = "MESSAGEAPP:";

    public static String REDIS_KEY_MESSAGE_ID = REDIS_KEY_MESSAGE + "MESSAGEID:";
}

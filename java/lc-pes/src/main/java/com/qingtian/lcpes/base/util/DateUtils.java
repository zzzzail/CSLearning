package com.qingtian.lcpes.base.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zhangxq
 * @since 2022/9/5
 */
public class DateUtils {

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHHMMSS2 = "yyyy/MM/dd/HH/mm/ss";
    public static final String YYYYMMDD2 = "yyyy-MM-dd";

    public DateUtils() {
    }

    public static Date strToDate(String strDate, String pattern) {
        if (org.apache.commons.lang3.StringUtils.isBlank(strDate)) {
            return null;
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            ParsePosition pos = new ParsePosition(0);
            Date date = formatter.parse(strDate, pos);
            return date;
        }
    }

    public static Date strToDateLong(String strDate) {
        if (org.apache.commons.lang3.StringUtils.isBlank(strDate)) {
            return null;
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        }
    }

    public static Date strToDateLong(String strDate, String pattern) {
        if (org.apache.commons.lang3.StringUtils.isBlank(strDate)) {
            return null;
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        }
    }

    public static Date strToDateyyyyMMddHHmmss(String strDate) {
        if (org.apache.commons.lang3.StringUtils.isBlank(strDate)) {
            return null;
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        }
    }

    public static String DateToStryyyyMMdd(Date date) {
        if (date == null) {
            return null;
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String dateString = formatter.format(date);
            return dateString;
        }
    }

    public static void main(String[] args) {
        System.out.println(dateToStr(getDateByMonth(strToDateLong("201701", "yyyyMM"), -3), "yyyyMM"));
    }

    public static String dateToStrLong(Date date) {
        if (date == null) {
            return "";
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);
            return dateString;
        }
    }

    public static String dateToStrYYYYMMdd(Date date) {
        if (date == null) {
            return "";
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String dateString = formatter.format(date);
            return dateString;
        }
    }

    public static String dateToStr(Date date) {
        if (date == null) {
            return "";
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(date);
            return dateString;
        }
    }

    public static Date strToDate(String strDate) {
        if (org.apache.commons.lang3.StringUtils.isBlank(strDate)) {
            return null;
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        }
    }

    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String dateToStr(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        else {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
    }

    public static String strTostr(String str, String newPattern, String oldPattern) {
        Date date = strToDate(str, oldPattern);
        String newStr = dateToStr(date, newPattern);
        return newStr;
    }

    public static Date getDate(Date date, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (days != null) {
            calendar.add(5, days);
        }

        return calendar.getTime();
    }

    public static Date getDateByMonth(Date date, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (month != null) {
            calendar.add(2, month);
        }

        return calendar.getTime();
    }

    public static boolean isValidDate(String dateStr, String datePattern) {
        if (!org.apache.commons.lang3.StringUtils.isBlank(dateStr) && !org.apache.commons.lang3.StringUtils.isBlank(datePattern)) {
            boolean convertSuccess = true;
            SimpleDateFormat format = new SimpleDateFormat(datePattern);

            try {
                format.setLenient(false);
                format.parse(dateStr);
            }
            catch (ParseException var5) {
                convertSuccess = false;
            }

            return convertSuccess;
        }
        else {
            return false;
        }
    }

    public static String getCurrentMaxDate(String date) {
        if (org.apache.commons.lang3.StringUtils.isBlank(date)) {
            return "";
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(date, pos);
            String dateString = formatter.format(strtodate) + " 23:59:59";
            return dateString;
        }
    }

    public static String getCurrentMinDate(String date) {
        if (org.apache.commons.lang3.StringUtils.isBlank(date)) {
            return "";
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(date, pos);
            String dateString = formatter.format(strtodate) + " 00:00:00";
            return dateString;
        }
    }

    public static boolean IsCompareDate(String date1, String date2, String format) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(date1) && org.apache.commons.lang3.StringUtils.isNotBlank(date2)) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos2 = new ParsePosition(0);
            Date date1Time = formatter.parse(date1, pos);
            Date date2Time = formatter.parse(date2, pos2);
            return date1Time.getTime() > date2Time.getTime();
        }
        else {
            return StringUtils.isNotBlank(date1);
        }
    }
}

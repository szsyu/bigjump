/*
 *  @fileName               DateTimeParse.java      
 *  @author                 Shawn Yu
 *  @version                1.0
 *  @createdDate            2014-08-30     
 *  @lastUpdateDate         2014-08-30
 *  Modification Log:
 *  
 */
package com.ect.bigjump.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 定义了多个静态方法,用于日期时间的计算
 *
 * @author Shawn Yu
 * @version 1.0
 * @since 2014-08-30
 */
public class DateTimeParse {

    /**
     * 默认日期格式
     */
    private static final SimpleDateFormat YMD_SDF = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 转换String为Date
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static Date convertStr2Date(String str) throws Exception {
        if (str.length() == 0) {
            return null;
        }
        try {
            return YMD_SDF.parse(str);
        } catch (ParseException e) {
            throw new Exception(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 将Date转换成String,默认为类中定义的默认的格式化类型yyyy-MM-dd
     *
     * @param date Date对象
     * @return 字符串对象
     */
    public static String convertDate2Str(Date date) {

        if (date == null) {
            return null;
        }
        try {
            return YMD_SDF.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将Date转换成String
     *
     * @param date    Date对象
     * @param pattern 格式化字符串
     * @return 字符串对象
     */
    public static String convertDate2Str(Date date, String pattern) {
        SimpleDateFormat p = new SimpleDateFormat(pattern);
        return p.format(date);
    }

    /**
     * 获取日期对应的weekday,星期一为1，星期日为7
     *
     * @param date Date对象
     * @return int 一周的第几天
     */
    public static int getWeekDayByDate(Date date) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(date);
        } catch (Exception e) {
            e.getStackTrace();
        }
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 获得财年的周次
     *
     * @param beginDate 开始日期(第一周的最后一天)
     * @param endDate   当前日期
     * @return int 指定日期所处的周次
     */
    public static int getFiscalWeekByDate(Date beginDate, Date endDate) {
        String beginDateStr = YMD_SDF.format(beginDate);
        String endDateStr = YMD_SDF.format(endDate);
        try {
            return (int) Math.ceil(getDifferDays(YMD_SDF.parse(beginDateStr),
                    YMD_SDF.parse(endDateStr)) / 7) + 1;
        } catch (ParseException e) {
            return 0;
        }

    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return 相差的天数
     */
    public static double getDifferDays(Date beginDate, Date endDate) {
        return (double) ((endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000));
    }

    /**
     * 获取当前日期时间，并格式化为yyyy-MM-dd HH:mm字符串
     *
     * @return string
     */
    public static String getCurrentDateTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(currentTime);
    }

    /**
     * 对日期进行格式化
     *
     * @param date    Date对象
     * @param pattern 格式化字符串
     * @return date Date对象
     */
    public static Date formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateStr = sdf.format(date);
        Date mydate = date;
        try {
            mydate = sdf.parse(dateStr);
        } catch (ParseException e) {
            return date;
        }
        return mydate;
    }
}

package com.zhishangquan.server.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author song
 */
public class DateUtil {

  public static final String yyyyMMdd = "yyyyMMdd";
  public static final String yyyyMMddHH = "yyyyMMddHH";
  public static final String yyyy_MM_dd = "yyyy-MM-dd";
  public static final String yyyyMMddHHmmss = "yyyyMMddHHmmssSSS";

  private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

  private static SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat(DEFAULT_PATTERN);
  private static SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat(yyyyMMdd);
  private static SimpleDateFormat YYYY_MM_DD_FORMAT = new SimpleDateFormat(yyyy_MM_dd);
  private static SimpleDateFormat yyyyMMddHH_FORMAT = new SimpleDateFormat(yyyyMMddHH);
  private static SimpleDateFormat yyyyMMddHHmmss_FORMAT = new SimpleDateFormat(yyyyMMddHHmmss);

  public static Date getCurrentTime() {
    return new Date();
  }

  /**
   * 转成默认格式yyyy-MM-dd HH:mm:ss
   * 
   * @param date
   * @return
   */
  public static String format(Date date) {
    if (date == null) {
      return null;
    }
    return DEFAULT_FORMAT.format(date);
  }

  /**
   * 将Date类型的日期转换为参数定义的格式的字符串。
   * 
   * @param date
   * @param pattern
   * @return
   */
  public static String format(Date date, String pattern) {
    if (date == null || pattern == null) {
      return null;
    }
    if (pattern.equalsIgnoreCase(yyyyMMdd)) {
      return YYYYMMDD_FORMAT.format(date);
    }
    if (pattern.equalsIgnoreCase(yyyy_MM_dd)) {
      return YYYY_MM_DD_FORMAT.format(date);
    }
    if (pattern.equalsIgnoreCase(yyyyMMddHH)) {
      return yyyyMMddHH_FORMAT.format(date);
    }
    if (pattern.equalsIgnoreCase(yyyyMMddHHmmss)) {
      return yyyyMMddHHmmss_FORMAT.format(date);
    }
    SimpleDateFormat dateFromat = new SimpleDateFormat(pattern);
    return dateFromat.format(date);
  }
  
  public static Date getCurrDate(String format) {
	  DateFormat df = new SimpleDateFormat(format);
	  try {
		  String currDateStr = df.format(new Date());
		  return df.parse(currDateStr);
	  } catch (Exception e) {
	  }
	  return null;
  }

}

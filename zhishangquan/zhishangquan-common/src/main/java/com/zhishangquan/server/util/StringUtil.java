package com.zhishangquan.server.util;

import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.math.RandomUtils;

/**
 * @author jzsong@uworks.cc
 */
public class StringUtil {

  /**
   * <pre>
   * 判断是否为空，为空则返回true
   * 为空的条件：null、""、"null"
   * </pre>
   * 
   * @param obj
   * @return
   */
  public static boolean isBlank(Object obj) {
    if (obj == null) {
      return true;
    }
    String str = obj.toString().trim();
    if ("".equals(str) || "null".equalsIgnoreCase(str)) {
      return true;
    }
    return false;
  }

  /**
   * <pre>
   * 判断是否不为空，不为空则返回true
   * 为空的条件：null、""、"null"
   * </pre>
   * 
   * @param obj
   * @return
   */
  public static boolean isNotBlank(Object obj) {
    return !isBlank(obj);
  }

  /**
   * <pre>
   * 获取换行符
   * </pre>
   * 
   * @return
   */
  public static String getNewLine() {
    return System.getProperty("line.separator");
  }

  /**
   * 获取uuid字符串
   */
  public static String uuid() {
    return UUID.randomUUID().toString();
  }

  /**
   * 获取uuid字符串，无横杠
   */
  public static String uuidNotLine() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  /**
   * 去除前后空格，若obj为null返回""空字串
   * 
   * @param obj
   * @return
   */
  public static String trim(Object obj) {
    if (obj == null) {
      return "";
    }
    return obj.toString().trim();
  }

  /**
   * <p>
   * 生成随机码
   *
   * <p>
   * 若指定的长度为0，则返回空白字符串
   *
   * @param codeLength
   *          随机码长度
   * @return
   */
  public static String randomCode(int codeLength) {
    if (codeLength <= 0) {
      return "";
    }

    long powResult = 1L;
    for (int i = 0; i < codeLength / 2; i++) {
      powResult *= 10;
    }
    if ((codeLength & 0x1) == 0) {
      powResult *= powResult;
    } else {
      powResult *= powResult * 10;
    }
    long randomResult = RandomUtils.nextLong(new Random(System.currentTimeMillis()));
    if (randomResult < 0) {
      randomResult += Long.MAX_VALUE;
    }
    StringBuilder code = new StringBuilder(Long.toString(randomResult));
    if (code.length() > codeLength) {
      code.setLength(codeLength);
    } else {
      int[] source = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
      int sourceHash = source.hashCode();
      int index = code.length();
      while ((index < codeLength)) {
        code.append(sourceHash % 10);
        sourceHash /= 10;
        index++;
      }
    }
    return String.valueOf(code);
  }
  /**
   * 取uuid去掉-后从后向前截取指定长度随机串 @Date 2016年3月19日,上午12:57:40 @author YYF @param
   * length @return @return String @throws
   */
  public static String getUUID(int length) {
      String uuid = UUID.randomUUID().toString();
      uuid = uuid.replace("-", "");
      if (uuid.length() > length) {
          uuid = uuid.substring(uuid.length() - length, uuid.length());
      }
      return uuid;
  }

  /**
   * 判断字符串是否为数字
   * @param str
   * @return
   */
  public static boolean isNumeric(String str) {
      if (null == str || str.trim().length() == 0) {
          return false;
      }
      for (int i = 0; i < str.length(); i++) {
          if (!Character.isDigit(str.charAt(i))) {
              return false;
          }
      }
      return true;
  }

  /**
   * 检查指定的字符串是否为空。
   * <ul>
   * <li>SysUtils.isEmpty(null) = true</li>
   * <li>SysUtils.isEmpty("") = true</li>
   * <li>SysUtils.isEmpty("   ") = true</li>
   * <li>SysUtils.isEmpty("abc") = false</li>
   * </ul>
   * 
   * @param value 待检查的字符串
   * @return true/false
   */
  public static boolean isEmpty(String value) {
      int strLen;
      if (value == null || (strLen = value.length()) == 0) {
          return true;
      }
      for (int i = 0; i < strLen; i++) {
          if ((Character.isWhitespace(value.charAt(i)) == false)) {
              return false;
          }
      }
      return true;
  }

  /**
   * 检查对象是否为数字型字符串,包含负数开头的。
   */
  public static boolean isNumeric(Object obj) {
      if (obj == null) {
          return false;
      }
      char[] chars = obj.toString().toCharArray();
      int length = chars.length;
      if (length < 1)
          return false;

      int i = 0;
      if (length > 1 && chars[0] == '-')
          i = 1;

      for (; i < length; i++) {
          if (!Character.isDigit(chars[i])) {
              return false;
          }
      }
      return true;
  }

  /**
   * 检查指定的字符串列表是否不为空。
   */
  public static boolean areNotEmpty(String... values) {
      boolean result = true;
      if (values == null || values.length == 0) {
          result = false;
      } else {
          for (String value : values) {
              result &= !isEmpty(value);
          }
      }
      return result;
  }

  /**
   * 把通用字符编码的字符串转化为汉字编码。
   */
  public static String unicodeToChinese(String unicode) {
      StringBuilder out = new StringBuilder();
      if (!isEmpty(unicode)) {
          for (int i = 0; i < unicode.length(); i++) {
              out.append(unicode.charAt(i));
          }
      }
      return out.toString();
  }

  /**
   * 过滤不可见字符
   */
  public static String stripNonValidXMLCharacters(String input) {
      if (input == null || ("".equals(input)))
          return "";
      StringBuilder out = new StringBuilder();
      char current;
      for (int i = 0; i < input.length(); i++) {
          current = input.charAt(i);
          if ((current == 0x9) || (current == 0xA) || (current == 0xD) || ((current >= 0x20) && (current <= 0xD7FF))
                  || ((current >= 0xE000) && (current <= 0xFFFD)) || ((current >= 0x10000) && (current <= 0x10FFFF)))
              out.append(current);
      }
      return out.toString();
  }

}

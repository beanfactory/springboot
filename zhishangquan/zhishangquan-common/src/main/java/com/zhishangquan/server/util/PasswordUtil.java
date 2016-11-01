package com.zhishangquan.server.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <pre>
 * 密码工具类
 * 采用BCrypt加密模式，同一个字符串，每次encode的结果都不一样，需要使用checkPassword验证正确性.
 * </pre>
 * 
 * @author jzsong@uworks.cc
 */
public class PasswordUtil {

  private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  /**
   * 加密
   * 
   * @param password
   * @return
   */
  public static String encode(String password) {
    return encoder.encode(password);
  }

  /**
   * 验证密码正确性
   * 
   * @param password：原始密码
   * @param encodedPassword：加密过后的密码
   * @return
   */
  public static boolean checkPassword(String password, String encodedPassword) {
    return encoder.matches(password, encodedPassword);
  }
  
}

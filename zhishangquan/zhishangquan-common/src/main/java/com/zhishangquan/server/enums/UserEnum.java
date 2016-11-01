package com.zhishangquan.server.enums;

/**
 * 用户枚举
 *
 * @author jzsong@uworks.cc
 */
public class UserEnum {

  public enum UserStatus {
    NORMAL(1, "正常"), FROZEN(2, "冻结");
    public int code;
    public String label;

    private UserStatus(int code, String label) {
      this.code = code;
      this.label = label;
    }

    public static boolean isNormal(Integer status) {
      if (status != null && status.intValue() == NORMAL.code) {
        return true;
      }
      return false;
    }
  }

  public enum UserGender {
    MAN(1, "男"), WOMAN(2, "女");
    public int code;
    public String label;

    private UserGender(int code, String label) {
      this.code = code;
      this.label = label;
    }
  }
  
  /**
   * @Title: UserEnum.java
   * @Package com.zhishangquan.server.domain.enums
   * @Description: 注册来源:1手机,2新浪微博,3qq,4微信
   * @version V1.0
   */
  public enum UserRegistSource {
  	/**
  	 * 1手机
  	 */
    MOBILE(1, "手机"),
    /**
     * 2新浪微博
     */
    WEIBO(2, "微博"),
    /**
     * 3qq
     */
    QQ(3, "qq"),
    /**
     * 4微信
     */
    WEIXIN(4, "微信");
    public int code;
    public String label;

    private UserRegistSource(int code, String label) {
      this.code = code;
      this.label = label;
    }

  }
}

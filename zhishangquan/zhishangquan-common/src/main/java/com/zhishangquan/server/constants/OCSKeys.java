package com.zhishangquan.server.constants;

/**
 * OCS Cache中存储的数据所用的Key的前缀.
 * 
 * @author jzsong@uworks.cc
 */
public class OCSKeys {
  /**
   * 默认的访问Token的HTTP请求头的名字
   */
  public static final String ACCESS_TOKEN_HEADER_NAME = "X-Access-Auth-Token";

  /**
   * <pre>
   * ZHISHANGQUAN_SSERVER 缓存用户的key
   * 用法是: String key = ZHISHANGQUAN_SERVER_USER_ + 123;
   * </pre>
   */
  public static final String ZHISHANGQUAN_SERVER_USER_KEY = "ZHISHANGQUAN_SERVER_USER_";

  /**
   * ZHISHANGQUAN_SERVER用户过期时间(单位:秒，7天)
   */
  public static final int ZHISHANGQUAN_SERVER_USER_EXP_KEY = 60 * 60 * 24 * 7;

}

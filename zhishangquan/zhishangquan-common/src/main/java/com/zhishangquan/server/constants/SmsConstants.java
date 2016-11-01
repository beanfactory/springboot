package com.zhishangquan.server.constants;

/**
 * 短信常量
 * 
 * @author jzsong@uworks.cc
 */
public class SmsConstants {

  public static final String REGIST_TEMPLATE = "注册，验证码：%s，%d分钟内有效。";
  public static final String CHANGEPWD_TEMPLATE = "修改密码，验证码：%s，%d分钟内有效。";
  public static final String SEARCHPWD_TEMPLATE = "找回密码，验证码：%s，%d分钟内有效。";

  // 短信发送提示语
  public static final String SMS_SEND_TIPS = "短信已发送至%s,请稍等片刻";

  public static final String SMS_PERFIX = "ZHISHANGQUAN_SMS_";

  // 短信有效时间
  public static final int SMS_VALID_TIME = 5 * 60 * 1000; // 有效时间,5分钟,单位毫秒

}

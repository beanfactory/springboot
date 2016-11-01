package com.zhishangquan.server.constants;

/**
 * 错误码（200-599 禁止使用）
 *
 * @author jzsong@uworks.cc
 */
public enum Errors {
  // 0-200 系统级
  SUCCESS(0, "操作成功"),
  //
  SYSTEM_ERROR(1, "系统错误"),
  // 自定义错误，可以修改label
  SYSTEM_CUSTOM_ERROR(2, "自定义错误"),
  //
  SYSTEM_DATA_NOT_FOUND(3, "数据不存在"),
  //
  SYSTEM_NOT_LOGIN(4, "请登录"),
  //
  SYSTEM_NO_ACCESS(5, "无权限访问"),
  //
  SYSTEM_REQUEST_PARAM_ERROR(6, "请求参数错误"),
  //
  SYSTEM_BUSINESS_ERROR(7, "系统繁忙,请您稍后再试"),

  // 用户模块(700-800)
  USER_LOGIN_ERROR(701, "用户名或密码错误"),
  //
  USER_PASSWORD_INPUT_ERROR(702, "密码不符合要求"),
  //
  USER_EXIST(703, "用户已存在"),
  //
  USER_PASSWORD_SAME(704, "密码不能与老密码重复"),
  //
  USER_ACCOUNT_STATUS_ERROR(705, "帐号异常，请联系客服"),
  //
  USER_OLD_PASSWORD_ERROR(706, "原密码错误"),
  //
  USER_MOBILE_EXISTS(707, "手机号已注册"),
  //
  USER_MOBILE_NOT_REGISTER(708, "手机号未注册"),
  //
  USER_NOT_FIND(709, "用户不存在"),
  //
  USER_SMS_SEND_FAST(710, "频率过快，请稍后再试"),

  // 短信发送模块(801-820)
  SMS_CAPTCHA_NOT_REGISTER(801, "该手机号尚未注册"),
  //
  SMS_SEND_FAST(802, "请稍后再试"),
  //
  SMS_CAPTCHA_IS_NULL(803, "验证码不存在"),
  //
  SMS_CAPTCHA_EXPIRED(804, "验证码已过期"),
  //
  SMS_CAPTCHA_ERROR(805, "验证码有误"),

  //
  ;

  public int code;
  public String label;

  private Errors(int code, String label) {
    this.code = code;
    this.label = label;
  }

  /**
   * 获取状态码描述
   *
   * @param code
   *          状态码
   * @return 状态码描述，如果没有返回空串
   */
  public static String getLabel(int code) {
    String result = "";
    for (Errors status : Errors.values()) {
      if (status.code == code) {
        result = status.label;
      }
    }
    return result;
  }

}

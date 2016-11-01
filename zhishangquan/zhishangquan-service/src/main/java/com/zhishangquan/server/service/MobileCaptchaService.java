package com.zhishangquan.server.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zhishangquan.server.constants.Errors;
import com.zhishangquan.server.constants.SmsConstants;
import com.zhishangquan.server.domain.User;
import com.zhishangquan.server.response.SmsResponseBean;
import com.zhishangquan.server.util.DateUtil;
import com.zhishangquan.server.util.ExceptionUtil;
import com.zhishangquan.server.util.StringUtil;

/**
 * 手机验证码service
 * 
 * @author jzsong@uworks.cc
 */
@Service("mobileCaptchaService")
public class MobileCaptchaService {
  @Resource
  private UserService userService;
  @Resource
  private MemcachedService memcachedService;
  @Resource
  private SmsService smsService;

  // 验证码发送间隔时间（60秒）
  private long sendCaptchaIntervalTime = 60 * 1000;
  // 短信发送倒数时间
  private int captchaCoolDown = 60;

  /**
   * 用户注册
   * 
   * @param mobile
   * @return
   */
  public SmsResponseBean userRegister(String mobile) {
    return send(mobile, SmsType.REGISTER);
  }

  private SmsResponseBean send(String mobile, SmsType smsType) {
    validateSend(mobile, smsType);

    String code = StringUtil.randomCode(4);
    MobileCaptchaCache mobileCaptcha = new MobileCaptchaCache();
    mobileCaptcha.setCode(code);
    mobileCaptcha.setSentTime(DateUtil.getCurrentTime());

    String key = SmsConstants.SMS_PERFIX + mobile + "_" + smsType.name();
    memcachedService.set(key, smsValidTime(), JSONObject.toJSONString(mobileCaptcha));

    smsService.send(mobile, buildContent(smsType, code));

    SmsResponseBean bean = new SmsResponseBean();
    bean.setCoolDown(captchaCoolDown);
    return bean;
  }

  /**
   * 发送验证
   *
   * @param mobile
   * @param smsType
   */
  private void validateSend(String mobile, SmsType smsType) {
    User user = userService.findByMobile(mobile);
    // 如果是注册,验证手机是否是注册过的
    if (smsType == SmsType.REGISTER) {
      if (user != null) {
        ExceptionUtil.throwException(Errors.USER_MOBILE_EXISTS);
      }
      return;
    }
    // 其他,则用户必须存在
    if (user == null) {
      ExceptionUtil.throwException(Errors.USER_MOBILE_NOT_REGISTER);
    }

    // 验证是否频繁发送
    String key = captchaKey(mobile, smsType);
    Object obj = memcachedService.get(key);
    // 没有找到对应验证码则表示可以发送短信
    if (obj == null) {
      return;
    }
    MobileCaptchaCache mobileCaptcha = JSONObject.parseObject(obj.toString(), MobileCaptchaCache.class);
    // 最后一次发送时间
    long lastSendTime = mobileCaptcha.getSentTime().getTime();
    // 根据最后一次发送验证码时间检查两次发送请求的时间跨度
    if (lastSendTime + sendCaptchaIntervalTime >= System.currentTimeMillis()) {
      ExceptionUtil.throwException(Errors.USER_SMS_SEND_FAST);
    }
  }

  private String buildContent(SmsType smsType, String code) {
    if (smsType == null) {
      return "";
    }
    switch (smsType) {
    case REGISTER:
      return String.format(SmsConstants.REGIST_TEMPLATE, code, smsValidTime() / (60 * 1000));
    case UPDATE_PWD:
      return String.format(SmsConstants.CHANGEPWD_TEMPLATE, code, smsValidTime() / (60 * 1000));
    case FIND_PWD:
      return String.format(SmsConstants.SEARCHPWD_TEMPLATE, code, smsValidTime() / (60 * 1000));
    default:
      break;
    }
    return "";
  }

  /**
   * 短信有效时间
   */
  private int smsValidTime() {
    return SmsConstants.SMS_VALID_TIME;
  }

  /**
   * 验证码放入缓存中的key
   */
  private String captchaKey(String mobile, SmsType smsType) {
    return SmsConstants.SMS_PERFIX + mobile + "_" + smsType.toString();
  }

  /**
   * 验证码校验
   * 
   * @param bean
   */
  public void verify(String mobile, String code, SmsType smsType) {
    String key = captchaKey(mobile, smsType);
    Object obj = memcachedService.get(key);
    if (obj == null) {
      ExceptionUtil.throwException(Errors.SMS_CAPTCHA_ERROR);
    }
    // 比对验证码
    MobileCaptchaCache mobileCaptcha = JSONObject.parseObject(obj.toString(), MobileCaptchaCache.class);
    if (!code.equalsIgnoreCase(mobileCaptcha.getCode())) {
      ExceptionUtil.throwException(Errors.SMS_CAPTCHA_ERROR);
    }
    // 比对通过删除缓存中的数据
    memcachedService.delete(key);
  }

  public enum SmsType {
    REGISTER(1, "注册"), UPDATE_PWD(2, "更改密码"), FIND_PWD(3, "找回密码");
    public int id;
    public String label;

    private SmsType(int id, String label) {
      this.id = id;
      this.label = label;
    }
  }
}

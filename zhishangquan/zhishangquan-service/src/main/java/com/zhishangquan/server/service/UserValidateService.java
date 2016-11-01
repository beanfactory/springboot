package com.zhishangquan.server.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhishangquan.server.constants.Errors;
import com.zhishangquan.server.domain.User;
import com.zhishangquan.server.request.UserRegisterRequestBean;
import com.zhishangquan.server.util.ExceptionUtil;

/**
 * 用户验证service
 * 
 * @author jzsong@uworks.cc
 */
@Service("userValidateService")
public class UserValidateService {
  @Resource
  private MobileCaptchaService mobileCaptchaService;

  public void register(UserRegisterRequestBean bean, User oldUser) {
    String mobile = bean.getMobile();
    if (oldUser != null) {
      ExceptionUtil.throwException(Errors.USER_MOBILE_EXISTS);
    }
    mobileCaptchaService.verify(mobile, bean.getCaptcha(), MobileCaptchaService.SmsType.REGISTER);
  }

}

package com.zhishangquan.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhishangquan.server.interceptor.ExcludeUrl;
import com.zhishangquan.server.response.SmsResponseBean;
import com.zhishangquan.server.service.MobileCaptchaService;
import com.zhishangquan.server.util.ResponseEntity;
import com.zhishangquan.server.util.ResponseEntityUtil;

/**
 * @author jzsong@uworks.cc
 */
@Api(value = "短信发送", produces = "application/json")
@RestController
@RequestMapping("/sms")
public class SmsController extends BaseController {
  @Resource
  private MobileCaptchaService mobileCaptchaService;

  @ApiOperation(value = "用户注册", notes = "用户注册")
  @ExcludeUrl
  @RequestMapping(value = "/userRegister/{mobile}", method = RequestMethod.GET)
  public ResponseEntity<SmsResponseBean> userRegister(@PathVariable("mobile") String mobile,
      HttpServletRequest request) {
    SmsResponseBean bean = mobileCaptchaService.userRegister(mobile);
    return ResponseEntityUtil.success(bean);
  }

}

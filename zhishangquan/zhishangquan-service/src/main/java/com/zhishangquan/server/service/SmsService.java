package com.zhishangquan.server.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zhishangquan.server.config.SmsConfig;
import com.zhishangquan.server.constants.Errors;
import com.zhishangquan.server.util.ExceptionUtil;
import com.zhishangquan.server.util.HttpUtil;
import com.zhishangquan.server.util.StringUtil;

/**
 * @author jzsong@uworks.cc
 */
@Service("smsService")
public class SmsService {

  private Logger logger = LoggerFactory.getLogger(getClass());
  @Resource
  private SmsConfig smsConfig;

  /**
   * 发送
   * 
   * @param mobile
   * @param content
   */
  public String send(String mobile, String content) {
    if (StringUtil.isBlank(mobile)) {
      ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "手机号不能为空");
    }
    if (StringUtil.isBlank(content)) {
      ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "短信内容不能为空");
    }
    JSONObject params = new JSONObject();
    params.put("content", content);
    params.put("mobile", mobile);
    params.put("sign", smsConfig.getSign());
    params.put("uid", smsConfig.getUid());
    params.put("token", smsConfig.getToken());
    String json = params.toJSONString();

    JSONObject response = null;
    try {
      response = HttpUtil.doPostJson(smsConfig.getUrl(), json);
    } catch (Exception e) {
      logger.info("发送短信失败", e);
      ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "发送短信失败");
    }
    if (response == null) {
      logger.info("发送短信响应结果：null" + response);
      return null;
    }
    logger.info("发送短信响应结果：" + response.toJSONString());
    return response.toJSONString();
  }

}

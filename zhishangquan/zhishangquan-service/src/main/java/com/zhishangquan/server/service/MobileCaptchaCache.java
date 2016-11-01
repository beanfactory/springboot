package com.zhishangquan.server.service;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码存储结构
 * 
 * @author jzsong@uworks.cc
 */
public class MobileCaptchaCache implements Serializable {

  private static final long serialVersionUID = -1472169310373137424L;

  /**
   * 验证码值
   */
  private String code;

  /**
   * 发送时间(用于验证是否发送频率太快)
   */
  private Date sentTime;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getSentTime() {
    return sentTime;
  }

  public void setSentTime(Date sentTime) {
    this.sentTime = sentTime;
  }

}

package com.zhishangquan.server.response;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 短信响应bean
 * 
 * @author jzsong@uworks.cc
 */
@ApiModel
public class SmsResponseBean implements Serializable {
  private static final long serialVersionUID = 5652411762366701231L;

  @ApiModelProperty(value = "过期时间")
  private Date expired;

  @ApiModelProperty(value = "冻结时间")
  private int coolDown;

  public int getCoolDown() {
    return coolDown;
  }

  public void setCoolDown(int coolDown) {
    this.coolDown = coolDown;
  }

  public Date getExpired() {
    return expired;
  }

  public void setExpired(Date expired) {
    this.expired = expired;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("SmsResponseBean {");
    sb.append("expired=").append(expired);
    sb.append(", coolDown=").append(coolDown);
    sb.append('}');
    return sb.toString();
  }
}

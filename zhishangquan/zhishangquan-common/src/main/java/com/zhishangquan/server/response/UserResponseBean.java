package com.zhishangquan.server.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author jzsong@uworks.cc
 */
@ApiModel
public class UserResponseBean extends BaseResponseBean {
  private static final long serialVersionUID = 3728106137910811040L;
  /**
   * 用户登录访问token
   */
  @ApiModelProperty(value = "用户登录访问token")
  private String accessToken;

  /** 手机号 */
  @ApiModelProperty(value = "手机号")
  private String mobile;

  /** 头像 */
  @ApiModelProperty(value = "头像")
  private String icon;

  /** 状态 */
  @ApiModelProperty(value = "状态")
  private Integer status;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

}

package com.zhishangquan.server.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserRegisterRequestBean {
  /**
   * 手机号
   */
  @NotBlank(message = "手机号不能为空")
  @ApiModelProperty(value = "手机号，必填", required = true)
  private String mobile;
  /**
   * 验证码
   */
  @NotBlank(message = "验证码不能为空")
  @ApiModelProperty(value = "验证，必填", required = true)
  private String captcha;
  /**
   * 密码
   */
  @NotBlank(message = "密码不能为空")
  @Length(min = 6, max = 16, message = "请输入6-16位密码")
  @ApiModelProperty(value = "密码，必填", required = true)
  private String password;

  public UserRegisterRequestBean() {

  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getCaptcha() {
    return captcha;
  }

  public void setCaptcha(String captcha) {
    this.captcha = captcha;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}

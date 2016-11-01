package com.zhishangquan.server.constants;

/**
 * 端来源
 * 
 * @author jzsong@uworks.cc
 */
public enum SideEnum {

  APP("APP", "移动端"), WEB("WEB", "网页"), WECHAT("WECHAT", "微信");
  public String code;
  public String label;

  private SideEnum(String code, String label) {
    this.code = code;
    this.label = label;
  }

}

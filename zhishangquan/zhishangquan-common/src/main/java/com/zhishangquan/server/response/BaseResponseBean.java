package com.zhishangquan.server.response;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 基础响应bean
 * 
 * @author jzsong@uworks.cc
 */
@ApiModel
public class BaseResponseBean implements Serializable {

  private static final long serialVersionUID = -1656390266896792435L;

  @ApiModelProperty(value = "对象主键")
  protected Long id;
  @ApiModelProperty(value = "创建时间")
  protected Date createTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

}

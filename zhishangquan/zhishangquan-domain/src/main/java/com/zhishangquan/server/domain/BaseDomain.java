package com.zhishangquan.server.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 基础领域模型
 * 
 * @author jzsong@uworks.cc
 */
@MappedSuperclass
public class BaseDomain implements Serializable {
  private static final long serialVersionUID = -1420855211569611556L;
  /**
   * 主键
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  /**
   * 创建时间
   */
  @Column(name = "create_time")
  private Date createTime;

  @java.beans.Transient
  @javax.persistence.Transient
  public boolean entityIsNew() {
    if (id == null) {
      return true;
    }
    return false;
  }

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

package com.zhishangquan.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户实体
 * 
 * @author jzsong@uworks.cc
 */
@Entity
@Table(name = "t_user")
public class User extends BaseDomain {
  private static final long serialVersionUID = -4559190465790402430L;
  /** 手机号 */
  @Column(name = "mobile", length = 15)
  private String mobile;

  /** 密码 */
  @Column(name = "password", length = 100)
  private String password;

  /** 头像 */
  @Column(name = "icon", length = 255)
  private String icon;

  /** 状态 :1正常,2冻结 */
  @Column(name = "status", length = 2)
  private Integer status;

  /** 昵称 */
  @Column(name = "user_name")
  private String userName;
  
  /**
   * 自我介绍
   */
  private String introduction;
  
  /**
   * 总余额(单位分)
   */
  @Column(name = "total_balance")
  private Integer totalBalance;
  
  /**
   * 可用余额(单位分)
   */
  @Column(name = "useable_balance")
  private Integer useableBalance;
  
  /**
   * 注册来源:1手机,2新浪,3qq,4微信
   */
  @Column(name = "regist_source")
  private Integer registSource;
  
  /**
   * qq
   */
  private String qq;
  
  /**
   * 微信号
   */
  private String weixin;
  
  /**
   * 版本号
   */
  private Integer version;
  
  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(Integer totalBalance) {
		this.totalBalance = totalBalance;
	}

	public Integer getUseableBalance() {
		return useableBalance;
	}

	public void setUseableBalance(Integer useableBalance) {
		this.useableBalance = useableBalance;
	}

	public Integer getRegistSource() {
		return registSource;
	}

	public void setRegistSource(Integer registSource) {
		this.registSource = registSource;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}

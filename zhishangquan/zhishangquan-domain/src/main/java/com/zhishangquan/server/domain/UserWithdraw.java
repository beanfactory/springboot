package com.zhishangquan.server.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: Answer.java
 * @Package com.zhishangquan.server.domain
 * @author liyuchang
 * @Description: 用户提现表
 * @date 2016年10月25日
 * @version V1.0
 */
@Entity
@Table(name = "t_user_withdraw")
public class UserWithdraw extends BaseDomain {
	private static final long serialVersionUID = 1063606121843324462L;

	/**
	 * 提现者id(t_user.id)
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 提现者昵称
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 提现金额
	 */
	private BigDecimal amount;

	/**
	 * 状态:1提现中,2成功,3失败
	 */
	private Integer status;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 版本号
	 */
	private Integer version;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
package com.zhishangquan.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: Answer.java
 * @Package com.zhishangquan.server.domain
 * @author liyuchang
 * @Description: 偷看记录表
 * @date 2016年10月25日
 * @version V1.0
 */
@Entity
@Table(name = "t_answer_peek")
public class AnswerPeek extends BaseDomain {
	private static final long serialVersionUID = 2822231057102204227L;

	/**
	 * 问题id(t_answer.id)
	 */
	@Column(name = "qustion_id")
	private Long qustionId;

	/**
	 * 偷看者id
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 偷看者昵称
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 偷看所用金额(单位分)
	 */
	private Integer amount;

	/**
	 * 状态:1待支付,2已支付
	 */
	private Integer status;

	public Long getQustionId() {
		return qustionId;
	}

	public void setQustionId(Long qustionId) {
		this.qustionId = qustionId;
	}

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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
package com.zhishangquan.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: Answer.java
 * @Package com.zhishangquan.server.domain
 * @author liyuchang
 * @Description: 点赞(打赏)表
 * @date 2016年10月25日
 * @version V1.0
 */
@Entity
@Table(name = "t_answer_reward")
public class AnswerReward extends BaseDomain {
	private static final long serialVersionUID = 5424283194766589899L;

	/**
	 * 回答id(t_answer.id)
	 */
	@Column(name = "answer_id")
	private Long answerId;

	/**
	 * 打赏者id
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 打赏者昵称
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 打赏所用金额(单位分)
	 */
	private Integer amount;

	/**
	 * 状态:1待支付,2已支付
	 */
	private Integer status;

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
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
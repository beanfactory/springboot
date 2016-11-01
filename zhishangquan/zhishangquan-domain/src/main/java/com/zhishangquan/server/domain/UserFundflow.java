package com.zhishangquan.server.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: Answer.java
 * @Package com.zhishangquan.server.domain
 * @author liyuchang
 * @Description: 用户资金流水表
 * @date 2016年10月25日
 * @version V1.0
 */
@Entity
@Table(name = "t_user_fundflow")
public class UserFundflow extends BaseDomain {

	private static final long serialVersionUID = 1400672698065906887L;

	/**
	 * 用户id(t_user.id)
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 交易金额
	 */
	private BigDecimal amount;

	/**
	 * 交易后用户余额
	 */
	@Column(name = "user_balance")
	private BigDecimal userbalance;

	/**
	 * 业务类型:1提问,2打赏,3偷看,4分享
	 */
	@Column(name = "business_type")
	private Integer businessType;

	/**
	 * 交易类型:1收入,2支出
	 */
	@Column(name = "trade_type")
	private Integer tradeType;

	/**
	 * 关联表id
	 */
	@Column(name = "relate_id")
	private Long relateId;

	/**
	 * 描述
	 */
	private String describe;

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

	public BigDecimal getUserbalance() {
		return userbalance;
	}

	public void setUserbalance(BigDecimal userbalance) {
		this.userbalance = userbalance;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Long getRelateId() {
		return relateId;
	}

	public void setRelateId(Long relateId) {
		this.relateId = relateId;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe == null ? null : describe.trim();
	}

}
package com.zhishangquan.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: Answer.java
 * @Package com.zhishangquan.server.domain
 * @author liyuchang
 * @Description: 支付信息表
 * @date 2016年10月25日
 * @version V1.0
 */
@Entity
@Table(name = "t_trade_info")
public class TradeInfo extends BaseDomain {

	private static final long serialVersionUID = -5823288978913838320L;

	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 金额(单位分)
	 */
	private Integer amount;

	/**
	 * 唯一编号
	 */
	@Column(name = "trade_code")
	private String tradeCode;

	/**
	 * 请求数据
	 */
	private String request;

	/**
	 * 响应数据
	 */
	private String response;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 错误信息
	 */
	@Column(name = "error_msg")
	private String errorMsg;

	/**
	 * 状态:1支付中,2支付成功,3支付失败
	 */
	private Integer status;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode == null ? null : tradeCode.trim();
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request == null ? null : request.trim();
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response == null ? null : response.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg == null ? null : errorMsg.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
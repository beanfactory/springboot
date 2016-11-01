package com.zhishangquan.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: Answer.java
 * @Package com.zhishangquan.server.domain
 * @author liyuchang
 * @Description: 收藏表
 * @date 2016年10月25日
 * @version V1.0
 */
@Entity
@Table(name = "t_collect")
public class Collect extends BaseDomain {
	private static final long serialVersionUID = 7182582849349483359L;

	/**
	 * 收藏者id(t_user.id)
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 类型:1问,2答,3偷看
	 */
	private Integer type;

	/**
	 * 关联id(问题id/回答id/偷看id)
	 */
	@Column(name = "relate_id")
	private Long relateId;

	/**
	 * 状态:1时效内,2时效外
	 */
	private Integer status;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getRelateId() {
		return relateId;
	}

	public void setRelateId(Long relateId) {
		this.relateId = relateId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
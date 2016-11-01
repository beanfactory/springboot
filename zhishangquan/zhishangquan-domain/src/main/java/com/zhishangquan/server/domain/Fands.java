package com.zhishangquan.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: Answer.java
 * @Package com.zhishangquan.server.domain
 * @author liyuchang
 * @Description: 粉丝表
 * @date 2016年10月25日
 * @version V1.0
 */
@Entity
@Table(name = "t_fands")
public class Fands extends BaseDomain {

	private static final long serialVersionUID = -414394899534259606L;

	/**
	 * 被关注者id
	 */
	@Column(name = "master_id")
	private Long masterId;

	/**
	 * 被关注者昵称
	 */
	@Column(name = "master_user_name")
	private String masterUserName;

	/**
	 * 关注者id(粉丝id)
	 */
	@Column(name = "follower_id")
	private Long followerId;

	/**
	 * 关注者昵称
	 */
	@Column(name = "follower_name")
	private String followerName;

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public String getMasterUserName() {
		return masterUserName;
	}

	public void setMasterUserName(String masterUserName) {
		this.masterUserName = masterUserName == null ? null : masterUserName.trim();
	}

	public Long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(Long followerId) {
		this.followerId = followerId;
	}

	public String getFollowerName() {
		return followerName;
	}

	public void setFollowerName(String followerName) {
		this.followerName = followerName == null ? null : followerName.trim();
	}

}
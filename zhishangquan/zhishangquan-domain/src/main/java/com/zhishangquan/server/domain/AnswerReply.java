package com.zhishangquan.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: Answer.java
 * @Package com.zhishangquan.server.domain
 * @author liyuchang
 * @Description: 追问表
 * @date 2016年10月25日
 * @version V1.0
 */
@Entity
@Table(name = "t_answer_reply")
public class AnswerReply extends BaseDomain {
	private static final long serialVersionUID = -4760880947614019439L;

	/**
	 * 回复人id
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 回复人姓名
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 被回复条id
	 */
	@Column(name = "reply_id")
	private Long replyId;

	/**
	 * 被回复条用户id
	 */
	@Column(name = "reply_user_id")
	private Long replyUserId;

	/**
	 * 被回复条用户昵称
	 */
	@Column(name = "reply_user_name")
	private String replyUserName;

	/**
	 * 针对的回答id(t_answer.id)
	 */
	@Column(name = "answer_id")
	private Long answerId;

	/**
	 * 回答表用户昵称
	 */
	@Column(name = "answer_user_name")
	private String answerUserName;

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

	public Long getReplyId() {
		return replyId;
	}

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}

	public Long getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(Long replyUserId) {
		this.replyUserId = replyUserId;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName == null ? null : replyUserName.trim();
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getAnswerUserName() {
		return answerUserName;
	}

	public void setAnswerUserName(String answerUserName) {
		this.answerUserName = answerUserName == null ? null : answerUserName.trim();
	}

}
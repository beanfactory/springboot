package com.zhishangquan.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: Answer.java
 * @Package com.zhishangquan.server.domain
 * @author liyuchang
 * @Description: 回答表
 * @date 2016年10月25日
 * @version V1.0
 */
@Entity
@Table(name = "t_answer")
public class Answer extends BaseDomain {
	private static final long serialVersionUID = 1254621537185900463L;

	/**
	 * 问题id(t_question.id)
	 */
	@Column(name = "question_id")
	private Long questionId;

	/**
	 * 回答人id(t_user.id)
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 回答人昵称
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * 回答内容
	 */
	@Column(name = "answer_desc")
	private String answerDesc;

	/**
	 * 图片地址
	 */
	private String photo;

	/**
	 * 音频地址
	 */
	private String audio;

	/**
	 * 被赞数
	 */
	@Column(name = "praise_count")
	private Integer praiseCount;

	/**
	 * 盈利金额(单位分)
	 */
	private Integer profit;

	/**
	 * 状态:1未被采纳,2被采纳
	 */
	private Integer status;

	/**
	 * 版本号
	 */
	private Integer version;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
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

	public String getAnswerDesc() {
		return answerDesc;
	}

	public void setAnswerDesc(String answerDesc) {
		this.answerDesc = answerDesc == null ? null : answerDesc.trim();
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo == null ? null : photo.trim();
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio == null ? null : audio.trim();
	}

	public Integer getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}

	public Integer getProfit() {
		return profit;
	}

	public void setProfit(Integer profit) {
		this.profit = profit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
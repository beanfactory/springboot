package com.zhishangquan.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: Answer.java
 * @Package com.zhishangquan.server.domain
 * @author liyuchang
 * @Description: 用户信息统计表
 * @date 2016年10月25日
 * @version V1.0
 */
@Entity
@Table(name = "t_user_statistic")
public class UserStatistic extends BaseDomain {
	private static final long serialVersionUID = 8591838577296169004L;

	/**
	 * 用户id
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 粉丝数量
	 */
	@Column(name = "fans_num")
	private Integer fansNum;

	/**
	 * 关注数量
	 */
	@Column(name = "follow_num")
	private Integer followNum;

	/**
	 * 互粉数量
	 */
	@Column(name = "fands_eachother_num")
	private Integer fandsEachotherNum;

	/**
	 * 我的问数量
	 */
	@Column(name = "question_num")
	private Integer questionNum;

	/**
	 * 我的答数量
	 */
	@Column(name = "answer_num")
	private Integer answerNum;

	/**
	 * 邀我答数量
	 */
	@Column(name = "invite_question_num")
	private Integer inviteQuestionNum;

	/**
	 * 收藏数量
	 */
	@Column(name = "collect_num")
	private Integer collectNum;

	/**
	 * 总收益(单位分)
	 */
	@Column(name = "total_income")
	private Integer totalIncome;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getFansNum() {
		return fansNum;
	}

	public void setFansNum(Integer fansNum) {
		this.fansNum = fansNum;
	}

	public Integer getFollowNum() {
		return followNum;
	}

	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}

	public Integer getFandsEachotherNum() {
		return fandsEachotherNum;
	}

	public void setFandsEachotherNum(Integer fandsEachotherNum) {
		this.fandsEachotherNum = fandsEachotherNum;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}

	public Integer getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(Integer answerNum) {
		this.answerNum = answerNum;
	}

	public Integer getInviteQuestionNum() {
		return inviteQuestionNum;
	}

	public void setInviteQuestionNum(Integer inviteQuestionNum) {
		this.inviteQuestionNum = inviteQuestionNum;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public Integer getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Integer totalIncome) {
		this.totalIncome = totalIncome;
	}
}
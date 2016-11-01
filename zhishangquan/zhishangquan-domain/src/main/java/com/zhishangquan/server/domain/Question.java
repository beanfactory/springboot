package com.zhishangquan.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: Question.java
 * @Package com.zhishangquan.server.domain
 * @author liyuchang
 * @Description: 问题表
 * @date 2016年10月25日
 * @version V1.0
 */
@Entity
@Table(name = "t_question")
public class Question extends BaseDomain {
	private static final long serialVersionUID = 794770276678377185L;

		/**
     * 提问者id(t_user.id)
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 提问者昵称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 问题标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 问题描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 问题价格(单位分)
     */
    @Column(name = "prize")
    private Integer prize;

    /**
     * 时效天数
     */
    @Column(name = "days")
    private Integer days;

    /**
     * 类型:1秘密(无法偷看),2公开,3私问(不在列表显示)
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 私有类型时回答者id
     */
    @Column(name = "answer_id")
    private Long answerId;

    /**
     * 过期时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 被偷看总次数
     */
    @Column(name = "peek_count")
    private Integer peekCount;

    /**
     * 被回答总次数
     */
    @Column(name = "answer_count")
    private Integer answerCount;

    /**
     * 盈利金额(单位分)
     */
    @Column(name = "profit")
    private Integer profit;

    /**
     * 状态:1待支付,2已支付
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 版本号
     */
    @Column(name = "version")
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPeekCount() {
        return peekCount;
    }

    public void setPeekCount(Integer peekCount) {
        this.peekCount = peekCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
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
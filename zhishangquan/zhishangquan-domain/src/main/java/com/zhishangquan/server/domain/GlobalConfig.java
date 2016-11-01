package com.zhishangquan.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Title: Answer.java
 * @Package com.zhishangquan.server.domain
 * @author liyuchang
 * @Description: 系统配置表
 * @date 2016年10月25日
 * @version V1.0
 */
@Entity
@Table(name = "t_global_config")
public class GlobalConfig extends BaseDomain {

	private static final long serialVersionUID = -6995365806108173447L;

	/**
	 * 字段参数
	 */
	@Column(name = "parameter_name")
	private String parameterName;

	/**
	 * 字段值
	 */
	@Column(name = "parameter_value")
	private String parameterValue;

	/**
	 * 描述
	 */
	@Column(name = "parameter_desc")
	private String parameterDesc;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 版本号
	 */
	private Integer version;

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName == null ? null : parameterName.trim();
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue == null ? null : parameterValue.trim();
	}

	public String getParameterDesc() {
		return parameterDesc;
	}

	public void setParameterDesc(String parameterDesc) {
		this.parameterDesc = parameterDesc == null ? null : parameterDesc.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
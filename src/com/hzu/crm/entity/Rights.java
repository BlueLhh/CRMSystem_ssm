package com.hzu.crm.entity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 权限表实体类
 * @author Administrator
 *
 */
public class Rights {
	private long rid;
	private String rightName; // 权限名称
	private String rightType; // 权限级别
	private String url; // 根据权限功能打开选项卡，选项卡中include的JSP页面名称连接
	@JSONField(serialize = false)
	private Rights rig; // 自连接，一级权限pid和自身的rid相同，二级权限是其对应的一级权限的rid
	@JSONField(serialize = false)
	private List<JobRight> jobRigList = new ArrayList<JobRight>(); // 一种权限可以被多种职位拥有

	public Rights() {
	}

	public Rights(String rightName, String rightType, String url) {
		this.rightName = rightName;
		this.rightType = rightType;
		this.url = url;
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightType() {
		return rightType;
	}

	public void setRightType(String rightType) {
		this.rightType = rightType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Rights getRig() {
		return rig;
	}

	public void setRig(Rights rig) {
		this.rig = rig;
	}

	public List<JobRight> getJobRigList() {
		return jobRigList;
	}

	public void setJobRigList(List<JobRight> jobRigList) {
		this.jobRigList = jobRigList;
	}

	@Override
	public String toString() {
		return "Rights [rid=" + rid + ", rightName=" + rightName + ", rightType=" + rightType + ", url=" + url + "]";
	}
}

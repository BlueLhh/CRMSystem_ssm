package com.hzu.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 职位权限对照信息表实体类
 * @author Administrator
 *
 */
@JsonIgnoreProperties(value = {"handler"})
public class JobRight {
	private long id;
	private Rights rights; // 权限表外键
	private JobInfo jobInfo; // 职位信息表外键

	public JobRight() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public JobInfo getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(JobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}

	public Rights getRights() {
		return rights;
	}

	public void setRights(Rights rights) {
		this.rights = rights;
	}

	@Override
	public String toString() {
		return "JobRight [id=" + id + "]";
	}
}

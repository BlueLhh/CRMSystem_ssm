package com.hzu.crm.entity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ְλ��Ϣ��ʵ����
 * @author Administrator
 *
 */
public class JobInfo {
	private long id;
	private String job; // ְλ����
	private Department dept; // ���ű����ʵ����
	@JSONField(serialize = false)
	private List<Employee> empList; // ͬһ��ְλ�ж���Ա��
	@JSONField(serialize = false)
	private List<JobRight> jobRigList = new ArrayList<JobRight>();// һ��ְλ�ж���Ȩ��

	public JobInfo() {
	}

	public JobInfo(String job) {
		this.job = job;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public List<JobRight> getJobRigList() {
		return jobRigList;
	}

	public void setJobRigList(List<JobRight> jobRigList) {
		this.jobRigList = jobRigList;
	}

	@Override
	public String toString() {
		return "JobInfo [id=" + id + ", job=" + job + "]";
	}
}

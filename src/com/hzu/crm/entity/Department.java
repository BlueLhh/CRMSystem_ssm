package com.hzu.crm.entity;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * ������Ϣʵ����
 * @author Administrator
 *
 */
public class Department {
	private long id;
	private String dname; // ��������
	@JSONField(serialize = false)
	private List<JobInfo> jobList; // һ�������ж��ְλ
	@JSONField(serialize = false)
	private List<Employee> empList; // һ�������ж��Ա��

	public Department() {
	}

	public Department(String dname) {
		this.dname = dname;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public List<JobInfo> getJobList() {
		return jobList;
	}

	public void setJobList(List<JobInfo> jobList) {
		this.jobList = jobList;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", dname=" + dname + "]";
	}
}

package com.hzu.crm.entity;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 部门信息实体类
 * @author Administrator
 *
 */
public class Department {
	private long id;
	private String dname; // 部门名称
	@JSONField(serialize = false)
	private List<JobInfo> jobList; // 一个部门有多个职位
	@JSONField(serialize = false)
	private List<Employee> empList; // 一个部门有多个员工

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

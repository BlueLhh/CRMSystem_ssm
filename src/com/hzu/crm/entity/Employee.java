package com.hzu.crm.entity;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 员工表实体类
 * 
 * @author Administrator
 *
 */
public class Employee {
	private long id;
	private String username;
	private String pass;
	private String nickname; // 昵称
	private String realname; // 真实姓名
	private String phoneNo;
	private String officeTel; // 办公电话
	private String workstatu; // 在职状态 1表示在职，0表示离职。离职后不能登陆系统
	private JobInfo jobInfo = new JobInfo(); // 职位信息表外键
	private Department dept = new Department(); // 部门表外键
	@JSONField(serialize = false) // 转json字符串时，忽略此属性
	private List<CustomerInfo> infoList; // 一名员工负责多名客户
	@JSONField(serialize = false)
	private List<ConsultRecord> consultList; // 一名员工可以负责咨询多名客户

	public Employee() {

	}

	public Employee(String username, String pass, String nickname, String realname, String phoneNo, String officeTel,
			String workstatu) {
		this.username = username;
		this.pass = pass;
		this.nickname = nickname;
		this.realname = realname;
		this.phoneNo = phoneNo;
		this.officeTel = officeTel;
		this.workstatu = workstatu;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getWorkstatu() {
		return workstatu;
	}

	public void setWorkstatu(String workstatu) {
		this.workstatu = workstatu;
	}

	public JobInfo getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(JobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public List<CustomerInfo> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<CustomerInfo> infoList) {
		this.infoList = infoList;
	}

	public List<ConsultRecord> getConsultList() {
		return consultList;
	}

	public void setConsultList(List<ConsultRecord> consultList) {
		this.consultList = consultList;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", pass=" + pass + ", nickname=" + nickname
				+ ", realname=" + realname + ", phoneNo=" + phoneNo + ", officeTel=" + officeTel + ", workstatu="
				+ workstatu + "]";
	}
}

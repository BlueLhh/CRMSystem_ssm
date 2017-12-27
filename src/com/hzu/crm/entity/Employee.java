package com.hzu.crm.entity;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Ա����ʵ����
 * 
 * @author Administrator
 *
 */
public class Employee {
	private long id;
	private String username;
	private String pass;
	private String nickname; // �ǳ�
	private String realname; // ��ʵ����
	private String phoneNo;
	private String officeTel; // �칫�绰
	private String workstatu; // ��ְ״̬ 1��ʾ��ְ��0��ʾ��ְ����ְ���ܵ�½ϵͳ
	private JobInfo jobInfo = new JobInfo(); // ְλ��Ϣ�����
	private Department dept = new Department(); // ���ű����
	@JSONField(serialize = false) // תjson�ַ���ʱ�����Դ�����
	private List<CustomerInfo> infoList; // һ��Ա����������ͻ�
	@JSONField(serialize = false)
	private List<ConsultRecord> consultList; // һ��Ա�����Ը�����ѯ�����ͻ�

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

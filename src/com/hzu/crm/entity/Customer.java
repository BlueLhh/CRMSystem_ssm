package com.hzu.crm.entity;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 客户表实体类
 * @author Administrator
 *
 */
public class Customer {
	private long id;
	private String name;
	private String education; // 教育水平
	private String phoneNo;
	private String qq;
	private String email; // customStatu客户状态:0： 新增未上门 1：新增已上门 2：销售跟进中
	private String customStatu; // 3：咨询跟进中 4：死单 5：已报名
	private Date createDate; // 创建日期
	private String inviteName; // 邀请人姓名 在网咨员工添加时，将自己的名字加进去
	private String empName; // 保存负责该客户的员工名，不写入数据库
	@JSONField(serialize = false)
	private List<CustomerInfo> infoList; // 一个客户拥有多个客户跟踪表信息
	@JSONField(serialize = false)
	private List<ConsultRecord> consultList;// 一个客户拥有多个咨询跟踪表信息

	public Customer() {

	}

	public Customer(String name, String education, String phoneNo, String qq, String email, String customStatu,
			Date createDate, String inviteName) {
		this.name = name;
		this.education = education;
		this.phoneNo = phoneNo;
		this.qq = qq;
		this.email = email;
		this.customStatu = customStatu;
		this.createDate = createDate;
		this.inviteName = inviteName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomStatu() {
		return customStatu;
	}

	public void setCustomStatu(String customStatu) {
		this.customStatu = customStatu;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getInviteName() {
		return inviteName;
	}

	public void setInviteName(String inviteName) {
		this.inviteName = inviteName;
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", education=" + education + ", phoneNo=" + phoneNo + ", qq="
				+ qq + ", email=" + email + ", customStatu=" + customStatu + ", createDate=" + createDate
				+ ", inviteName=" + inviteName + "]";
	}
}

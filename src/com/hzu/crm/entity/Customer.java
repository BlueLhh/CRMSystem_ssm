package com.hzu.crm.entity;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * �ͻ���ʵ����
 * @author Administrator
 *
 */
public class Customer {
	private long id;
	private String name;
	private String education; // ����ˮƽ
	private String phoneNo;
	private String qq;
	private String email; // customStatu�ͻ�״̬:0�� ����δ���� 1������������ 2�����۸�����
	private String customStatu; // 3����ѯ������ 4������ 5���ѱ���
	private Date createDate; // ��������
	private String inviteName; // ���������� ������Ա�����ʱ�����Լ������ּӽ�ȥ
	private String empName; // ���渺��ÿͻ���Ա��������д�����ݿ�
	@JSONField(serialize = false)
	private List<CustomerInfo> infoList; // һ���ͻ�ӵ�ж���ͻ����ٱ���Ϣ
	@JSONField(serialize = false)
	private List<ConsultRecord> consultList;// һ���ͻ�ӵ�ж����ѯ���ٱ���Ϣ

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

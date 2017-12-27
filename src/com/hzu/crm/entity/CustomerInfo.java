package com.hzu.crm.entity;
/**
 * ���ۿͻ�������Ϣ��ʵ����
 * @author Administrator
 *
 */

import java.util.Date;

public class CustomerInfo {
	private long id; // statu����״̬ 0��δ��ϵ 1��δ��ͨ 2������ 3��������
	private String statu; // 4������(����ʱͬʱ���¿ͻ�������Ϣ��״̬)��5���绰��Ч
	private Date startDate; // ��ʼ����
	private Date lastFollowDate;// �����ϵ����
	private Date planDate; // �ƻ���ϵ����
	private String mark;// ������ע
	private Customer customer; // �ͻ������
	private Employee employee; // Ա�������
	private long cusId; // �ͻ�id�����������ݿ�
	private String cusName; // �ͻ����������������ݿ�
	private String empName; // Ա�����������������ݿ�
	private int count; // �����¼����
	public CustomerInfo() {

	}

	public CustomerInfo(String statu, Date startDate, Date lastFollowDate, Date planDate, String mark) {
		this.statu = statu;
		this.startDate = startDate;
		this.lastFollowDate = lastFollowDate;
		this.planDate = planDate;
		this.mark = mark;
	}

	public long getId() {
		return id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getLastFollowDate() {
		return lastFollowDate;
	}

	public void setLastFollowDate(Date lastFollowDate) {
		this.lastFollowDate = lastFollowDate;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public long getCusId() {
		return cusId;
	}

	public void setCusId(long cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Override
	public String toString() {
		return "CustomInfo [id=" + id + ", statu=" + statu + ", startDate=" + startDate + ", lastFollowDate="
				+ lastFollowDate + ", planDate=" + planDate + ", mark=" + mark + "]";
	}
}

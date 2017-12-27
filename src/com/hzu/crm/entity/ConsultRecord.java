package com.hzu.crm.entity;
/**
 * ��ѯʦ������¼��
 * @author Administrator
 *
 */

import java.util.Date;

public class ConsultRecord {
	private long id; // consultStatu��ѯ״̬ 0������ 1������ 2���ѱ���
	private String consultStatu; // 3������(����ʱͬʱ���¿ͻ�������Ϣ��״̬) 4�������˷�
	private Date consultDate; // ��ѯ����
	private String result;// ��ѯ��ע
	private Customer customer; // �ͻ������
	private Employee employee; // Ա�������
	private long cusId; // �ͻ�id�����������ݿ�
	private String cusName; // �ͻ����������������ݿ�
	private String empName; // Ա�����������������ݿ�
	private int count; //��¼ÿ��Ա����ÿ��״̬����ѯ��������д�����ݿ�
	
	public ConsultRecord() {
	}

	public ConsultRecord(String consultStatu, Date consultDate, String result) {
		this.consultStatu = consultStatu;
		this.consultDate = consultDate;
		this.result = result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getConsultStatu() {
		return consultStatu;
	}

	public void setConsultStatu(String consultStatu) {
		this.consultStatu = consultStatu;
	}

	public Date getConsultDate() {
		return consultDate;
	}

	public void setConsultDate(Date consultDate) {
		this.consultDate = consultDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ConsultRecord [id=" + id + ", consultStatu=" + consultStatu + ", consultDate=" + consultDate
				+ ", result=" + result + "]";
	}
}

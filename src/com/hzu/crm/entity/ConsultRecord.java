package com.hzu.crm.entity;
/**
 * 咨询师跟单记录表
 * @author Administrator
 *
 */

import java.util.Date;

public class ConsultRecord {
	private long id; // consultStatu咨询状态 0：新增 1：紧跟 2：已报名
	private String consultStatu; // 3：死单(死单时同时更新客户基础信息表状态) 4报名后退费
	private Date consultDate; // 咨询日期
	private String result;// 咨询备注
	private Customer customer; // 客户表外键
	private Employee employee; // 员工表外键
	private long cusId; // 客户id，不存入数据库
	private String cusName; // 客户姓名，不存入数据库
	private String empName; // 员工姓名，不存入数据库
	private int count; //记录每个员工，每种状态的咨询单数，不写入数据库
	
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

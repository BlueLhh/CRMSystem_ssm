package com.hzu.crm.entity;
/**
 * 销售客户跟踪信息表实体类
 * @author Administrator
 *
 */

import java.util.Date;

public class CustomerInfo {
	private long id; // statu跟单状态 0：未联系 1：未接通 2：紧跟 3：已上门
	private String statu; // 4：死单(死单时同时更新客户基础信息表状态)，5：电话无效
	private Date startDate; // 开始日期
	private Date lastFollowDate;// 最近联系日期
	private Date planDate; // 计划联系日期
	private String mark;// 跟单备注
	private Customer customer; // 客户表外键
	private Employee employee; // 员工表外键
	private long cusId; // 客户id，不存入数据库
	private String cusName; // 客户姓名，不存入数据库
	private String empName; // 员工姓名，不存入数据库
	private int count; // 保存记录总数
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

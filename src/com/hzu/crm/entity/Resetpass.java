package com.hzu.crm.entity;

/**
 * 重置密码记录表的实体类
 * @author Administrator
 *
 */
public class Resetpass {
	private long id;
	private String username;
	private String phoneNo;

	public Resetpass() {
	}

	public Resetpass(String username, String phoneNo) {
		this.username = username;
		this.phoneNo = phoneNo;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "Resetpass [id=" + id + ", username=" + username + ", phoneNo=" + phoneNo + "]";
	}

}

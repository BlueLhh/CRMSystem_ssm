package com.hzu.crm.entity;

/**
 * 保存销售单记录的实体类类，该实体类不写入数据库
 * 只做为前端和后台传递数据的媒介
 * @author Administrator
 *
 */
public class CusInfoRecCount {
	private String empName; // 负责员工姓名
	private int noConnCount; // 未联系订单数
	private int noCallCount; // 未接通的订单数
	private int followCount;// 紧跟的数目
	private int deathCount; // 死单数目
	private int visitCount; // 上门数目
	private int callLoseCount; // 电话失效
	private int count; // 记录总数
	private String statu;// 记录状态
	private long empId;// 负责员工id

	public CusInfoRecCount() {
	}

	public CusInfoRecCount(String statu, int count) {
		this.count = count;
		this.statu = statu;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getNoConnCount() {
		return noConnCount;
	}

	public void setNoConnCount(int noConnCount) {
		this.noConnCount = noConnCount;
	}

	public int getNoCallCount() {
		return noCallCount;
	}

	public void setNoCallCount(int noCallCount) {
		this.noCallCount = noCallCount;
	}

	public int getFollowCount() {
		return followCount;
	}

	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}

	public int getDeathCount() {
		return deathCount;
	}

	public void setDeathCount(int deathCount) {
		this.deathCount = deathCount;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public int getCallLoseCount() {
		return callLoseCount;
	}

	public void setCallLoseCount(int callLoseCount) {
		this.callLoseCount = callLoseCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	@Override
	public String toString() {
		return "CusInfoRecCount [empName=" + empName + ", noConnCount=" + noConnCount + ", noCallCount=" + noCallCount
				+ ", followCount=" + followCount + ", deathCount=" + deathCount + ", visitCount=" + visitCount
				+ ", callLoseCount=" + callLoseCount + ", count=" + count + ", statu=" + statu + ", empId=" + empId
				+ "]";
	}

}

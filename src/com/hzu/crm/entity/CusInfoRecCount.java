package com.hzu.crm.entity;

/**
 * �������۵���¼��ʵ�����࣬��ʵ���಻д�����ݿ�
 * ֻ��Ϊǰ�˺ͺ�̨�������ݵ�ý��
 * @author Administrator
 *
 */
public class CusInfoRecCount {
	private String empName; // ����Ա������
	private int noConnCount; // δ��ϵ������
	private int noCallCount; // δ��ͨ�Ķ�����
	private int followCount;// ��������Ŀ
	private int deathCount; // ������Ŀ
	private int visitCount; // ������Ŀ
	private int callLoseCount; // �绰ʧЧ
	private int count; // ��¼����
	private String statu;// ��¼״̬
	private long empId;// ����Ա��id

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

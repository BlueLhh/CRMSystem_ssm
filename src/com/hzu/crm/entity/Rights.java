package com.hzu.crm.entity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Ȩ�ޱ�ʵ����
 * @author Administrator
 *
 */
public class Rights {
	private long rid;
	private String rightName; // Ȩ������
	private String rightType; // Ȩ�޼���
	private String url; // ����Ȩ�޹��ܴ�ѡ���ѡ���include��JSPҳ����������
	@JSONField(serialize = false)
	private Rights rig; // �����ӣ�һ��Ȩ��pid�������rid��ͬ������Ȩ�������Ӧ��һ��Ȩ�޵�rid
	@JSONField(serialize = false)
	private List<JobRight> jobRigList = new ArrayList<JobRight>(); // һ��Ȩ�޿��Ա�����ְλӵ��

	public Rights() {
	}

	public Rights(String rightName, String rightType, String url) {
		this.rightName = rightName;
		this.rightType = rightType;
		this.url = url;
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightType() {
		return rightType;
	}

	public void setRightType(String rightType) {
		this.rightType = rightType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Rights getRig() {
		return rig;
	}

	public void setRig(Rights rig) {
		this.rig = rig;
	}

	public List<JobRight> getJobRigList() {
		return jobRigList;
	}

	public void setJobRigList(List<JobRight> jobRigList) {
		this.jobRigList = jobRigList;
	}

	@Override
	public String toString() {
		return "Rights [rid=" + rid + ", rightName=" + rightName + ", rightType=" + rightType + ", url=" + url + "]";
	}
}

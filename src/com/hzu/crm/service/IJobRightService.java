package com.hzu.crm.service;

import java.util.List;

import com.hzu.crm.entity.JobRight;
/**
 * ְλȨ�޶��ձ�����ӿ�
 * @author Administrator
 *
 */
public interface IJobRightService {

	/**
	 * Ϊĳְλ���ĳȨ��
	 * @return
	 */
	public int addJobRight(JobRight jobRight);
	
	/**
	 * ��ѯ����ְλȨ�޶��ձ��¼
	 * @return
	 */
	public List<JobRight> findAllJobRig();
	
	/**
	 * ����idɾ����¼
	 * @param id
	 * @return
	 */
	public int delJobRigByID(long id);
	
	/**
	 * �������ɾ����¼
	 * @param rid
	 * @param jid
	 * @return
	 */
	public int delJobRigByFK(long rid,long jid);
}

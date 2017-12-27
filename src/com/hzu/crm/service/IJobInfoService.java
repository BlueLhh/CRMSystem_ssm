package com.hzu.crm.service;

import java.util.List;

import com.hzu.crm.entity.JobInfo;

/**
 * ְλ�����ӿ�
 * @author Administrator
 *
 */
public interface IJobInfoService {

	/**
	 * ���ְλ
	 * @param job
	 * @return
	 */
	public int addJob(JobInfo job);

	/**
	 * ����ְλ
	 * @param job
	 * @return
	 */
	public int updateJob(JobInfo job);

	/**
	 * ����id��ѯְλ
	 * @param id
	 * @return
	 */
	public JobInfo findJobById(long id);
	
	/**
	 * ����ְλ���Ʋ�ѯ
	 * @param jobName
	 * @return
	 */
	public JobInfo findJobByName(String jobName);

	/**
	 * ��ѯ����ְλ��Ϣ
	 * @return
	 */
	public List<JobInfo> findAllJob();

	/**
	 * ���ݲ��������ȡְλ����
	 * @param deptId
	 * @return
	 */
	public List<JobInfo> findJobNameByDeptId(long deptId);
}

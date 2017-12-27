package com.hzu.crm.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.JobInfo;

/**
 * ְλ��Ϣ��Dao�ӿ�
 * @author Administrator
 *
 */
@MapperScan
public interface IJobInfoDao {
	/**
	 * ����id��ѯְλ��Ϣ
	 * @param id
	 * @return
	 */
	public JobInfo findById(long id);
	
	/**
	 * ����ְλ���Ʋ�ѯְλ��Ϣ
	 * @param job
	 * @return
	 */
	public JobInfo findByJob(String job);

	/**
	 * ���ݲ��������ѯְλ
	 * @param deptId
	 * @return
	 */
	public List<JobInfo> findByDeptId(long deptId);
	
	/**
	 * ���ݲ��������ѯְλ��
	 * @param deptId
	 * @return
	 */
	public List<JobInfo> findJobNameByDeptId(long deptId);

	/**
	 * ��ѯ����ְλ
	 * @return
	 */
	public List<JobInfo> findAll();

	/**
	 * ���ְλ
	 * @param 
	 * @return
	 */
	public int insert(JobInfo info);

	/**
	 * ����ְλ
	 * @param 
	 * @return
	 */
	public int update(JobInfo info);

	/**
	 * ����idɾ��ְλ
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

package com.hzu.crm.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.JobRight;

/**
 * ְλȨ����Ϣ��Dao�ӿ�
 * @author Administrator
 *
 */
@MapperScan
public interface IJobRightDao {
	/**
	 * ����id��ѯ��ְλȨ��
	 * @param id
	 * @return
	 */
	public JobRight findById(long id);

	/**
	 * ����Ȩ�ޱ������ѯӵ�и�Ȩ�޵�ְλ
	 * @param
	 * @return
	 */
	public List<JobRight> findByRigId(long rid);

	/**
	 * ����ְλ��Ϣ�������ѯ��ְλӵ�е�Ȩ��
	 * @param 
	 * @return
	 */
	public List<JobRight> findByJobId(long jid);

	/**
	 * ��ѯ����Ȩ��
	 * @return
	 */
	public List<JobRight> findAll();

	/**
	 * ��Ӹ�ְλȨ��
	 * @param 
	 * @return
	 */
	public int insert(JobRight jRig);

	/**
	 * ����Ȩ��
	 * @param 
	 * @return
	 */
	public int update(JobRight rig);

	/**
	 * ����idɾ����ְλȨ��
	 * @param id
	 * @return
	 */
	public int deleteById(long id);

	/**
	 * �����������ɾ��ְλȨ�޼�¼
	 * @param rid
	 * @param jid
	 * @return
	 */
	public int deleteByFK(long rid, long jid);
}

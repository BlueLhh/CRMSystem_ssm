package com.hzu.crm.service;

import java.util.List;

import com.hzu.crm.entity.JobRight;
/**
 * 职位权限对照表服务层接口
 * @author Administrator
 *
 */
public interface IJobRightService {

	/**
	 * 为某职位添加某权限
	 * @return
	 */
	public int addJobRight(JobRight jobRight);
	
	/**
	 * 查询所有职位权限对照表记录
	 * @return
	 */
	public List<JobRight> findAllJobRig();
	
	/**
	 * 根据id删除记录
	 * @param id
	 * @return
	 */
	public int delJobRigByID(long id);
	
	/**
	 * 根据外键删除记录
	 * @param rid
	 * @param jid
	 * @return
	 */
	public int delJobRigByFK(long rid,long jid);
}

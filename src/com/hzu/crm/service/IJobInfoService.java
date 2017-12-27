package com.hzu.crm.service;

import java.util.List;

import com.hzu.crm.entity.JobInfo;

/**
 * 职位服务层接口
 * @author Administrator
 *
 */
public interface IJobInfoService {

	/**
	 * 添加职位
	 * @param job
	 * @return
	 */
	public int addJob(JobInfo job);

	/**
	 * 更新职位
	 * @param job
	 * @return
	 */
	public int updateJob(JobInfo job);

	/**
	 * 根据id查询职位
	 * @param id
	 * @return
	 */
	public JobInfo findJobById(long id);
	
	/**
	 * 根据职位名称查询
	 * @param jobName
	 * @return
	 */
	public JobInfo findJobByName(String jobName);

	/**
	 * 查询所有职位信息
	 * @return
	 */
	public List<JobInfo> findAllJob();

	/**
	 * 根据部门外键获取职位名称
	 * @param deptId
	 * @return
	 */
	public List<JobInfo> findJobNameByDeptId(long deptId);
}

package com.hzu.crm.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.JobInfo;

/**
 * 职位信息表Dao接口
 * @author Administrator
 *
 */
@MapperScan
public interface IJobInfoDao {
	/**
	 * 根据id查询职位信息
	 * @param id
	 * @return
	 */
	public JobInfo findById(long id);
	
	/**
	 * 根据职位名称查询职位信息
	 * @param job
	 * @return
	 */
	public JobInfo findByJob(String job);

	/**
	 * 根据部门外键查询职位
	 * @param deptId
	 * @return
	 */
	public List<JobInfo> findByDeptId(long deptId);
	
	/**
	 * 根据部门外键查询职位名
	 * @param deptId
	 * @return
	 */
	public List<JobInfo> findJobNameByDeptId(long deptId);

	/**
	 * 查询所有职位
	 * @return
	 */
	public List<JobInfo> findAll();

	/**
	 * 添加职位
	 * @param 
	 * @return
	 */
	public int insert(JobInfo info);

	/**
	 * 更新职位
	 * @param 
	 * @return
	 */
	public int update(JobInfo info);

	/**
	 * 根据id删除职位
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

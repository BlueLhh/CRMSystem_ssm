package com.hzu.crm.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.JobRight;

/**
 * 职位权限信息表Dao接口
 * @author Administrator
 *
 */
@MapperScan
public interface IJobRightDao {
	/**
	 * 根据id查询该职位权限
	 * @param id
	 * @return
	 */
	public JobRight findById(long id);

	/**
	 * 根据权限表外键查询拥有该权限的职位
	 * @param
	 * @return
	 */
	public List<JobRight> findByRigId(long rid);

	/**
	 * 根据职位信息表外键查询该职位拥有的权限
	 * @param 
	 * @return
	 */
	public List<JobRight> findByJobId(long jid);

	/**
	 * 查询所有权限
	 * @return
	 */
	public List<JobRight> findAll();

	/**
	 * 添加该职位权限
	 * @param 
	 * @return
	 */
	public int insert(JobRight jRig);

	/**
	 * 更新权限
	 * @param 
	 * @return
	 */
	public int update(JobRight rig);

	/**
	 * 根据id删除该职位权限
	 * @param id
	 * @return
	 */
	public int deleteById(long id);

	/**
	 * 根据两个外键删除职位权限记录
	 * @param rid
	 * @param jid
	 * @return
	 */
	public int deleteByFK(long rid, long jid);
}

package com.hzu.crm.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.Rights;

/**
 * 权限表Dao接口
 * @author Administrator
 *
 */
@MapperScan
public interface IRightsDao {
	/**
	 * 根据id查询权限
	 * @param id
	 * @return
	 */
	public Rights findById(long id);

	/**
	 * 根据权限名称查询权限
	 * @param rightName
	 * @return
	 */
	public Rights findByName(String rightName);
	
	/**
	 * 根据权限表外键查询权限，自连接
	 * @param deptId
	 * @return
	 */
	public List<Rights> findByRigId(long pid);

	/**
	 * 查询所有权限
	 * @return
	 */
	public List<Rights> findAll();

	/**
	 * 查询权限总数
	 * @return
	 */
	public int count();
	
	/**
	 * 分页查询
	 * @param page 当前页码
	 * @param pageSize 页码显示
	 * @return
	 */
	public List<Rights> findByPage(int min,int max);
	
	/**
	 * 添加权限	
	 * @param 
	 * @return
	 */
	public int insert(Rights rig);

	/**
	 * 更新权限
	 * @param 
	 * @return
	 */
	public int update(Rights rig);

	/**
	 * 根据id删除权限
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

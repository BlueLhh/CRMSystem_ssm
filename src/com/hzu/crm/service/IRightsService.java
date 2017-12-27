package com.hzu.crm.service;
/**
 * 权限表服务层接口
 * @author Administrator
 *
 */

import java.util.List;

import com.hzu.crm.entity.Rights;

public interface IRightsService {

	/**
	 * 根据id获取权限
	 * @param id
	 * @return
	 */
	public Rights findRigById(long id);
	
	/**
	 * 根据权限名称获取权限
	 * @param name
	 * @return
	 */
	public Rights findRigByName(String name);
	
	/**
	 * 查询所有权限
	 * @return
	 */
	public List<Rights> findAllRig();
	
	/**
	 * 查询权限总数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 根据分页查询权限
	 * @param min
	 * @param max
	 * @return
	 */
	public List<Rights> getPageRig(int min,int max);
	
	
	/**
	 * 新增权限
	 * @param rights
	 * @return
	 */
	public int addRights(Rights rights);
	
	/**
	 * 更新权限
	 * @param rights
	 * @return
	 */
	public int updateRights(Rights rights);
	
	/**
	 * 根据id删除权限
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

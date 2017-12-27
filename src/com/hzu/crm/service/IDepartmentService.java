package com.hzu.crm.service;

import java.util.List;

import com.hzu.crm.entity.Department;
/**
 * 部门服务层接口
 * @author Administrator
 *
 */
public interface IDepartmentService {

	/**
	 * 根据id查询部门
	 * @param id
	 * @return
	 */
	public Department findDeptById(long id);
	
	/**
	 * 更新部门
	 * @param dept
	 * @return
	 */
	public int updateDept(Department dept);
	
	/**
	 * 查询所有部门
	 * @return
	 */
	public List<Department> findAllDept();
}

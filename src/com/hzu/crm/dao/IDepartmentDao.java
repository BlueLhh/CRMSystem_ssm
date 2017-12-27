package com.hzu.crm.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.Department;

/**
 * 部门表Dao接口
 * @author Administrator
 *
 */
@MapperScan
public interface IDepartmentDao {
	/**
	 * 根据id查询部门
	 * @param id
	 * @return
	 */
	public Department findById(long id);

	/**
	 * 查询所有部门
	 * @return
	 */
	public List<Department> findAll();

	/**
	 * 添加部门
	 * @param dept
	 * @return
	 */
	public int insert(Department dept);

	/**
	 * 更新部门
	 * @param dept
	 * @return
	 */
	public int update(Department dept);

	/**
	 * 根据id删除部门
	 * @param id
	 * @return
	 */
	public int deleteById(long id);

}

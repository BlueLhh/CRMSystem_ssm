package com.hzu.crm.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.Employee;

/**
 * 员工表Dao接口
 * 
 * @author Administrator
 *
 */
@MapperScan
public interface IEmployeeDao {
	/**
	 * 根据id查询员工
	 * 
	 * @param id
	 * @return
	 */
	public Employee findById(long id);

	/**
	 * 根据用户名查询员工
	 * 
	 * @param username
	 * @return
	 */
	public Employee findByName(String username);

	/**
	 * 根据用户名和密码查询员工
	 * 
	 * @param username
	 * @param pass
	 * @return
	 */
	public Employee findByNameAndPWD(String username, String pass);

	/**
	 * 根据部门外键查询员工
	 * 
	 * @param deptId
	 * @return
	 */
	public List<Employee> findByDeptId(long deptId);

	/**
	 * 根据职位外键查询员工
	 * 
	 * @param jobId
	 * @return
	 */
	public List<Employee> findByJobId(long jobId);
	
	/**
	 * 根据职位外键查询员工id
	 * 
	 * @param jobId
	 * @return
	 */
	public List<Long> findEmpIdJobId(long jobId);

	/**
	 * 查询所有员工
	 * 
	 * @return
	 */
	public List<Employee> findAll();

	/**
	 * 查询所有员工总数
	 * 
	 * @return
	 */
	public int count(Map<String, Object> condition);

	/**
	 * 查询普通员工总数，即超级管理员和管理员之外的所有员工
	 * 
	 * @return
	 */
	public int comCount();

	/**
	 * 分页查询 condition中分别传入，职位id，当前页码，每页显示行数
	 * 
	 * @return
	 */
	public List<Employee> findByPage(Map<String, Object> condition);

	/**
	 * 分页查询普通员工
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Employee> findComByPage(int start, int end);

	/**
	 * 添加员工
	 * 
	 * @param
	 * @return
	 */
	public int insert(Employee emp);

	/**
	 * 更新员工
	 * 
	 * @param
	 * @return
	 */
	public int update(Employee emp);

	/**
	 * 根据id删除员工
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

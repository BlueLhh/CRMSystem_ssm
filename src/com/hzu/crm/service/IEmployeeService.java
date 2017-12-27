package com.hzu.crm.service;
/**
 * 员工服务层接口
 * @author Administrator
 *
 */

import java.util.List;
import java.util.Map;

import com.hzu.crm.entity.Employee;

public interface IEmployeeService {

	/**
	 * 根据id查询员工
	 * 
	 * @param id
	 * @return
	 */
	public Employee findEmpById(long id);

	/**
	 * 根据用户名查询员工
	 * 
	 * @param username
	 * @return
	 */
	public Employee findEmpByName(String username);

	/**
	 * 员工登录
	 * 
	 * @param username
	 * @param pass
	 * @return
	 */
	public Employee login(String username, String pass);

	/**
	 * 查询所有员工
	 * 
	 * @return
	 */
	public List<Employee> findAllEmp();

	/**
	 * 查询普通员工总数
	 * 
	 * @return
	 */
	public int getCount(Map<String, Object> condition);

	/**
	 * 查询普通员工人数
	 * 
	 * @return
	 */
	public int getComCount();

	/**
	 * 根据分页查询所有员工
	 * 
	 * @return
	 */
	public List<Employee> getPageEmp(Map<String, Object> condition);

	/**
	 * 根据职位id查询员工id
	 * @param id
	 * @return
	 */
	public List<Long> findEmpIdByJobId(long id);
	
	/**
	 * 根据分页查询普通员工
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Employee> getPageComEmp(int start, int end);

	/**
	 * 根据职位外键查询员工
	 * 
	 * @param jid
	 * @return
	 */
	public List<Employee> getEmpByJobId(long jid);

	/**
	 * 添加员工
	 * 
	 * @param emp
	 * @return
	 */
	public int addEmp(Employee emp);

	/**
	 * 更新员工
	 * 
	 * @param emp
	 * @return
	 */
	public int updateEmp(Employee emp);

	/**
	 * 删除员工
	 * 
	 * @param id
	 * @return
	 */
	public int delEmp(long id);
}

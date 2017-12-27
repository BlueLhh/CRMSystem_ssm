package com.hzu.crm.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.Customer;

/**
 * 客户表Dao接口
 * @author Administrator
 *
 */
@MapperScan
public interface ICustomerDao {
	/**
	 * 根据id查询客户
	 * @param id
	 * @return
	 */
	public Customer findById(long id);

	/**
	 * 查询所有客户
	 * @return
	 */
	public List<Customer> findAll();
	
	/**
	 * 分页查询客户
	 * @param start 开始行数
	 * @param end  结束行数
	 * @return
	 */
	public List<Customer> findByPage(int start,int end);
	
	/**
	 * 查询所有客户
	 * @return
	 */
	public int count();

	/**
	 * 添加客户
	 * @param 
	 * @return
	 */
	public int insert(Customer customer);

	/**
	 * 更新用户
	 * @param 
	 * @return
	 */
	public int update(Customer customer);

	/**
	 * 根据id删除用户
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

package com.hzu.crm.service;
/**
 * 客户服务层接口
 * @author Administrator
 *
 */

import java.util.List;

import com.hzu.crm.entity.Customer;

public interface ICustomerService {
	
	/**
	 * 根据id查询客户
	 * @param id
	 * @return
	 */
	public Customer findCusById(long id);
	
	/**
	 * 新增客户
	 * @param customer
	 * @return
	 */
	public int addCustomer(Customer customer);
	
	/**
	 * 更新客户
	 * @param customer
	 * @return
	 */
	public int updateCustomer(Customer customer);
	
	/**
	 * 获取客户总数
	 * @return
	 */
	public int getCount();

	/**
	 * 分页查询客户
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Customer> findCusByPage(int start, int end);
}

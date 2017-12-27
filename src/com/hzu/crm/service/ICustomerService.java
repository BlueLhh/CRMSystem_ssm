package com.hzu.crm.service;
/**
 * �ͻ������ӿ�
 * @author Administrator
 *
 */

import java.util.List;

import com.hzu.crm.entity.Customer;

public interface ICustomerService {
	
	/**
	 * ����id��ѯ�ͻ�
	 * @param id
	 * @return
	 */
	public Customer findCusById(long id);
	
	/**
	 * �����ͻ�
	 * @param customer
	 * @return
	 */
	public int addCustomer(Customer customer);
	
	/**
	 * ���¿ͻ�
	 * @param customer
	 * @return
	 */
	public int updateCustomer(Customer customer);
	
	/**
	 * ��ȡ�ͻ�����
	 * @return
	 */
	public int getCount();

	/**
	 * ��ҳ��ѯ�ͻ�
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Customer> findCusByPage(int start, int end);
}

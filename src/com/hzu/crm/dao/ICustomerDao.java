package com.hzu.crm.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.Customer;

/**
 * �ͻ���Dao�ӿ�
 * @author Administrator
 *
 */
@MapperScan
public interface ICustomerDao {
	/**
	 * ����id��ѯ�ͻ�
	 * @param id
	 * @return
	 */
	public Customer findById(long id);

	/**
	 * ��ѯ���пͻ�
	 * @return
	 */
	public List<Customer> findAll();
	
	/**
	 * ��ҳ��ѯ�ͻ�
	 * @param start ��ʼ����
	 * @param end  ��������
	 * @return
	 */
	public List<Customer> findByPage(int start,int end);
	
	/**
	 * ��ѯ���пͻ�
	 * @return
	 */
	public int count();

	/**
	 * ��ӿͻ�
	 * @param 
	 * @return
	 */
	public int insert(Customer customer);

	/**
	 * �����û�
	 * @param 
	 * @return
	 */
	public int update(Customer customer);

	/**
	 * ����idɾ���û�
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

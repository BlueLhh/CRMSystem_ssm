package com.hzu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzu.crm.dao.ICustomerDao;
import com.hzu.crm.entity.Customer;
import com.hzu.crm.service.ICustomerService;

/**
 * 客户服务层接口实现类
 * @author Administrator
 *
 */
@Service("cusService")
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private ICustomerDao cusDao;
	
	@Override
	public int getCount() {
		int count = cusDao.count();
		return count;
	}

	@Override
	public List<Customer> findCusByPage(int start, int end) {
		List<Customer> list = cusDao.findByPage(start, end);
		return list;
	}

	@Override
	public int addCustomer(Customer customer) {
		int row = cusDao.insert(customer);
		return row;
	}

	@Override
	public Customer findCusById(long id) {
		Customer customer = cusDao.findById(id);
		return customer;
	}

	@Override
	public int updateCustomer(Customer customer) {
		int row = cusDao.update(customer);
		return row;
	}
}

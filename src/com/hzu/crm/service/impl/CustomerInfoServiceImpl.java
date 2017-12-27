package com.hzu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzu.crm.dao.ICustomerInfoDao;
import com.hzu.crm.entity.CustomerInfo;
import com.hzu.crm.service.ICustomerInfoService;

/**
 * 跟踪单服务层接口实现类
 * 
 * @author Administrator
 *
 */
@Service("cusInfoService")
public class CustomerInfoServiceImpl implements ICustomerInfoService {

	@Autowired
	private ICustomerInfoDao cusInfoDao;

	@Override
	public int addCusInfo(CustomerInfo customerInfo) {
		int row = cusInfoDao.insert(customerInfo);
		return row;
	}

	@Override
	public int getCountByEmpId(Map<String, Object> condition) {
		int count = cusInfoDao.getCount(condition);
		return count;
	}

	@Override
	public List<CustomerInfo> findInfoByPage(Map<String, Object> condition) {
		List<CustomerInfo> list = cusInfoDao.findByPage(condition);
		return list;
	}

	@Override
	public int updateCusInfo(CustomerInfo customerInfo) {
		int row = cusInfoDao.update(customerInfo);
		return row;
	}

	@Override
	public int getStatuCount(Map<String, Object> condition) {
		int count = cusInfoDao.getStatuCount(condition);
		return count;
	}

	@Override
	public List<CustomerInfo> findAllStatu(Map<String, Object> condition) {
		List<CustomerInfo> list = cusInfoDao.findAllStatu(condition);
		return list;
	}

}

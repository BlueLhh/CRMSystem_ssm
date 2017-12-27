package com.hzu.crm.service;

import java.util.List;
import java.util.Map;

import com.hzu.crm.entity.CustomerInfo;

/**
 * 跟踪单服务层接口
 * 
 * @author Administrator
 *
 */
public interface ICustomerInfoService {

	/**
	 * 新增跟踪单
	 * 
	 * @param customerInfo
	 * @return
	 */
	public int addCusInfo(CustomerInfo customerInfo);

	/**
	 * 更新跟踪单
	 * 
	 * @param customerInfo
	 * @return
	 */
	public int updateCusInfo(CustomerInfo customerInfo);

	/**
	 * 根据员工外键查询其拥有跟踪单总数
	 * 
	 * @param empId
	 * @return
	 */
	public int getCountByEmpId(Map<String, Object> condition);

	/**
	 * 根据员工id分页查询
	 * 
	 * @return
	 */
	public List<CustomerInfo> findInfoByPage(Map<String, Object> condition);
	
	/**
	 * 跟据员工id，及记录状态查询相应的记录数，
	 * @param condition
	 * @return
	 */
	public int getStatuCount(Map<String, Object> condition);
	
	/**
	 * 查询所有订单数
	 * @return
	 */
	public List<CustomerInfo> findAllStatu(Map<String, Object> condition);
}

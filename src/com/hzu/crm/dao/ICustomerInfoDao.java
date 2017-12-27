package com.hzu.crm.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.CustomerInfo;

/**
 * 销售客户跟踪信息表Dao接口
 * 
 * @author Administrator
 *
 */
@MapperScan
public interface ICustomerInfoDao {
	/**
	 * 根据id查询跟踪单
	 * 
	 * @param id
	 * @return
	 */
	public CustomerInfo findById(long id);

	/**
	 * 根据客户外键查询跟踪单
	 * 
	 * @param cid
	 * @return
	 */
	public List<CustomerInfo> findByCusId(long cid);

	/**
	 * 查询所有员工的订单数
	 * @return
	 */
	public List<CustomerInfo> findAllStatu(Map<String, Object> condition);
	
	/**
	 * 根据员工外键查询跟踪单
	 * 
	 * @param cid
	 * @return
	 */
	public List<CustomerInfo> findByEmpId(long eid);

	/**
	 * 根据员工id获取记录总数
	 * 
	 * @param Map中一般就传入empId
	 * @return
	 */
	public int getCount(Map<String, Object> condition);

	/**
	 * 分页查询
	 * 
	 * @param Map 传入empId,start开始页码,end结束页码
	 * @return
	 */
	public List<CustomerInfo> findByPage(Map<String, Object> condition);

	/**
	 * 根据不同的员工id，及订单不同的状态，查询相应的记录
	 * @param condition
	 * @return
	 */
	public int getStatuCount(Map<String, Object> condition);
	
	/**
	 * 查询所有跟踪单
	 * 
	 * @return
	 */
	public List<CustomerInfo> findAll();

	/**
	 * 添加跟踪单
	 * 
	 * @param
	 * @return
	 */
	public int insert(CustomerInfo cusInfo);

	/**
	 * 更新跟踪单
	 * 
	 * @param
	 * @return
	 */
	public int update(CustomerInfo cusInfo);

	/**
	 * 根据id删除跟踪单
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

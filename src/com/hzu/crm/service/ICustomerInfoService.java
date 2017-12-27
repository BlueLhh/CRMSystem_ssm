package com.hzu.crm.service;

import java.util.List;
import java.util.Map;

import com.hzu.crm.entity.CustomerInfo;

/**
 * ���ٵ������ӿ�
 * 
 * @author Administrator
 *
 */
public interface ICustomerInfoService {

	/**
	 * �������ٵ�
	 * 
	 * @param customerInfo
	 * @return
	 */
	public int addCusInfo(CustomerInfo customerInfo);

	/**
	 * ���¸��ٵ�
	 * 
	 * @param customerInfo
	 * @return
	 */
	public int updateCusInfo(CustomerInfo customerInfo);

	/**
	 * ����Ա�������ѯ��ӵ�и��ٵ�����
	 * 
	 * @param empId
	 * @return
	 */
	public int getCountByEmpId(Map<String, Object> condition);

	/**
	 * ����Ա��id��ҳ��ѯ
	 * 
	 * @return
	 */
	public List<CustomerInfo> findInfoByPage(Map<String, Object> condition);
	
	/**
	 * ����Ա��id������¼״̬��ѯ��Ӧ�ļ�¼����
	 * @param condition
	 * @return
	 */
	public int getStatuCount(Map<String, Object> condition);
	
	/**
	 * ��ѯ���ж�����
	 * @return
	 */
	public List<CustomerInfo> findAllStatu(Map<String, Object> condition);
}

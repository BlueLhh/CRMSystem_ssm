package com.hzu.crm.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.CustomerInfo;

/**
 * ���ۿͻ�������Ϣ��Dao�ӿ�
 * 
 * @author Administrator
 *
 */
@MapperScan
public interface ICustomerInfoDao {
	/**
	 * ����id��ѯ���ٵ�
	 * 
	 * @param id
	 * @return
	 */
	public CustomerInfo findById(long id);

	/**
	 * ���ݿͻ������ѯ���ٵ�
	 * 
	 * @param cid
	 * @return
	 */
	public List<CustomerInfo> findByCusId(long cid);

	/**
	 * ��ѯ����Ա���Ķ�����
	 * @return
	 */
	public List<CustomerInfo> findAllStatu(Map<String, Object> condition);
	
	/**
	 * ����Ա�������ѯ���ٵ�
	 * 
	 * @param cid
	 * @return
	 */
	public List<CustomerInfo> findByEmpId(long eid);

	/**
	 * ����Ա��id��ȡ��¼����
	 * 
	 * @param Map��һ��ʹ���empId
	 * @return
	 */
	public int getCount(Map<String, Object> condition);

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param Map ����empId,start��ʼҳ��,end����ҳ��
	 * @return
	 */
	public List<CustomerInfo> findByPage(Map<String, Object> condition);

	/**
	 * ���ݲ�ͬ��Ա��id����������ͬ��״̬����ѯ��Ӧ�ļ�¼
	 * @param condition
	 * @return
	 */
	public int getStatuCount(Map<String, Object> condition);
	
	/**
	 * ��ѯ���и��ٵ�
	 * 
	 * @return
	 */
	public List<CustomerInfo> findAll();

	/**
	 * ��Ӹ��ٵ�
	 * 
	 * @param
	 * @return
	 */
	public int insert(CustomerInfo cusInfo);

	/**
	 * ���¸��ٵ�
	 * 
	 * @param
	 * @return
	 */
	public int update(CustomerInfo cusInfo);

	/**
	 * ����idɾ�����ٵ�
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

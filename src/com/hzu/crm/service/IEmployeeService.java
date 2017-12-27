package com.hzu.crm.service;
/**
 * Ա�������ӿ�
 * @author Administrator
 *
 */

import java.util.List;
import java.util.Map;

import com.hzu.crm.entity.Employee;

public interface IEmployeeService {

	/**
	 * ����id��ѯԱ��
	 * 
	 * @param id
	 * @return
	 */
	public Employee findEmpById(long id);

	/**
	 * �����û�����ѯԱ��
	 * 
	 * @param username
	 * @return
	 */
	public Employee findEmpByName(String username);

	/**
	 * Ա����¼
	 * 
	 * @param username
	 * @param pass
	 * @return
	 */
	public Employee login(String username, String pass);

	/**
	 * ��ѯ����Ա��
	 * 
	 * @return
	 */
	public List<Employee> findAllEmp();

	/**
	 * ��ѯ��ͨԱ������
	 * 
	 * @return
	 */
	public int getCount(Map<String, Object> condition);

	/**
	 * ��ѯ��ͨԱ������
	 * 
	 * @return
	 */
	public int getComCount();

	/**
	 * ���ݷ�ҳ��ѯ����Ա��
	 * 
	 * @return
	 */
	public List<Employee> getPageEmp(Map<String, Object> condition);

	/**
	 * ����ְλid��ѯԱ��id
	 * @param id
	 * @return
	 */
	public List<Long> findEmpIdByJobId(long id);
	
	/**
	 * ���ݷ�ҳ��ѯ��ͨԱ��
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Employee> getPageComEmp(int start, int end);

	/**
	 * ����ְλ�����ѯԱ��
	 * 
	 * @param jid
	 * @return
	 */
	public List<Employee> getEmpByJobId(long jid);

	/**
	 * ���Ա��
	 * 
	 * @param emp
	 * @return
	 */
	public int addEmp(Employee emp);

	/**
	 * ����Ա��
	 * 
	 * @param emp
	 * @return
	 */
	public int updateEmp(Employee emp);

	/**
	 * ɾ��Ա��
	 * 
	 * @param id
	 * @return
	 */
	public int delEmp(long id);
}

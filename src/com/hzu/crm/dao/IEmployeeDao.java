package com.hzu.crm.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.Employee;

/**
 * Ա����Dao�ӿ�
 * 
 * @author Administrator
 *
 */
@MapperScan
public interface IEmployeeDao {
	/**
	 * ����id��ѯԱ��
	 * 
	 * @param id
	 * @return
	 */
	public Employee findById(long id);

	/**
	 * �����û�����ѯԱ��
	 * 
	 * @param username
	 * @return
	 */
	public Employee findByName(String username);

	/**
	 * �����û����������ѯԱ��
	 * 
	 * @param username
	 * @param pass
	 * @return
	 */
	public Employee findByNameAndPWD(String username, String pass);

	/**
	 * ���ݲ��������ѯԱ��
	 * 
	 * @param deptId
	 * @return
	 */
	public List<Employee> findByDeptId(long deptId);

	/**
	 * ����ְλ�����ѯԱ��
	 * 
	 * @param jobId
	 * @return
	 */
	public List<Employee> findByJobId(long jobId);
	
	/**
	 * ����ְλ�����ѯԱ��id
	 * 
	 * @param jobId
	 * @return
	 */
	public List<Long> findEmpIdJobId(long jobId);

	/**
	 * ��ѯ����Ա��
	 * 
	 * @return
	 */
	public List<Employee> findAll();

	/**
	 * ��ѯ����Ա������
	 * 
	 * @return
	 */
	public int count(Map<String, Object> condition);

	/**
	 * ��ѯ��ͨԱ������������������Ա�͹���Ա֮�������Ա��
	 * 
	 * @return
	 */
	public int comCount();

	/**
	 * ��ҳ��ѯ condition�зֱ��룬ְλid����ǰҳ�룬ÿҳ��ʾ����
	 * 
	 * @return
	 */
	public List<Employee> findByPage(Map<String, Object> condition);

	/**
	 * ��ҳ��ѯ��ͨԱ��
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Employee> findComByPage(int start, int end);

	/**
	 * ���Ա��
	 * 
	 * @param
	 * @return
	 */
	public int insert(Employee emp);

	/**
	 * ����Ա��
	 * 
	 * @param
	 * @return
	 */
	public int update(Employee emp);

	/**
	 * ����idɾ��Ա��
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

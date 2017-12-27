package com.hzu.crm.service;

import java.util.List;

import com.hzu.crm.entity.Department;
/**
 * ���ŷ����ӿ�
 * @author Administrator
 *
 */
public interface IDepartmentService {

	/**
	 * ����id��ѯ����
	 * @param id
	 * @return
	 */
	public Department findDeptById(long id);
	
	/**
	 * ���²���
	 * @param dept
	 * @return
	 */
	public int updateDept(Department dept);
	
	/**
	 * ��ѯ���в���
	 * @return
	 */
	public List<Department> findAllDept();
}

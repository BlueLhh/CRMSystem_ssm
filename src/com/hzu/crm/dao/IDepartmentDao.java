package com.hzu.crm.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.Department;

/**
 * ���ű�Dao�ӿ�
 * @author Administrator
 *
 */
@MapperScan
public interface IDepartmentDao {
	/**
	 * ����id��ѯ����
	 * @param id
	 * @return
	 */
	public Department findById(long id);

	/**
	 * ��ѯ���в���
	 * @return
	 */
	public List<Department> findAll();

	/**
	 * ��Ӳ���
	 * @param dept
	 * @return
	 */
	public int insert(Department dept);

	/**
	 * ���²���
	 * @param dept
	 * @return
	 */
	public int update(Department dept);

	/**
	 * ����idɾ������
	 * @param id
	 * @return
	 */
	public int deleteById(long id);

}

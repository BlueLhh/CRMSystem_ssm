package com.hzu.crm.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.Rights;

/**
 * Ȩ�ޱ�Dao�ӿ�
 * @author Administrator
 *
 */
@MapperScan
public interface IRightsDao {
	/**
	 * ����id��ѯȨ��
	 * @param id
	 * @return
	 */
	public Rights findById(long id);

	/**
	 * ����Ȩ�����Ʋ�ѯȨ��
	 * @param rightName
	 * @return
	 */
	public Rights findByName(String rightName);
	
	/**
	 * ����Ȩ�ޱ������ѯȨ�ޣ�������
	 * @param deptId
	 * @return
	 */
	public List<Rights> findByRigId(long pid);

	/**
	 * ��ѯ����Ȩ��
	 * @return
	 */
	public List<Rights> findAll();

	/**
	 * ��ѯȨ������
	 * @return
	 */
	public int count();
	
	/**
	 * ��ҳ��ѯ
	 * @param page ��ǰҳ��
	 * @param pageSize ҳ����ʾ
	 * @return
	 */
	public List<Rights> findByPage(int min,int max);
	
	/**
	 * ���Ȩ��	
	 * @param 
	 * @return
	 */
	public int insert(Rights rig);

	/**
	 * ����Ȩ��
	 * @param 
	 * @return
	 */
	public int update(Rights rig);

	/**
	 * ����idɾ��Ȩ��
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

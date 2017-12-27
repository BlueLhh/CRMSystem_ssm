package com.hzu.crm.service;
/**
 * Ȩ�ޱ�����ӿ�
 * @author Administrator
 *
 */

import java.util.List;

import com.hzu.crm.entity.Rights;

public interface IRightsService {

	/**
	 * ����id��ȡȨ��
	 * @param id
	 * @return
	 */
	public Rights findRigById(long id);
	
	/**
	 * ����Ȩ�����ƻ�ȡȨ��
	 * @param name
	 * @return
	 */
	public Rights findRigByName(String name);
	
	/**
	 * ��ѯ����Ȩ��
	 * @return
	 */
	public List<Rights> findAllRig();
	
	/**
	 * ��ѯȨ������
	 * @return
	 */
	public int getCount();
	
	/**
	 * ���ݷ�ҳ��ѯȨ��
	 * @param min
	 * @param max
	 * @return
	 */
	public List<Rights> getPageRig(int min,int max);
	
	
	/**
	 * ����Ȩ��
	 * @param rights
	 * @return
	 */
	public int addRights(Rights rights);
	
	/**
	 * ����Ȩ��
	 * @param rights
	 * @return
	 */
	public int updateRights(Rights rights);
	
	/**
	 * ����idɾ��Ȩ��
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

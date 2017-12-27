package com.hzu.crm.service;

import java.util.List;
import java.util.Map;

import com.hzu.crm.entity.ConsultRecord;

/**
 * ��ѯ�������ӿ�
 * @author Administrator
 *
 */
public interface IConsultRecordService {
	
	/**
	 * ������¼
	 * @param record
	 * @return
	 */
	public int addConsultRecord(ConsultRecord record);
	
	/**
	 * ���¼�¼
	 * @param record
	 * @return
	 */
	public int updateConsultRecord(ConsultRecord record);
	
	/**
	 * ����conditions��������ȡ��ѯ��������
	 * @param map
	 * @return
	 */
	public int getConRecCount(Map<String, Object> conditions);
	
	/**
	 * ����conditions��������ȡ��ѯ��
	 * @param conditions
	 * @return
	 */
	public List<ConsultRecord> findConRecByPage(Map<String, Object> conditions);
	
	/**
	 * ����Ա��id������¼״̬��ѯ��Ӧ�ļ�¼����
	 * @param condition
	 * @return
	 */
	public int getStatuCount(Map<String, Object> condition);
	
	/**
	 * ��ѯÿ��Ա�������ж�����
	 * @return
	 */
	public List<ConsultRecord> findAllStatu(Map<String, Object> condition);
}

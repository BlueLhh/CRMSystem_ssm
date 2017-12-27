package com.hzu.crm.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.ConsultRecord;

/**
 * ��ѯ��Ϣ��Dao�ӿ�
 * @author Administrator
 *
 */
@MapperScan
public interface IConsultRecordDao {
	/**
	 * ����id��ѯ��ѯ��
	 * @param id
	 * @return
	 */
	public ConsultRecord findById(long id);

	/**
	 * ���ݿͻ������ѯ��ѯ��
	 * @param cid
	 * @return
	 */
	public List<ConsultRecord> findByCusId(long cid);

	/**
	 * ����Ա�������ѯ��ѯ��
	 * @param eid
	 * @return
	 */
	public List<ConsultRecord> findByEmpId(long eid);

	/**
	 * ��ѯ������ѯ��
	 * @return
	 */
	public List<ConsultRecord> findAll();

	/**
	 * ��ѯ����Ա���Ķ�����
	 * @return
	 */
	public List<ConsultRecord> findAllStatu(Map<String, Object> condition);

	/**
	 * ����Ա��id��ȡ��ѯ������
	 * 
	 * @param Map��һ��ʹ���empId
	 * @return
	 */
	public int getCount(Map<String, Object> condition);

	/**
	 * ���ݲ�ͬ��Ա��id����������ͬ��״̬����ѯ��Ӧ�ļ�¼
	 * @param condition
	 * @return
	 */
	public int getStatuCount(Map<String, Object> condition);

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param Map ����empId,start��ʼҳ��,end����ҳ��
	 * @return
	 */
	public List<ConsultRecord> findByPage(Map<String, Object> condition);

	/**
	 * �����ѯ��
	 * @param 
	 * @return
	 */
	public int insert(ConsultRecord conRec);

	/**
	 * ������ѯ��
	 * @param 
	 * @return
	 */
	public int update(ConsultRecord conRec);

	/**
	 * ����idɾ����ѯ��
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

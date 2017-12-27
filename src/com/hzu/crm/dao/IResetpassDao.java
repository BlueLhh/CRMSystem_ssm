package com.hzu.crm.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.Resetpass;

/**
 * ���������¼��Dao�ӿ�
 * @author Administrator
 *
 */
@MapperScan
public interface IResetpassDao {
	/**
	 * ����id��ѯ��¼
	 * @param id
	 * @return
	 */
	public Resetpass findById(long id);

	/**
	 * ��ѯ���м�¼
	 * @return
	 */
	public List<Resetpass> findAll();

	/**
	 * д���¼
	 * @param 
	 * @return
	 */
	public int insert(Resetpass resetpass);

	/**
	 * ���¼�¼
	 * @param 
	 * @return
	 */
	public int update(Resetpass resetpass);

	/**
	 * ����idɾ����¼
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

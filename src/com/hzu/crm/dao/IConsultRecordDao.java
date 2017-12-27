package com.hzu.crm.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.hzu.crm.entity.ConsultRecord;

/**
 * 咨询信息表Dao接口
 * @author Administrator
 *
 */
@MapperScan
public interface IConsultRecordDao {
	/**
	 * 根据id查询咨询单
	 * @param id
	 * @return
	 */
	public ConsultRecord findById(long id);

	/**
	 * 根据客户外键查询咨询单
	 * @param cid
	 * @return
	 */
	public List<ConsultRecord> findByCusId(long cid);

	/**
	 * 根据员工外键查询咨询单
	 * @param eid
	 * @return
	 */
	public List<ConsultRecord> findByEmpId(long eid);

	/**
	 * 查询所有咨询单
	 * @return
	 */
	public List<ConsultRecord> findAll();

	/**
	 * 查询所有员工的订单数
	 * @return
	 */
	public List<ConsultRecord> findAllStatu(Map<String, Object> condition);

	/**
	 * 根据员工id获取咨询单总数
	 * 
	 * @param Map中一般就传入empId
	 * @return
	 */
	public int getCount(Map<String, Object> condition);

	/**
	 * 根据不同的员工id，及订单不同的状态，查询相应的记录
	 * @param condition
	 * @return
	 */
	public int getStatuCount(Map<String, Object> condition);

	/**
	 * 分页查询
	 * 
	 * @param Map 传入empId,start开始页码,end结束页码
	 * @return
	 */
	public List<ConsultRecord> findByPage(Map<String, Object> condition);

	/**
	 * 添加咨询单
	 * @param 
	 * @return
	 */
	public int insert(ConsultRecord conRec);

	/**
	 * 更新咨询单
	 * @param 
	 * @return
	 */
	public int update(ConsultRecord conRec);

	/**
	 * 根据id删除咨询单
	 * @param id
	 * @return
	 */
	public int deleteById(long id);
}

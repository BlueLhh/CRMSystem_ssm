package com.hzu.crm.service;

import java.util.List;
import java.util.Map;

import com.hzu.crm.entity.ConsultRecord;

/**
 * 咨询单服务层接口
 * @author Administrator
 *
 */
public interface IConsultRecordService {
	
	/**
	 * 新增记录
	 * @param record
	 * @return
	 */
	public int addConsultRecord(ConsultRecord record);
	
	/**
	 * 更新记录
	 * @param record
	 * @return
	 */
	public int updateConsultRecord(ConsultRecord record);
	
	/**
	 * 根据conditions条件，获取咨询单总条数
	 * @param map
	 * @return
	 */
	public int getConRecCount(Map<String, Object> conditions);
	
	/**
	 * 根据conditions条件，获取咨询单
	 * @param conditions
	 * @return
	 */
	public List<ConsultRecord> findConRecByPage(Map<String, Object> conditions);
	
	/**
	 * 跟据员工id，及记录状态查询相应的记录数，
	 * @param condition
	 * @return
	 */
	public int getStatuCount(Map<String, Object> condition);
	
	/**
	 * 查询每名员工的所有订单数
	 * @return
	 */
	public List<ConsultRecord> findAllStatu(Map<String, Object> condition);
}

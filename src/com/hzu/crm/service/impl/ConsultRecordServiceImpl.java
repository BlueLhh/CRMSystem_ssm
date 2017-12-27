package com.hzu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzu.crm.dao.IConsultRecordDao;
import com.hzu.crm.entity.ConsultRecord;
import com.hzu.crm.service.IConsultRecordService;

/**
 * 咨询单服务层接口实现类
 * @author Administrator
 *
 */
@Service("recordService")
public class ConsultRecordServiceImpl implements IConsultRecordService {

	@Autowired
	private IConsultRecordDao recordDao;

	@Override
	public int addConsultRecord(ConsultRecord record) {
		int row = recordDao.insert(record);
		return row;
	}

	@Override
	public int getConRecCount(Map<String, Object> conditions) {
		int count = recordDao.getCount(conditions);
		return count;
	}

	@Override
	public List<ConsultRecord> findConRecByPage(Map<String, Object> conditions) {
		List<ConsultRecord> list = recordDao.findByPage(conditions);
		return list;
	}

	@Override
	public int updateConsultRecord(ConsultRecord record) {
		int row = recordDao.update(record);
		return row;
	}

	@Override
	public int getStatuCount(Map<String, Object> condition) {
		int count = recordDao.getStatuCount(condition);
		return count;
	}

	@Override
	public List<ConsultRecord> findAllStatu(Map<String, Object> condition) {
		List<ConsultRecord> list = recordDao.findAllStatu(condition);
		return list;
	}
}

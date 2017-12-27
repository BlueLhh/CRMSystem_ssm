package com.hzu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzu.crm.dao.IJobRightDao;
import com.hzu.crm.entity.JobRight;
import com.hzu.crm.service.IJobRightService;
/**
 * 权限对照表接口实现类
 * @author Administrator
 *
 */
@Service("jobRigService")
public class JobRightServiceImpl implements IJobRightService{

	@Autowired
	private IJobRightDao jobRigDao;
	
	@Override
	public List<JobRight> findAllJobRig() {
		List<JobRight> jrList = jobRigDao.findAll();
		return jrList;
	}

	@Override
	public int addJobRight(JobRight jobRight) {
		int row = jobRigDao.insert(jobRight);
		return row;
	}

	@Override
	public int delJobRigByID(long id) {
		int row = jobRigDao.deleteById(id);
		return row;
	}

	@Override
	public int delJobRigByFK(long rid, long jid) {
		int row = jobRigDao.deleteByFK(rid, jid);
		return row;
	}

}

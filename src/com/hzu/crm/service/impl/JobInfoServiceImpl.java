package com.hzu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzu.crm.dao.IJobInfoDao;
import com.hzu.crm.entity.JobInfo;
import com.hzu.crm.service.IJobInfoService;

/**
 * 职位服务层接口实现类
 * @author Administrator
 *
 */
@Service("jobService")
public class JobInfoServiceImpl implements IJobInfoService {

	@Autowired
	private IJobInfoDao jobDao;

	@Override
	public int addJob(JobInfo job) {
		int rows = jobDao.insert(job);
		return rows;
	}

	@Override
	public List<JobInfo> findAllJob() {
		List<JobInfo> jobList = jobDao.findAll();
		return jobList;
	}

	@Override
	public JobInfo findJobById(long id) {
		JobInfo jobInfo = jobDao.findById(id);
		return jobInfo;
	}

	@Override
	public int updateJob(JobInfo job) {
		int row = jobDao.update(job);
		return row;
	}

	@Override
	public List<JobInfo> findJobNameByDeptId(long deptId) {
		List<JobInfo> list = jobDao.findJobNameByDeptId(deptId);
		return list;
	}

	@Override
	public JobInfo findJobByName(String jobName) {
		JobInfo jobInfo = jobDao.findByJob(jobName);
		return jobInfo;
	}
}

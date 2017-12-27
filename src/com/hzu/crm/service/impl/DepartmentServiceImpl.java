package com.hzu.crm.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzu.crm.dao.IDepartmentDao;
import com.hzu.crm.entity.Department;
import com.hzu.crm.service.IDepartmentService;
/**
 * 部门服务层接口实现类
 * @author Administrator
 *
 */
@Service("deptService")
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private IDepartmentDao deptDao;

	@Override
	public Department findDeptById(long id) {
		Department dept = deptDao.findById(id);
		return dept;
	}
	
	@Override
	public List<Department> findAllDept() {
		List<Department> list = deptDao.findAll();
		return list;
	}

	@Override
	public int updateDept(Department dept) {
		int row = deptDao.update(dept);
		return row;
	}

	
}

package com.hzu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzu.crm.dao.IEmployeeDao;
import com.hzu.crm.entity.Employee;
import com.hzu.crm.service.IEmployeeService;

/**
 * 员工服务层接口的实现类
 * 
 * @author Administrator
 *
 */
@Service("empService")
public class EmployeeServiceImpl implements IEmployeeService {

	// 注入员工Dao接口
	@Autowired
	private IEmployeeDao empDao;

	@Override
	public Employee login(String username, String pass) {
		Employee employee = empDao.findByNameAndPWD(username, pass);
		return employee;
	}

	@Override
	public int addEmp(Employee emp) {
		int row = empDao.insert(emp);
		return row;
	}

	@Override
	public List<Employee> findAllEmp() {
		List<Employee> empList = empDao.findAll();
		return empList;
	}

	@Override
	public int getCount(Map<String, Object> condition) {
		int count = empDao.count(condition);
		return count;
	}

	@Override
	public List<Employee> getPageEmp(Map<String, Object> condition) {
		List<Employee> list = empDao.findByPage(condition);
		return list;
	}

	@Override
	public int delEmp(long id) {
		int row = empDao.deleteById(id);
		return row;
	}

	@Override
	public Employee findEmpById(long id) {
		Employee emp = empDao.findById(id);
		return emp;
	}

	@Override
	public List<Employee> getPageComEmp(int start, int end) {
		List<Employee> list = empDao.findComByPage(start, end);
		return list;
	}

	@Override
	public int getComCount() {
		int comCount = empDao.comCount();
		return comCount;
	}

	@Override
	public int updateEmp(Employee emp) {
		int row = empDao.update(emp);
		return row;
	}

	@Override
	public List<Employee> getEmpByJobId(long jid) {
		List<Employee> list = empDao.findByJobId(jid);
		return list;
	}

	@Override
	public Employee findEmpByName(String username) {
		Employee employee = empDao.findByName(username);
		return employee;
	}

	@Override
	public List<Long> findEmpIdByJobId(long id) {
		List<Long> list = empDao.findEmpIdJobId(id);
		return list;
	}
}

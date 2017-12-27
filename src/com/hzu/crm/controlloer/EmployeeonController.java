package com.hzu.crm.controlloer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hzu.crm.entity.Department;
import com.hzu.crm.entity.Employee;
import com.hzu.crm.entity.JobInfo;
import com.hzu.crm.service.IDepartmentService;
import com.hzu.crm.service.IEmployeeService;
import com.hzu.crm.service.IJobInfoService;

/**
 * 员工控制层
 * 
 * @author Administrator
 *
 */
@Controller()
@RequestMapping("/emp")
public class EmployeeonController {

	@Autowired
	private IEmployeeService empService;
	@Autowired
	private IDepartmentService deptService;
	@Autowired
	private IJobInfoService jobService;

	/**
	 * 员工登录
	 * 
	 * @param userNum
	 * @param userPw
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String userNum, String userPw, HttpServletRequest request, HttpServletResponse response) {
		// 查询登录用户是否存在
		Employee employee = empService.login(userNum, userPw);
		if (employee != null) {
			HttpSession session = request.getSession();
			// 把员工保存到session对象
			session.setAttribute("employee", employee);
			// 传递员工职位信息
			session.setAttribute("job", employee.getJobInfo().getJob().trim());
			// 获取所有部门
			List<Department> deptList = deptService.findAllDept();
			// 传递部门信息
			session.setAttribute("deptList", deptList);
			// 获取所有职位
			List<JobInfo> jobList = jobService.findAllJob();
			session.setAttribute("jobList", jobList);
			return "/view/frame/main.jsp";
		} else {
			try {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.flush();
				pw.write("<script>javascript:alert('用户名或者密码错误！');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "/login.jsp";
		}
	}

	/**
	 * 员工退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "/login.jsp";
	}

	/**
	 * 根据id查询员工
	 * @param request
	 * @param response
	 */
	@RequestMapping("/findEmp")
	@ResponseBody
	public Employee getEmpById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Employee emp = empService.findEmpById(Long.valueOf(id));
		// 新建一个员工对象保存必要信息，和之前一样直接传查出来的对象会报异常
		Employee employee = new Employee();
		employee.setNickname(emp.getNickname());
		employee.getJobInfo().setJob(emp.getJobInfo().getJob());
		employee.getDept().setDname(emp.getDept().getDname());
		employee.setPhoneNo(emp.getPhoneNo());
		employee.setOfficeTel(emp.getOfficeTel());
		return employee;
	}

	/**
	 * 查询所有员工信息，并分页
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/empInfo")
	public void findAllEmp(HttpServletRequest request, HttpServletResponse response) {
		// 获取职位id
		String jobId = request.getParameter("jobId");
		// 获取页码
		Integer page = Integer.valueOf(request.getParameter("page"));
		// 每页需要显示的行数
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		// 获取当前页需要显示的员工
		// 创建map，将初始和结束显示的行数，职位id传入
		Map<String, Object> condition = new HashMap<String, Object>();
		if (jobId != null && !"".equals(jobId)) {
			condition.put("jobId", jobId);
		} else {
			condition.put("jobId", 0);
		}
		condition.put("start", (page - 1) * rows + 1);
		condition.put("end", rows * page);
		List<Employee> empList = empService.getPageEmp(condition);

		// 获取员工总数
		int count = empService.getCount(condition);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		// 当前页数据
		map.put("rows", empList);

		String empJSON = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
		try {
			response.getWriter().write(empJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询普通员工信息，并分页
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/comEmpInfo")
	public void findAllComEmp(HttpServletRequest request, HttpServletResponse response) {
		// 获取页码
		Integer page = Integer.valueOf(request.getParameter("page"));
		// 每页需要显示的行数
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		// 获取员工总数
		int count = empService.getComCount();
		// 获取当前页需要显示的员工
		List<Employee> empList = empService.getPageComEmp((page - 1) * rows + 1, rows * page);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		// 当前页数据
		map.put("rows", empList);

		String empJSON = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
		try {
			response.getWriter().write(empJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加管理员，或其他员工
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addEmp")
	public String addAdmin(String deptId, Employee emp, HttpServletRequest request) {
		boolean flag = true;
		// 获取职位名称
		String jobName = request.getParameter("jobSelect");
		JobInfo jobInfo;
		Department dept;
		if (deptId != null && !"".equals(deptId)) {
			flag = false;
			// 根据职位名称，查询职位信息
			jobInfo = jobService.findJobByName(jobName);
			// 查询部门信息
			dept = deptService.findDeptById(Long.valueOf(deptId));
		} else {
			// 查询职位信息，管理员职位id为2
			jobInfo = jobService.findJobById(2);
			// 查询部门信息，管理员为技术部门id为1
			dept = deptService.findDeptById(1);
		}

		// 把管理员和职位，部门之间对象进行关联
		// 先设置在职状态
		emp.setWorkstatu("1");
		emp.setDept(dept);
		emp.setJobInfo(jobInfo);
		// 保存
		empService.addEmp(emp);

		// 部门和职位关联管理员
		jobInfo.getEmpList().add(emp);
		dept.getEmpList().add(emp);
		// 更新
		jobService.updateJob(jobInfo);
		deptService.updateDept(dept);

		if (!flag) {
			return "/view/frame/comEmp_info.jsp";
		} else {
			return "/view/frame/emp_info.jsp";
		}
	}

	/**
	 * 删除员工
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delEmp")
	public String delAdmin(HttpServletRequest request) {
		// 获取员工id
		String id = request.getParameter("id");
		Employee emp = empService.findEmpById(Long.valueOf(id));
		// 删除 TODO 在员工有关联跟踪单表，咨询表后，还需要删除表记录
		empService.delEmp(Long.valueOf(id));
		if (emp.getJobInfo().getId() == 2) {
			return "/view/frame/ordadmin_del.jsp";
		} else {
			return "/view/frame/comEmp_info.jsp";
		}
	}

	/**
	 * 重置员工密码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/rePass")
	public String rePass(HttpServletRequest request) {
		// 获取员工id
		String id = request.getParameter("id");
		// 查询员工
		Employee emp = empService.findEmpById(Long.valueOf(id));
		// 初始化密码为123
		emp.setPass("123");
		// 更新
		empService.updateEmp(emp);
		return "/view/frame/comEmp_info.jsp";
	}
}

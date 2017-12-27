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
 * Ա�����Ʋ�
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
	 * Ա����¼
	 * 
	 * @param userNum
	 * @param userPw
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String userNum, String userPw, HttpServletRequest request, HttpServletResponse response) {
		// ��ѯ��¼�û��Ƿ����
		Employee employee = empService.login(userNum, userPw);
		if (employee != null) {
			HttpSession session = request.getSession();
			// ��Ա�����浽session����
			session.setAttribute("employee", employee);
			// ����Ա��ְλ��Ϣ
			session.setAttribute("job", employee.getJobInfo().getJob().trim());
			// ��ȡ���в���
			List<Department> deptList = deptService.findAllDept();
			// ���ݲ�����Ϣ
			session.setAttribute("deptList", deptList);
			// ��ȡ����ְλ
			List<JobInfo> jobList = jobService.findAllJob();
			session.setAttribute("jobList", jobList);
			return "/view/frame/main.jsp";
		} else {
			try {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter pw = response.getWriter();
				pw.flush();
				pw.write("<script>javascript:alert('�û��������������');history.back();</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "/login.jsp";
		}
	}

	/**
	 * Ա���˳�
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
	 * ����id��ѯԱ��
	 * @param request
	 * @param response
	 */
	@RequestMapping("/findEmp")
	@ResponseBody
	public Employee getEmpById(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Employee emp = empService.findEmpById(Long.valueOf(id));
		// �½�һ��Ա�����󱣴��Ҫ��Ϣ����֮ǰһ��ֱ�Ӵ�������Ķ���ᱨ�쳣
		Employee employee = new Employee();
		employee.setNickname(emp.getNickname());
		employee.getJobInfo().setJob(emp.getJobInfo().getJob());
		employee.getDept().setDname(emp.getDept().getDname());
		employee.setPhoneNo(emp.getPhoneNo());
		employee.setOfficeTel(emp.getOfficeTel());
		return employee;
	}

	/**
	 * ��ѯ����Ա����Ϣ������ҳ
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/empInfo")
	public void findAllEmp(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡְλid
		String jobId = request.getParameter("jobId");
		// ��ȡҳ��
		Integer page = Integer.valueOf(request.getParameter("page"));
		// ÿҳ��Ҫ��ʾ������
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		// ��ȡ��ǰҳ��Ҫ��ʾ��Ա��
		// ����map������ʼ�ͽ�����ʾ��������ְλid����
		Map<String, Object> condition = new HashMap<String, Object>();
		if (jobId != null && !"".equals(jobId)) {
			condition.put("jobId", jobId);
		} else {
			condition.put("jobId", 0);
		}
		condition.put("start", (page - 1) * rows + 1);
		condition.put("end", rows * page);
		List<Employee> empList = empService.getPageEmp(condition);

		// ��ȡԱ������
		int count = empService.getCount(condition);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		// ��ǰҳ����
		map.put("rows", empList);

		String empJSON = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
		try {
			response.getWriter().write(empJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ѯ��ͨԱ����Ϣ������ҳ
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/comEmpInfo")
	public void findAllComEmp(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡҳ��
		Integer page = Integer.valueOf(request.getParameter("page"));
		// ÿҳ��Ҫ��ʾ������
		Integer rows = Integer.valueOf(request.getParameter("rows"));
		// ��ȡԱ������
		int count = empService.getComCount();
		// ��ȡ��ǰҳ��Ҫ��ʾ��Ա��
		List<Employee> empList = empService.getPageComEmp((page - 1) * rows + 1, rows * page);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		// ��ǰҳ����
		map.put("rows", empList);

		String empJSON = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
		try {
			response.getWriter().write(empJSON);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ӹ���Ա��������Ա��
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addEmp")
	public String addAdmin(String deptId, Employee emp, HttpServletRequest request) {
		boolean flag = true;
		// ��ȡְλ����
		String jobName = request.getParameter("jobSelect");
		JobInfo jobInfo;
		Department dept;
		if (deptId != null && !"".equals(deptId)) {
			flag = false;
			// ����ְλ���ƣ���ѯְλ��Ϣ
			jobInfo = jobService.findJobByName(jobName);
			// ��ѯ������Ϣ
			dept = deptService.findDeptById(Long.valueOf(deptId));
		} else {
			// ��ѯְλ��Ϣ������ԱְλidΪ2
			jobInfo = jobService.findJobById(2);
			// ��ѯ������Ϣ������ԱΪ��������idΪ1
			dept = deptService.findDeptById(1);
		}

		// �ѹ���Ա��ְλ������֮�������й���
		// ��������ְ״̬
		emp.setWorkstatu("1");
		emp.setDept(dept);
		emp.setJobInfo(jobInfo);
		// ����
		empService.addEmp(emp);

		// ���ź�ְλ��������Ա
		jobInfo.getEmpList().add(emp);
		dept.getEmpList().add(emp);
		// ����
		jobService.updateJob(jobInfo);
		deptService.updateDept(dept);

		if (!flag) {
			return "/view/frame/comEmp_info.jsp";
		} else {
			return "/view/frame/emp_info.jsp";
		}
	}

	/**
	 * ɾ��Ա��
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/delEmp")
	public String delAdmin(HttpServletRequest request) {
		// ��ȡԱ��id
		String id = request.getParameter("id");
		Employee emp = empService.findEmpById(Long.valueOf(id));
		// ɾ�� TODO ��Ա���й������ٵ�����ѯ��󣬻���Ҫɾ�����¼
		empService.delEmp(Long.valueOf(id));
		if (emp.getJobInfo().getId() == 2) {
			return "/view/frame/ordadmin_del.jsp";
		} else {
			return "/view/frame/comEmp_info.jsp";
		}
	}

	/**
	 * ����Ա������
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/rePass")
	public String rePass(HttpServletRequest request) {
		// ��ȡԱ��id
		String id = request.getParameter("id");
		// ��ѯԱ��
		Employee emp = empService.findEmpById(Long.valueOf(id));
		// ��ʼ������Ϊ123
		emp.setPass("123");
		// ����
		empService.updateEmp(emp);
		return "/view/frame/comEmp_info.jsp";
	}
}

package com.hzu.crm.controlloer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzu.crm.entity.Department;
import com.hzu.crm.entity.JobInfo;
import com.hzu.crm.entity.JobRight;
import com.hzu.crm.entity.Rights;
import com.hzu.crm.service.IDepartmentService;
import com.hzu.crm.service.IJobInfoService;

/**
 * 职位控制层
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/job")
public class JobInfoController {

	@Autowired
	private IJobInfoService jobService;
	@Autowired
	private IDepartmentService deptService;

	/**
	 * 添加职位
	 * 
	 * @param job
	 * @param deptId
	 * @return
	 */
	@RequestMapping("/addJob")
	public String addJob(String job, String deptId) {

		// 根据部门id查询部门
		Department dept = deptService.findDeptById(Long.valueOf(deptId));
		JobInfo jobInfo = new JobInfo();
		jobInfo.setJob(job);
		jobInfo.setDept(dept);

		// 添加职位
		jobService.addJob(jobInfo);
		return "/view/frame/emp_info.jsp";
	}

	/**
	 * 查询所有职位
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/allJob")
	public String getAllJob(HttpServletRequest request) {
		List<JobInfo> jobList = jobService.findAllJob();
		HttpSession session = request.getSession(false);
		session.setAttribute("jobList", jobList);
		return "/view/frame/job_update.jsp";
	}

	/**
	 * 根据id获取职位,根据Ajax返回其拥有的权限
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/jobRig")
	@ResponseBody
	public List<Rights> getJobRig(String jobId) {
		// 根据id获取职位
		JobInfo jobInfo = jobService.findJobById(Long.valueOf(jobId));
		// 根据职位获取其拥有的权限对照表记录
		List<JobRight> jrList = jobInfo.getJobRigList();
		// 由于对象数据未知异常，自己不能解决，只能直接返回权限名称
		List<Rights> rigList = new ArrayList<Rights>();
		for (JobRight jobRight : jrList) {
			Rights rights=new Rights();
			String rightName = jobRight.getRights().getRightName();
			rights.setRightName(rightName);
			rigList.add(rights);
		}
		return rigList;
	}
	
	/**
	 * 根据部门外键获取职位名称
	 * @param deptId
	 * @return
	 */
	@RequestMapping("/jobName")
	@ResponseBody
	public List<JobInfo> getJob(String deptId){
		// 根据部门外键获取职位名称
		List<JobInfo> list = jobService.findJobNameByDeptId(Long.valueOf(deptId));
		return list;
	}
}

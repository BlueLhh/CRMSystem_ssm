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
 * ְλ���Ʋ�
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
	 * ���ְλ
	 * 
	 * @param job
	 * @param deptId
	 * @return
	 */
	@RequestMapping("/addJob")
	public String addJob(String job, String deptId) {

		// ���ݲ���id��ѯ����
		Department dept = deptService.findDeptById(Long.valueOf(deptId));
		JobInfo jobInfo = new JobInfo();
		jobInfo.setJob(job);
		jobInfo.setDept(dept);

		// ���ְλ
		jobService.addJob(jobInfo);
		return "/view/frame/emp_info.jsp";
	}

	/**
	 * ��ѯ����ְλ
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
	 * ����id��ȡְλ,����Ajax������ӵ�е�Ȩ��
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/jobRig")
	@ResponseBody
	public List<Rights> getJobRig(String jobId) {
		// ����id��ȡְλ
		JobInfo jobInfo = jobService.findJobById(Long.valueOf(jobId));
		// ����ְλ��ȡ��ӵ�е�Ȩ�޶��ձ��¼
		List<JobRight> jrList = jobInfo.getJobRigList();
		// ���ڶ�������δ֪�쳣���Լ����ܽ����ֻ��ֱ�ӷ���Ȩ������
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
	 * ���ݲ��������ȡְλ����
	 * @param deptId
	 * @return
	 */
	@RequestMapping("/jobName")
	@ResponseBody
	public List<JobInfo> getJob(String deptId){
		// ���ݲ��������ȡְλ����
		List<JobInfo> list = jobService.findJobNameByDeptId(Long.valueOf(deptId));
		return list;
	}
}

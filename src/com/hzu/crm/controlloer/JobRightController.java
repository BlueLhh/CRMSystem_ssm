package com.hzu.crm.controlloer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzu.crm.entity.JobInfo;
import com.hzu.crm.entity.JobRight;
import com.hzu.crm.entity.Rights;
import com.hzu.crm.service.IJobInfoService;
import com.hzu.crm.service.IJobRightService;
import com.hzu.crm.service.IRightsService;

/**
 * 职位权限对照表控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/jobRig")
public class JobRightController {

	@Autowired
	private IJobRightService jobRigService;
	@Autowired
	private IJobInfoService jobService;
	@Autowired
	private IRightsService rigService;

	/**
	 * 添加权限对照记录
	 * @return
	 */
	@RequestMapping("/addJR")
	public String addJobRight(HttpServletRequest request) {
		// 获取职位id
		String jobId = request.getParameter("jobId");
		// 获取权限名称
		String rigName = request.getParameter("rigName");
		// 获取URL
		String rigUrl = request.getParameter("rigUrl");
		// 获取权限级别
		String rigLevel = request.getParameter("rigLevel");
		// 获取上层一级权限
		String rigSelect = request.getParameter("rigSelect");

		// 新建权限
		Rights rights = new Rights();
		// 如果是二级权限再获取，其上层一级权限
		if ("二级权限".equals(rigLevel)) {
			Rights rig = rigService.findRigByName(rigSelect);
			rights.setRightType("2");
			rights.setRig(rig);
		} else {
			// 如果是一级权限
			rights.setRightType("1");
			rights.setRig(rights);
		}
		rights.setRightName(rigName);
		rights.setUrl(rigUrl);
		// 保存权限
		rigService.addRights(rights);

		// 根据id获取职位
		JobInfo jobInfo = jobService.findJobById(Long.valueOf(jobId));

		// 新建权限对照表对象
		JobRight jobRight = new JobRight();
		// 建立关联关系
		jobRight.setJobInfo(jobInfo);
		jobRight.setRights(rights);
		// 保存
		jobRigService.addJobRight(jobRight);

		// 权限关联到权限对照表
		rights.getJobRigList().add(jobRight);
		// 更新权限
		rigService.updateRights(rights);

		// 职位关联到权限对照表
		jobInfo.getJobRigList().add(jobRight);
		// 更新职位
		jobService.updateJob(jobInfo);

		return "/view/frame/job_update.jsp";
	}

	/**
	 * 删除权限对照记录
	 * @return
	 */
	@RequestMapping("/delJR")
	public String delJobRight(String jobId, String rigSelect) {
		// 根据权限名称获取权限
		Rights rig = rigService.findRigByName(rigSelect);
		// 根据职位id和权限id删除权限对照表记录
		jobRigService.delJobRigByFK(rig.getRid(), Long.valueOf(jobId));
		return "/view/frame/job_update.jsp";
	}
}

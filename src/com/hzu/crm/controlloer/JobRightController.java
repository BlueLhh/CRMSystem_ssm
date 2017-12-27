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
 * ְλȨ�޶��ձ���Ʋ�
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
	 * ���Ȩ�޶��ռ�¼
	 * @return
	 */
	@RequestMapping("/addJR")
	public String addJobRight(HttpServletRequest request) {
		// ��ȡְλid
		String jobId = request.getParameter("jobId");
		// ��ȡȨ������
		String rigName = request.getParameter("rigName");
		// ��ȡURL
		String rigUrl = request.getParameter("rigUrl");
		// ��ȡȨ�޼���
		String rigLevel = request.getParameter("rigLevel");
		// ��ȡ�ϲ�һ��Ȩ��
		String rigSelect = request.getParameter("rigSelect");

		// �½�Ȩ��
		Rights rights = new Rights();
		// ����Ƕ���Ȩ���ٻ�ȡ�����ϲ�һ��Ȩ��
		if ("����Ȩ��".equals(rigLevel)) {
			Rights rig = rigService.findRigByName(rigSelect);
			rights.setRightType("2");
			rights.setRig(rig);
		} else {
			// �����һ��Ȩ��
			rights.setRightType("1");
			rights.setRig(rights);
		}
		rights.setRightName(rigName);
		rights.setUrl(rigUrl);
		// ����Ȩ��
		rigService.addRights(rights);

		// ����id��ȡְλ
		JobInfo jobInfo = jobService.findJobById(Long.valueOf(jobId));

		// �½�Ȩ�޶��ձ����
		JobRight jobRight = new JobRight();
		// ����������ϵ
		jobRight.setJobInfo(jobInfo);
		jobRight.setRights(rights);
		// ����
		jobRigService.addJobRight(jobRight);

		// Ȩ�޹�����Ȩ�޶��ձ�
		rights.getJobRigList().add(jobRight);
		// ����Ȩ��
		rigService.updateRights(rights);

		// ְλ������Ȩ�޶��ձ�
		jobInfo.getJobRigList().add(jobRight);
		// ����ְλ
		jobService.updateJob(jobInfo);

		return "/view/frame/job_update.jsp";
	}

	/**
	 * ɾ��Ȩ�޶��ռ�¼
	 * @return
	 */
	@RequestMapping("/delJR")
	public String delJobRight(String jobId, String rigSelect) {
		// ����Ȩ�����ƻ�ȡȨ��
		Rights rig = rigService.findRigByName(rigSelect);
		// ����ְλid��Ȩ��idɾ��Ȩ�޶��ձ��¼
		jobRigService.delJobRigByFK(rig.getRid(), Long.valueOf(jobId));
		return "/view/frame/job_update.jsp";
	}
}

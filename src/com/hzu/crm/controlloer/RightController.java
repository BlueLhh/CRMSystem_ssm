package com.hzu.crm.controlloer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hzu.crm.entity.JobRight;
import com.hzu.crm.entity.Rights;
import com.hzu.crm.service.IJobRightService;
import com.hzu.crm.service.IRightsService;

/**
 * Ȩ�ޱ���Ʋ�
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/rig")
public class RightController {

	@Autowired
	private IRightsService rigService;
	@Autowired
	private IJobRightService jobRigService;

	/**
	 * ��ȡ����Ȩ�ޣ�����ҳ
	 * @param request
	 * @throws IOException 
	 */
	@RequestMapping("/allRig")
	public void getAllRig(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ȡ��ǰҳ��
		int page = Integer.valueOf(request.getParameter("page"));
		// ��ȡ��ǰÿҳ��ʾ����
		int rows = Integer.valueOf(request.getParameter("rows"));

		// ��ѯȨ������
		int count = rigService.getCount();
		// ��ѯ��ǰҳҪ��ʾ��¼
		List<Rights> rigList = rigService.getPageRig((page - 1) * rows + 1, rows * page);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		// ��ǰҳ������
		map.put("rows", rigList);

		String rigJSON = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);

		PrintWriter pw = response.getWriter();
		pw.write(rigJSON);
	}

	/**
	 * ��ѯ����һ��Ȩ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/firstRig")
	@ResponseBody
	public List<Rights> getFirstRig(HttpServletRequest request) {
		// ��ѯ����Ȩ��
		List<Rights> allRig = rigService.findAllRig();
		// ɸѡһ��Ȩ��
		List<Rights> firstRig = new ArrayList<Rights>();
		for (Rights rights : allRig) {
			if (rights.getRightType().equals("1")) {
				Rights rig = new Rights();
				rig.setRightName(rights.getRightName());
				firstRig.add(rig);
			}
		}
		return firstRig;
	}

	@RequestMapping("/delRig")
	public String deleteRig(HttpServletRequest request) {
		// ��ȡȨ��id
		String rid = request.getParameter("rid");

		// ����id��ѯȨ��
		Rights rights = rigService.findRigById(Long.valueOf(rid));

		// ��ȡȨ�޶��ձ��¼
		List<JobRight> rigList = rights.getJobRigList();
		for (JobRight jobRight : rigList) {
			// ��������ɾ����¼
			jobRigService.delJobRigByID(jobRight.getId());
		}

		// ���ɾ����Ȩ��
		rigService.deleteById(rights.getRid());
		return "/view/frame/rights_del.jsp";
	}
}

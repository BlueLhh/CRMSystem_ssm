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
 * 权限表控制层
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
	 * 获取所有权限，并分页
	 * @param request
	 * @throws IOException 
	 */
	@RequestMapping("/allRig")
	public void getAllRig(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取当前页码
		int page = Integer.valueOf(request.getParameter("page"));
		// 获取当前每页显示行数
		int rows = Integer.valueOf(request.getParameter("rows"));

		// 查询权限总数
		int count = rigService.getCount();
		// 查询当前页要显示记录
		List<Rights> rigList = rigService.getPageRig((page - 1) * rows + 1, rows * page);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		// 当前页的数据
		map.put("rows", rigList);

		String rigJSON = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);

		PrintWriter pw = response.getWriter();
		pw.write(rigJSON);
	}

	/**
	 * 查询所有一级权限
	 * @param request
	 * @return
	 */
	@RequestMapping("/firstRig")
	@ResponseBody
	public List<Rights> getFirstRig(HttpServletRequest request) {
		// 查询所有权限
		List<Rights> allRig = rigService.findAllRig();
		// 筛选一级权限
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
		// 获取权限id
		String rid = request.getParameter("rid");

		// 根据id查询权限
		Rights rights = rigService.findRigById(Long.valueOf(rid));

		// 获取权限对照表记录
		List<JobRight> rigList = rights.getJobRigList();
		for (JobRight jobRight : rigList) {
			// 遍历，并删除记录
			jobRigService.delJobRigByID(jobRight.getId());
		}

		// 最后删除该权限
		rigService.deleteById(rights.getRid());
		return "/view/frame/rights_del.jsp";
	}
}

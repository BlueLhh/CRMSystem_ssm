package com.hzu.crm.controlloer;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hzu.crm.entity.CusInfoRecCount;
import com.hzu.crm.entity.Customer;
import com.hzu.crm.entity.CustomerInfo;
import com.hzu.crm.entity.Employee;
import com.hzu.crm.service.ICustomerInfoService;
import com.hzu.crm.service.ICustomerService;
import com.hzu.crm.service.IEmployeeService;

/**
 * 咨询单控制层
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/cusInfo")
public class CustomerInfoController {

	@Autowired
	private ICustomerInfoService cusInfoService;
	@Autowired
	private ICustomerService cusService;
	@Autowired
	private IEmployeeService empService;

	/**
	 * 获取对应销售员的客户信息
	 * 
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/allCusInfo")
	public void getAllInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取销售员id
		String id = request.getParameter("id");
		// 获取当前页数
		int page = Integer.valueOf(request.getParameter("page"));
		// 每页显示行数
		int rows = Integer.valueOf(request.getParameter("rows"));
		// 获取该员工的拥有的销售单记录总数
		// 创建map，将初始和结束显示的行数，职位id传入
		Map<String, Object> condition = new HashMap<String, Object>();
		if (id != null && !"".equals(id)) {
			condition.put("empId", id);
		} else {
			condition.put("empId", 0);
		}
		int count = cusInfoService.getCountByEmpId(condition);
		condition.put("start", (page - 1) * rows + 1);
		condition.put("end", rows * page);

		// 分页查询
		List<CustomerInfo> list = cusInfoService.findInfoByPage(condition);
		// 遍历销售单，把客户id，客户姓名，员工id写入
		// 需要传递到页面的实际数组
		List<CustomerInfo> infoList = new ArrayList<CustomerInfo>();
		for (CustomerInfo customerInfo : list) {
			CustomerInfo info = new CustomerInfo();
			info.setId(customerInfo.getId());
			info.setCusId(customerInfo.getCustomer().getId());
			info.setCusName(customerInfo.getCustomer().getName());
			info.setEmpName(customerInfo.getEmployee().getUsername());
			info.setStatu(customerInfo.getStatu());
			info.setLastFollowDate(customerInfo.getLastFollowDate());
			info.setPlanDate(customerInfo.getPlanDate());
			info.setStartDate(customerInfo.getStartDate());
			info.setMark(customerInfo.getMark());
			infoList.add(info);
		}

		// 传递销售单
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		map.put("rows", infoList);

		String jsonInfo = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
		PrintWriter pw = response.getWriter();
		pw.write(jsonInfo);
	}

	/**
	 * 获取对应销售员的统计数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/infoCount")
	public void getInfoCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取销售员id
		String id = request.getParameter("id");
		// 获取查询条件
		// 今日数据
		String today = request.getParameter("today");
		// 诺在今日
		String start = request.getParameter("start");
		// 历史遗留
		String history = request.getParameter("history");
		// 本月统计
		String month = request.getParameter("month");
		// 获取该员工的拥有的销售单记录总数
		// 创建map，将初始和结束显示的行数，职位id传入
		Map<String, Object> condition = new HashMap<String, Object>();
		if (id != null && !"".equals(id)) {
			condition.put("empId", id);
		} else {
			condition.put("empId", 0);
		}

		// 条件不为空，查询今日数据
		if (today != null && !"".equals(today)) {
			condition.put("today", today);
		}

		// 查询诺在今日，即今天分配给员工的客户
		if (start != null && !"".equals(start)) {
			condition.put("start", start);
		}

		// 查询历史遗留，即最后联系日期在今天之前
		if (history != null && !"".equals(history)) {
			condition.put("history", history);
		}

		// 查询本月统计，即最后联系日期在今天之前
		if (month != null && !"".equals(month)) {
			condition.put("month", month);
		}

		// 未联系订单
		condition.put("statu", 0);
		CusInfoRecCount noConn = new CusInfoRecCount("未联系", cusInfoService.getStatuCount(condition));
		// 未接通
		condition.put("statu", 1);
		CusInfoRecCount noCall = new CusInfoRecCount("未接通", cusInfoService.getStatuCount(condition));
		// 紧跟
		condition.put("statu", 2);
		CusInfoRecCount follow = new CusInfoRecCount("紧跟", cusInfoService.getStatuCount(condition));
		// 已上门
		condition.put("statu", 3);
		CusInfoRecCount visit = new CusInfoRecCount("已上门", cusInfoService.getStatuCount(condition));
		// 死单
		condition.put("statu", 4);
		CusInfoRecCount death = new CusInfoRecCount("死单", cusInfoService.getStatuCount(condition));
		// 电话失效
		condition.put("statu", 5);
		CusInfoRecCount callLose = new CusInfoRecCount("电话失效", cusInfoService.getStatuCount(condition));

		List<CusInfoRecCount> list = new ArrayList<CusInfoRecCount>();
		list.add(callLose);
		list.add(visit);
		list.add(death);
		list.add(follow);
		list.add(noCall);
		list.add(noConn);

		String jsonInfo = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
		PrintWriter pw = response.getWriter();
		pw.write(jsonInfo);
	}

	/**
	 * 更新销售单信息
	 * 
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/updateInfo")
	public String updateCusInfo(HttpServletRequest request) throws ParseException {
		// 获取信息
		// 订单编号
		String id = request.getParameter("id");
		// 客户编号
		String cusId = request.getParameter("cusId");
		// 员工姓名
		String empName = request.getParameter("empName");
		String statu = request.getParameter("statu");
		String lastFollowDate = request.getParameter("lastFollowDate");
		String planDate = request.getParameter("planDate");
		String mark = request.getParameter("mark");
		String startDate = request.getParameter("startDate");
		// 根据id查询客户
		Customer customer = cusService.findCusById(Long.valueOf(cusId));
		// 根据员工姓名查询员工
		Employee employee = empService.findEmpByName(empName);

		// 新建销售单对象，写入信息
		CustomerInfo info = new CustomerInfo();
		info.setId(Long.valueOf(id));
		info.setStatu(statu);
		info.setMark(mark);
		info.setStartDate(new Date(Long.valueOf(startDate)));
		info.setCustomer(customer);
		info.setEmployee(employee);

		// 把经过修改的日期字符串，重新转换成时间戳
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		// 如果包含“/”则证明字符串在前台经过修改
		if (lastFollowDate.contains("/")) {
			Date date = format.parse(lastFollowDate);
			info.setLastFollowDate(date);
		} else {
			info.setLastFollowDate(new Date(Long.valueOf(lastFollowDate)));
		}

		if (planDate.contains("/")) {
			Date date = format.parse(planDate);
			info.setPlanDate(date);
		} else {
			info.setPlanDate(new Date(Long.valueOf(planDate)));
		}

		// 更新
		cusInfoService.updateCusInfo(info);
		return "/view/frame/cus_infoAndRecord.jsp";
	}

	/**
	 * 获取所有员工的订单数
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/allStatu")
	public void getallAllStatu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 今日数据
		String today = request.getParameter("today");
		// 诺在今日
		String start = request.getParameter("start");
		// 历史遗留
		String history = request.getParameter("history");
		// 本月统计
		String month = request.getParameter("month");
		// 创建map，将初始和结束显示的行数，职位id传入
		Map<String, Object> condition = new HashMap<String, Object>();
		// 条件不为空，查询今日数据
		if (today != null && !"".equals(today)) {
			condition.put("today", today);
		}

		// 查询诺在今日，即今天分配给员工的客户
		if (start != null && !"".equals(start)) {
			condition.put("start", start);
		}

		// 查询历史遗留，即最后联系日期在今天之前
		if (history != null && !"".equals(history)) {
			condition.put("history", history);
		}

		// 查询本月统计，即最后联系日期在今天之前
		if (month != null && !"".equals(month)) {
			condition.put("month", month);
		}

		List<CustomerInfo> list = cusInfoService.findAllStatu(condition);
		long id = 0;
		if (list != null && list.size() > 0) {
			id = list.get(0).getEmployee().getId();
		}
		CusInfoRecCount c = new CusInfoRecCount();
		c.setEmpId(id);
		List<CusInfoRecCount> cList = new ArrayList<CusInfoRecCount>();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				cList.add(c);
			}
			if (id == list.get(i).getEmployee().getId()) {
				switch (list.get(i).getStatu()) {
				case "0":
					c.setNoConnCount(list.get(i).getCount());
					break;
				case "1":
					c.setNoCallCount(list.get(i).getCount());
					break;
				case "2":
					c.setFollowCount(list.get(i).getCount());
					break;
				case "3":
					c.setDeathCount(list.get(i).getCount());
					break;
				case "4":
					c.setVisitCount(list.get(i).getCount());
					break;
				case "5":
					c.setCallLoseCount(list.get(i).getCount());
					break;
				}
			} else {
				cList.add(c);
				id = list.get(i).getEmployee().getId();
				c = new CusInfoRecCount();
				c.setEmpId(id);
				switch (list.get(i).getStatu()) {
				case "0":
					c.setNoConnCount(list.get(i).getCount());
					break;
				case "1":
					c.setNoCallCount(list.get(i).getCount());
					break;
				case "2":
					c.setFollowCount(list.get(i).getCount());
					break;
				case "3":
					c.setDeathCount(list.get(i).getCount());
					break;
				case "4":
					c.setVisitCount(list.get(i).getCount());
					break;
				case "5":
					c.setCallLoseCount(list.get(i).getCount());
					break;
				}
			}
		}

		String jsonInfo = JSON.toJSONString(cList, SerializerFeature.DisableCircularReferenceDetect);
		PrintWriter pw = response.getWriter();
		pw.write(jsonInfo);
	}
}

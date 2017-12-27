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
import com.hzu.crm.entity.ConsultRecord;
import com.hzu.crm.entity.CusInfoRecCount;
import com.hzu.crm.entity.Customer;
import com.hzu.crm.entity.CustomerInfo;
import com.hzu.crm.entity.Employee;
import com.hzu.crm.service.IConsultRecordService;
import com.hzu.crm.service.ICustomerInfoService;
import com.hzu.crm.service.ICustomerService;
import com.hzu.crm.service.IEmployeeService;

/**
 * 咨询单控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/record")
public class ConsultRecordController {

	@Autowired
	private IConsultRecordService recordService;
	@Autowired
	private ICustomerInfoService cusInfoService;
	@Autowired
	private ICustomerService cusService;
	@Autowired
	private IEmployeeService empService;

	/**
	 * 分配客户，新增跟踪单或者咨询单
	 * @param request
	 * @return
	 */
	@RequestMapping("addOrder")
	public String addOrder(HttpServletRequest request) {
		// 获取客户id
		String cusId = request.getParameter("cusId");
		// 获取员工id
		String empId = request.getParameter("empId");

		// 根据id查询客户
		Customer customer = cusService.findCusById(Long.valueOf(cusId));
		// 根据id查询员工
		Employee employee = empService.findEmpById(Long.valueOf(empId));

		// id为8则证明该员工职位为销售员
		if (employee.getJobInfo().getId() == 8) {
			// 员工为销售员，新建跟踪单
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setCustomer(customer);
			customerInfo.setEmployee(employee);
			customerInfo.setStatu("0");
			customerInfo.setStartDate(new java.sql.Timestamp(new Date().getTime()));
			customerInfo.setLastFollowDate(new java.sql.Timestamp(new Date().getTime()));
			customerInfo.setPlanDate(new java.sql.Timestamp(new Date().getTime()));
			customerInfo.setMark("这是备注");
			// 保存
			cusInfoService.addCusInfo(customerInfo);

			// 更新客户和员工
			customer.getInfoList().add(customerInfo);
			customer.setCustomStatu("2");
			cusService.updateCustomer(customer);
			employee.getInfoList().add(customerInfo);
			empService.updateEmp(employee);
		} else {
			// 员工为咨询师（网络咨询），新建咨询单
			ConsultRecord record = new ConsultRecord();
			record.setCustomer(customer);
			record.setEmployee(employee);
			record.setConsultStatu("0");
			record.setConsultDate(new java.sql.Timestamp(new Date().getTime()));
			record.setResult("还是备注");
			// 保存
			recordService.addConsultRecord(record);

			// 更新客户和员工
			customer.getConsultList().add(record);
			customer.setCustomStatu("3");
			cusService.updateCustomer(customer);
			employee.getConsultList().add(record);
			empService.updateEmp(employee);
		}
		return "/view/frame/cus_update.jsp";
	}

	/**
	 * 更新咨询单信息
	 * 
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/updateRecord")
	public String updateRecord(HttpServletRequest request) throws ParseException {
		// 获取信息
		// 订单编号
		String id = request.getParameter("id");
		// 客户编号
		String cusId = request.getParameter("cusId");
		// 员工姓名
		String empName = request.getParameter("empName");
		String consultStatu = request.getParameter("consultStatu");
		String consultDate = request.getParameter("consultDate");
		String result = request.getParameter("result");
		// 根据id查询客户
		Customer customer = cusService.findCusById(Long.valueOf(cusId));
		// 根据员工姓名查询员工
		Employee employee = empService.findEmpByName(empName);

		// 新建咨询单对象，写入信息
		ConsultRecord record = new ConsultRecord();
		record.setId(Long.valueOf(id));
		record.setConsultStatu(consultStatu);
		record.setResult(result);
		record.setCustomer(customer);
		record.setEmployee(employee);

		// 把经过修改的日期字符串，重新转换成时间戳
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		// 如果包含“/”则证明字符串在前台经过修改
		if (consultDate.contains("/")) {
			Date date = format.parse(consultDate);
			record.setConsultDate(date);
		} else {
			record.setConsultDate(new Date(Long.valueOf(consultDate)));
		}

		// 更新
		recordService.updateConsultRecord(record);
		return "/view/frame/cus_infoAndRecord.jsp";
	}

	/**
	 * 获取对应咨询员工的咨询单信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/allRecord")
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
		int count = recordService.getConRecCount(condition);

		condition.put("start", (page - 1) * rows + 1);
		condition.put("end", rows * page);

		// 分页查询
		List<ConsultRecord> list = recordService.findConRecByPage(condition);

		// 遍历咨询单，把客户id，客户姓名，员工id写入
		// 需要传递到页面的实际数组
		List<ConsultRecord> recordList = new ArrayList<ConsultRecord>();
		for (ConsultRecord record : list) {
			ConsultRecord consultRecord = new ConsultRecord();
			consultRecord.setId(record.getId());
			consultRecord.setCusId(record.getCustomer().getId());
			consultRecord.setCusName(record.getCustomer().getName());
			consultRecord.setEmpName(record.getEmployee().getUsername());
			consultRecord.setConsultStatu(record.getConsultStatu());
			consultRecord.setConsultDate(record.getConsultDate());
			consultRecord.setResult(record.getResult());
			recordList.add(consultRecord);
		}

		// 传递销售单
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		map.put("rows", recordList);

		String jsonInfo = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
		PrintWriter pw = response.getWriter();
		pw.write(jsonInfo);
	}

	/**
	 * 获取对应咨询师的统计数据
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/recordCount")
	public void getRecordCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取咨询师id
		String id = request.getParameter("id");
		// 获取查询条件jobInfo
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

		// 新增
		condition.put("statu", 0);
		CusInfoRecCount add = new CusInfoRecCount("新增", recordService.getStatuCount(condition));
		// 紧跟
		condition.put("statu", 1);
		CusInfoRecCount follow = new CusInfoRecCount("紧跟", recordService.getStatuCount(condition));
		// 已报名
		condition.put("statu", 2);
		CusInfoRecCount enroll = new CusInfoRecCount("已报名", recordService.getStatuCount(condition));
		// 死单
		condition.put("statu", 3);
		CusInfoRecCount death = new CusInfoRecCount("死单", recordService.getStatuCount(condition));
		// 报名后退费
		condition.put("statu", 4);
		CusInfoRecCount exit = new CusInfoRecCount("报名后退费", recordService.getStatuCount(condition));

		List<CusInfoRecCount> list = new ArrayList<CusInfoRecCount>();
		list.add(add);
		list.add(follow);
		list.add(enroll);
		list.add(death);
		list.add(exit);

		String jsonInfo = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
		PrintWriter pw = response.getWriter();
		pw.write(jsonInfo);
	}

	/**
	 * 获取所有员工的咨询单数
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/allRecordStatu")
	public void getallAllStatu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取职位主管信息，判断是咨询师主管还是网络咨询主管
		String jobInfo = request.getParameter("jobInfo");
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
		// 职位条件不为空
		if (jobInfo != null && !"".equals(jobInfo)) {
			condition.put("jobInfo", jobInfo);
			List<Long> empIds=empService.findEmpIdByJobId(4);
			condition.put("empIds", empIds);
		}
		
		// 条件不为空，查询今日数据
		if (today != null && !"".equals(today)) {
			condition.put("today", today);
		}

		// 查询诺在今日，即今天分配给员工的客户
		if (start != null && !"".equals(start)) {
			condition.put("start", start);
		}

		// 查询历史遗留，即咨询日期在今天之前
		if (history != null && !"".equals(history)) {
			condition.put("history", history);
		}

		// 查询本月统计，即咨询日期在今天之前，30天内
		if (month != null && !"".equals(month)) {
			condition.put("month", month);
		}

		// 开始查询
		List<ConsultRecord> list = recordService.findAllStatu(condition);

		long id = 0;
		if (list != null && list.size() > 0) {
			id = list.get(0).getEmployee().getId();
		}
		CusInfoRecCount c = new CusInfoRecCount();
		c.setEmpId(id);
		List<CusInfoRecCount> cList = new ArrayList<CusInfoRecCount>();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1 && id != list.get(0).getEmployee().getId()) {
				cList.add(c);
			}
			if (id == list.get(i).getEmployee().getId()) {
				switch (list.get(i).getConsultStatu()) {
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
				}

			} else {
				cList.add(c);
				id = list.get(i).getEmployee().getId();
				c = new CusInfoRecCount();
				c.setEmpId(id);
				switch (list.get(i).getConsultStatu()) {
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
				}
			}
		}

		// for (CusInfoRecCount cusInfoRecCount : cList) {
		// System.out.println(cusInfoRecCount);
		// }

		String jsonInfo = JSON.toJSONString(cList, SerializerFeature.DisableCircularReferenceDetect);
		PrintWriter pw = response.getWriter();
		pw.write(jsonInfo);
	}
}

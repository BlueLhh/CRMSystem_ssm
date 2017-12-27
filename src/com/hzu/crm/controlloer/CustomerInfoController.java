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
 * ��ѯ�����Ʋ�
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
	 * ��ȡ��Ӧ����Ա�Ŀͻ���Ϣ
	 * 
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/allCusInfo")
	public void getAllInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ȡ����Աid
		String id = request.getParameter("id");
		// ��ȡ��ǰҳ��
		int page = Integer.valueOf(request.getParameter("page"));
		// ÿҳ��ʾ����
		int rows = Integer.valueOf(request.getParameter("rows"));
		// ��ȡ��Ա����ӵ�е����۵���¼����
		// ����map������ʼ�ͽ�����ʾ��������ְλid����
		Map<String, Object> condition = new HashMap<String, Object>();
		if (id != null && !"".equals(id)) {
			condition.put("empId", id);
		} else {
			condition.put("empId", 0);
		}
		int count = cusInfoService.getCountByEmpId(condition);
		condition.put("start", (page - 1) * rows + 1);
		condition.put("end", rows * page);

		// ��ҳ��ѯ
		List<CustomerInfo> list = cusInfoService.findInfoByPage(condition);
		// �������۵����ѿͻ�id���ͻ�������Ա��idд��
		// ��Ҫ���ݵ�ҳ���ʵ������
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

		// �������۵�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		map.put("rows", infoList);

		String jsonInfo = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
		PrintWriter pw = response.getWriter();
		pw.write(jsonInfo);
	}

	/**
	 * ��ȡ��Ӧ����Ա��ͳ������
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/infoCount")
	public void getInfoCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ȡ����Աid
		String id = request.getParameter("id");
		// ��ȡ��ѯ����
		// ��������
		String today = request.getParameter("today");
		// ŵ�ڽ���
		String start = request.getParameter("start");
		// ��ʷ����
		String history = request.getParameter("history");
		// ����ͳ��
		String month = request.getParameter("month");
		// ��ȡ��Ա����ӵ�е����۵���¼����
		// ����map������ʼ�ͽ�����ʾ��������ְλid����
		Map<String, Object> condition = new HashMap<String, Object>();
		if (id != null && !"".equals(id)) {
			condition.put("empId", id);
		} else {
			condition.put("empId", 0);
		}

		// ������Ϊ�գ���ѯ��������
		if (today != null && !"".equals(today)) {
			condition.put("today", today);
		}

		// ��ѯŵ�ڽ��գ�����������Ա���Ŀͻ�
		if (start != null && !"".equals(start)) {
			condition.put("start", start);
		}

		// ��ѯ��ʷ�������������ϵ�����ڽ���֮ǰ
		if (history != null && !"".equals(history)) {
			condition.put("history", history);
		}

		// ��ѯ����ͳ�ƣ��������ϵ�����ڽ���֮ǰ
		if (month != null && !"".equals(month)) {
			condition.put("month", month);
		}

		// δ��ϵ����
		condition.put("statu", 0);
		CusInfoRecCount noConn = new CusInfoRecCount("δ��ϵ", cusInfoService.getStatuCount(condition));
		// δ��ͨ
		condition.put("statu", 1);
		CusInfoRecCount noCall = new CusInfoRecCount("δ��ͨ", cusInfoService.getStatuCount(condition));
		// ����
		condition.put("statu", 2);
		CusInfoRecCount follow = new CusInfoRecCount("����", cusInfoService.getStatuCount(condition));
		// ������
		condition.put("statu", 3);
		CusInfoRecCount visit = new CusInfoRecCount("������", cusInfoService.getStatuCount(condition));
		// ����
		condition.put("statu", 4);
		CusInfoRecCount death = new CusInfoRecCount("����", cusInfoService.getStatuCount(condition));
		// �绰ʧЧ
		condition.put("statu", 5);
		CusInfoRecCount callLose = new CusInfoRecCount("�绰ʧЧ", cusInfoService.getStatuCount(condition));

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
	 * �������۵���Ϣ
	 * 
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/updateInfo")
	public String updateCusInfo(HttpServletRequest request) throws ParseException {
		// ��ȡ��Ϣ
		// �������
		String id = request.getParameter("id");
		// �ͻ����
		String cusId = request.getParameter("cusId");
		// Ա������
		String empName = request.getParameter("empName");
		String statu = request.getParameter("statu");
		String lastFollowDate = request.getParameter("lastFollowDate");
		String planDate = request.getParameter("planDate");
		String mark = request.getParameter("mark");
		String startDate = request.getParameter("startDate");
		// ����id��ѯ�ͻ�
		Customer customer = cusService.findCusById(Long.valueOf(cusId));
		// ����Ա��������ѯԱ��
		Employee employee = empService.findEmpByName(empName);

		// �½����۵�����д����Ϣ
		CustomerInfo info = new CustomerInfo();
		info.setId(Long.valueOf(id));
		info.setStatu(statu);
		info.setMark(mark);
		info.setStartDate(new Date(Long.valueOf(startDate)));
		info.setCustomer(customer);
		info.setEmployee(employee);

		// �Ѿ����޸ĵ������ַ���������ת����ʱ���
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		// ���������/����֤���ַ�����ǰ̨�����޸�
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

		// ����
		cusInfoService.updateCusInfo(info);
		return "/view/frame/cus_infoAndRecord.jsp";
	}

	/**
	 * ��ȡ����Ա���Ķ�����
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/allStatu")
	public void getallAllStatu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��������
		String today = request.getParameter("today");
		// ŵ�ڽ���
		String start = request.getParameter("start");
		// ��ʷ����
		String history = request.getParameter("history");
		// ����ͳ��
		String month = request.getParameter("month");
		// ����map������ʼ�ͽ�����ʾ��������ְλid����
		Map<String, Object> condition = new HashMap<String, Object>();
		// ������Ϊ�գ���ѯ��������
		if (today != null && !"".equals(today)) {
			condition.put("today", today);
		}

		// ��ѯŵ�ڽ��գ�����������Ա���Ŀͻ�
		if (start != null && !"".equals(start)) {
			condition.put("start", start);
		}

		// ��ѯ��ʷ�������������ϵ�����ڽ���֮ǰ
		if (history != null && !"".equals(history)) {
			condition.put("history", history);
		}

		// ��ѯ����ͳ�ƣ��������ϵ�����ڽ���֮ǰ
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

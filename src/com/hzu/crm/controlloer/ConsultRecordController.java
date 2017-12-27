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
 * ��ѯ�����Ʋ�
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
	 * ����ͻ����������ٵ�������ѯ��
	 * @param request
	 * @return
	 */
	@RequestMapping("addOrder")
	public String addOrder(HttpServletRequest request) {
		// ��ȡ�ͻ�id
		String cusId = request.getParameter("cusId");
		// ��ȡԱ��id
		String empId = request.getParameter("empId");

		// ����id��ѯ�ͻ�
		Customer customer = cusService.findCusById(Long.valueOf(cusId));
		// ����id��ѯԱ��
		Employee employee = empService.findEmpById(Long.valueOf(empId));

		// idΪ8��֤����Ա��ְλΪ����Ա
		if (employee.getJobInfo().getId() == 8) {
			// Ա��Ϊ����Ա���½����ٵ�
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setCustomer(customer);
			customerInfo.setEmployee(employee);
			customerInfo.setStatu("0");
			customerInfo.setStartDate(new java.sql.Timestamp(new Date().getTime()));
			customerInfo.setLastFollowDate(new java.sql.Timestamp(new Date().getTime()));
			customerInfo.setPlanDate(new java.sql.Timestamp(new Date().getTime()));
			customerInfo.setMark("���Ǳ�ע");
			// ����
			cusInfoService.addCusInfo(customerInfo);

			// ���¿ͻ���Ա��
			customer.getInfoList().add(customerInfo);
			customer.setCustomStatu("2");
			cusService.updateCustomer(customer);
			employee.getInfoList().add(customerInfo);
			empService.updateEmp(employee);
		} else {
			// Ա��Ϊ��ѯʦ��������ѯ�����½���ѯ��
			ConsultRecord record = new ConsultRecord();
			record.setCustomer(customer);
			record.setEmployee(employee);
			record.setConsultStatu("0");
			record.setConsultDate(new java.sql.Timestamp(new Date().getTime()));
			record.setResult("���Ǳ�ע");
			// ����
			recordService.addConsultRecord(record);

			// ���¿ͻ���Ա��
			customer.getConsultList().add(record);
			customer.setCustomStatu("3");
			cusService.updateCustomer(customer);
			employee.getConsultList().add(record);
			empService.updateEmp(employee);
		}
		return "/view/frame/cus_update.jsp";
	}

	/**
	 * ������ѯ����Ϣ
	 * 
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/updateRecord")
	public String updateRecord(HttpServletRequest request) throws ParseException {
		// ��ȡ��Ϣ
		// �������
		String id = request.getParameter("id");
		// �ͻ����
		String cusId = request.getParameter("cusId");
		// Ա������
		String empName = request.getParameter("empName");
		String consultStatu = request.getParameter("consultStatu");
		String consultDate = request.getParameter("consultDate");
		String result = request.getParameter("result");
		// ����id��ѯ�ͻ�
		Customer customer = cusService.findCusById(Long.valueOf(cusId));
		// ����Ա��������ѯԱ��
		Employee employee = empService.findEmpByName(empName);

		// �½���ѯ������д����Ϣ
		ConsultRecord record = new ConsultRecord();
		record.setId(Long.valueOf(id));
		record.setConsultStatu(consultStatu);
		record.setResult(result);
		record.setCustomer(customer);
		record.setEmployee(employee);

		// �Ѿ����޸ĵ������ַ���������ת����ʱ���
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		// ���������/����֤���ַ�����ǰ̨�����޸�
		if (consultDate.contains("/")) {
			Date date = format.parse(consultDate);
			record.setConsultDate(date);
		} else {
			record.setConsultDate(new Date(Long.valueOf(consultDate)));
		}

		// ����
		recordService.updateConsultRecord(record);
		return "/view/frame/cus_infoAndRecord.jsp";
	}

	/**
	 * ��ȡ��Ӧ��ѯԱ������ѯ����Ϣ
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/allRecord")
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
		int count = recordService.getConRecCount(condition);

		condition.put("start", (page - 1) * rows + 1);
		condition.put("end", rows * page);

		// ��ҳ��ѯ
		List<ConsultRecord> list = recordService.findConRecByPage(condition);

		// ������ѯ�����ѿͻ�id���ͻ�������Ա��idд��
		// ��Ҫ���ݵ�ҳ���ʵ������
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

		// �������۵�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		map.put("rows", recordList);

		String jsonInfo = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
		PrintWriter pw = response.getWriter();
		pw.write(jsonInfo);
	}

	/**
	 * ��ȡ��Ӧ��ѯʦ��ͳ������
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/recordCount")
	public void getRecordCount(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ȡ��ѯʦid
		String id = request.getParameter("id");
		// ��ȡ��ѯ����jobInfo
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

		// ����
		condition.put("statu", 0);
		CusInfoRecCount add = new CusInfoRecCount("����", recordService.getStatuCount(condition));
		// ����
		condition.put("statu", 1);
		CusInfoRecCount follow = new CusInfoRecCount("����", recordService.getStatuCount(condition));
		// �ѱ���
		condition.put("statu", 2);
		CusInfoRecCount enroll = new CusInfoRecCount("�ѱ���", recordService.getStatuCount(condition));
		// ����
		condition.put("statu", 3);
		CusInfoRecCount death = new CusInfoRecCount("����", recordService.getStatuCount(condition));
		// �������˷�
		condition.put("statu", 4);
		CusInfoRecCount exit = new CusInfoRecCount("�������˷�", recordService.getStatuCount(condition));

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
	 * ��ȡ����Ա������ѯ����
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/allRecordStatu")
	public void getallAllStatu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ȡְλ������Ϣ���ж�����ѯʦ���ܻ���������ѯ����
		String jobInfo = request.getParameter("jobInfo");
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
		// ְλ������Ϊ��
		if (jobInfo != null && !"".equals(jobInfo)) {
			condition.put("jobInfo", jobInfo);
			List<Long> empIds=empService.findEmpIdByJobId(4);
			condition.put("empIds", empIds);
		}
		
		// ������Ϊ�գ���ѯ��������
		if (today != null && !"".equals(today)) {
			condition.put("today", today);
		}

		// ��ѯŵ�ڽ��գ�����������Ա���Ŀͻ�
		if (start != null && !"".equals(start)) {
			condition.put("start", start);
		}

		// ��ѯ��ʷ����������ѯ�����ڽ���֮ǰ
		if (history != null && !"".equals(history)) {
			condition.put("history", history);
		}

		// ��ѯ����ͳ�ƣ�����ѯ�����ڽ���֮ǰ��30����
		if (month != null && !"".equals(month)) {
			condition.put("month", month);
		}

		// ��ʼ��ѯ
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

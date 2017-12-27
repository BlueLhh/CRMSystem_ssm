package com.hzu.crm.controlloer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hzu.crm.entity.Customer;
import com.hzu.crm.entity.Employee;
import com.hzu.crm.service.ICustomerService;
import com.hzu.crm.service.IEmployeeService;

/**
 * �ͻ����Ʋ�
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/cus")
public class CustmerController {

	@Autowired
	private ICustomerService cusService;
	@Autowired
	private IEmployeeService empService;

	/**
	 * ���¿ͻ�
	 * @param cus
	 * @return
	 */
	@RequestMapping("/updateCus")
	public String updateCus(HttpServletRequest request) {
		// ��ȡ��Ϣ
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String education = request.getParameter("education");
		String phoneNo = request.getParameter("phoneNo");
		String qq = request.getParameter("qq");
		String email = request.getParameter("email");
		String customStatu = request.getParameter("customStatu");
		String createDate = request.getParameter("createDate");
		String inviteName = request.getParameter("inviteName");

		// ����һ���ͻ���д����Ϣ
		Customer customer = new Customer();
		customer.setId(Long.valueOf(id));
		customer.setName(name);
		customer.setEducation(education);
		customer.setPhoneNo(phoneNo);
		customer.setQq(qq);
		customer.setEmail(email);
		customer.setCustomStatu(customStatu);
		customer.setCreateDate(new Date(Long.valueOf(createDate)));
		customer.setInviteName(inviteName);

		// ����
		cusService.updateCustomer(customer);
		return "";
	}

	/**
	 * ��ȡ�����û�������ҳ
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/allCus")
	public void getAllCus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ȡ��ǰҳ��
		int page = Integer.valueOf(request.getParameter("page"));
		// ��ȡ��ǰÿҳ��ʾ����
		int rows = Integer.valueOf(request.getParameter("rows"));

		int count = cusService.getCount();
		List<Customer> cusList = cusService.findCusByPage((page - 1) * rows + 1, rows * page);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		// ��ǰҳ������
		map.put("rows", cusList);

		String rigJSON = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);

		PrintWriter pw = response.getWriter();
		pw.write(rigJSON);
	}

	/**
	 * ��ӿͻ�
	 * @param cus
	 * @return
	 */
	@RequestMapping("/addCus")
	public String addCus(Customer cus, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Employee employee = (Employee) session.getAttribute("employee");
		// ���ÿͻ�״̬
		cus.setCustomStatu("0");
		// ���ô�������
		cus.setCreateDate(new java.sql.Timestamp(new Date().getTime()));
		// ��������������
		cus.setInviteName(employee.getUsername());
		// �ͻ����
		cusService.addCustomer(cus);
		return "/view/frame/cus_add.jsp";
	}

	/**
	 * �ͻ�����
	 * @param request
	 * @return
	 */
	@RequestMapping("/allotCus")
	public String allotCus(HttpServletRequest request) {
		String id = request.getParameter("id");
		// ����id��ѯ�ͻ�
		Customer customer = cusService.findCusById(Long.valueOf(id));
		// �ѿͻ����浽session����
		HttpSession session = request.getSession(false);
		session.setAttribute("customer", customer);
		// ��ѯ������ѯʦ������Ա
		// ��ѯʦְλ���Ϊ4
		List<Employee> conEmpList = empService.getEmpByJobId(4);
		// ������ѯְλ���Ϊ6
		List<Employee> conNetEmpList=empService.getEmpByJobId(6);
		// ����Ա���Ϊ8
		List<Employee> saleEmpList = empService.getEmpByJobId(8);
		// ���������Ϻϳ�һ��Ա������
		conEmpList.addAll(saleEmpList);
		conEmpList.addAll(conNetEmpList);
		// ��Ա�����ϴ浽session
		session.setAttribute("conEmpList", conEmpList);
		return "/view/frame/cus_allot.jsp";
	}
}

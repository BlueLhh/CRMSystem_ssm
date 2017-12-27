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
 * 客户控制层
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
	 * 更新客户
	 * @param cus
	 * @return
	 */
	@RequestMapping("/updateCus")
	public String updateCus(HttpServletRequest request) {
		// 获取信息
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String education = request.getParameter("education");
		String phoneNo = request.getParameter("phoneNo");
		String qq = request.getParameter("qq");
		String email = request.getParameter("email");
		String customStatu = request.getParameter("customStatu");
		String createDate = request.getParameter("createDate");
		String inviteName = request.getParameter("inviteName");

		// 创建一个客户，写入信息
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

		// 更新
		cusService.updateCustomer(customer);
		return "";
	}

	/**
	 * 获取所有用户，并分页
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/allCus")
	public void getAllCus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取当前页码
		int page = Integer.valueOf(request.getParameter("page"));
		// 获取当前每页显示行数
		int rows = Integer.valueOf(request.getParameter("rows"));

		int count = cusService.getCount();
		List<Customer> cusList = cusService.findCusByPage((page - 1) * rows + 1, rows * page);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", count);
		// 当前页的数据
		map.put("rows", cusList);

		String rigJSON = JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);

		PrintWriter pw = response.getWriter();
		pw.write(rigJSON);
	}

	/**
	 * 添加客户
	 * @param cus
	 * @return
	 */
	@RequestMapping("/addCus")
	public String addCus(Customer cus, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Employee employee = (Employee) session.getAttribute("employee");
		// 设置客户状态
		cus.setCustomStatu("0");
		// 设置创建日期
		cus.setCreateDate(new java.sql.Timestamp(new Date().getTime()));
		// 设置邀请人姓名
		cus.setInviteName(employee.getUsername());
		// 客户添加
		cusService.addCustomer(cus);
		return "/view/frame/cus_add.jsp";
	}

	/**
	 * 客户分配
	 * @param request
	 * @return
	 */
	@RequestMapping("/allotCus")
	public String allotCus(HttpServletRequest request) {
		String id = request.getParameter("id");
		// 根据id查询客户
		Customer customer = cusService.findCusById(Long.valueOf(id));
		// 把客户保存到session对象
		HttpSession session = request.getSession(false);
		session.setAttribute("customer", customer);
		// 查询所有咨询师和销售员
		// 咨询师职位外键为4
		List<Employee> conEmpList = empService.getEmpByJobId(4);
		// 网络咨询职位外键为6
		List<Employee> conNetEmpList=empService.getEmpByJobId(6);
		// 销售员外键为8
		List<Employee> saleEmpList = empService.getEmpByJobId(8);
		// 把两个集合合成一个员工集合
		conEmpList.addAll(saleEmpList);
		conEmpList.addAll(conNetEmpList);
		// 把员工集合存到session
		session.setAttribute("conEmpList", conEmpList);
		return "/view/frame/cus_allot.jsp";
	}
}

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>客户分配</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<LINK href="<%=basePath%>resource/css/admin.css" type=text/css
	rel=stylesheet>
<script type="text/javascript"
	src="<%=basePath%>resource/js/CheckForm.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resource/js/My97DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript"
	src="<%=basePath%>resource/js/FormValid.js"></script>
<script type="text/javascript" src="<%=basePath%>view/frame/js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="<%=basePath%>view/frame/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>view/frame/js/function.js"></script>
<script type="text/javascript">
	//根据用户id查询用户的相关信息
	function empChange() {
		var empId=$("#empId option:checked").val();
		var nickname=$("#nickname");
		var jobInfo=$("#jobInfo");
		var dept=$("#dept");
		var empPhone=$("#empPhone");
		var empTel=$("#empTel");
		var url = "emp/findEmp.do";
		var param = "id=" + empId;
		$.post(url, param, function(data) {
			nickname.val(data.nickname);
			jobInfo.val(data.jobInfo.job);
			dept.val(data.dept.dname);
			empPhone.val(data.phoneNo);
			empTel.val(data.officeTel);
		});
	}
</script>
</head>
<body>
	<form action="<%=basePath%>record/addOrder.do" name="form1"
		onsubmit="return validator(this)" method="post">
		<!-- 客户id -->
		<input type="hidden" value="${sessionScope.customer.id }" name="cusId">
		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr class=editHeaderTr>
				<td class=editHeaderTd colSpan=7>客户分配</td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">客户姓名：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					maxlength="10" style="width: 145px" name="name" value="${sessionScope.customer.name }" disabled="disabled"></td>
				<td bgcolor="#FFFDF0"><div align="center">教育水平：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					maxlength="10" style="width: 145px" name="education" value="${sessionScope.customer.education }" disabled="disabled"></td>
			</tr>

			<tr>
				<td bgcolor="#FFFDF0"><div align="center">手机号码：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					style="width: 145px" name="phoneNo" value="${sessionScope.customer.phoneNo }" disabled="disabled"></td>
				<td bgcolor="#FFFDF0"><div align="center">QQ号码：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					style="width: 145px" name="qq" value="${sessionScope.customer.qq }" disabled="disabled"></td>
			</tr>

			<tr>
				<td bgcolor="#FFFDF0"><div align="center">电子邮箱：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					maxlength="10" style="width: 145px" name="email" value="${sessionScope.customer.email }" disabled="disabled"/></td>
				<td bgcolor="#FFFDF0"><div align="center">邀请人姓名：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					maxlength="10" style="width: 145px" name="inviteName" value="${sessionScope.customer.inviteName }" disabled="disabled"/></td>
			</tr>
		</table>

		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr bgcolor="#ECF3FD">
				<td style="padding-left: 50px;"><strong>将客户${sessionScope.customer.name }分配给</strong></td>
			</tr>
		</table>
		
		<!-- 分配到员工 -->
		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">员工用户：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><select name="empId" id="empId"
					style="width: 145px" onchange="empChange();">
						<!-- 员工用户名 -->
						<c:forEach var="emp" items="${sessionScope.conEmpList }">
							<option value="${emp.id }" label="">${emp.username}</option>
						</c:forEach>
				</select>
				</td>
				
				<td bgcolor="#FFFDF0"><div align="center">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					maxlength="10" style="width: 145px" id="nickname" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">员工职位：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					style="width: 145px" id="jobInfo" disabled="disabled"></td>
				<td bgcolor="#FFFDF0"><div align="center">所属部门：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					style="width: 145px" id="dept" disabled="disabled"></td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">手机号码：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					style="width: 145px" id="empPhone" disabled="disabled"></td>
				<td bgcolor="#FFFDF0"><div align="center">办公电话：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					style="width: 145px" id="empTel" disabled="disabled"></td>
			</tr>
		</table>
		
		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr bgcolor="#ECF3FD">
				<td width="25%"></td>
				<td width="17%"><input type="submit" name="submit" value="确认分配"></td>
				<td width="4%"><input type="button" name="button"
					onClick="history.back() " value="取消分配"></td>
				<td width="43%"></td>
				<td width="43%"></td>
			</tr>
		</table>
	</form>
</body>
</html>


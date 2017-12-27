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

<title>添加客户信息</title>

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
<script type="text/javascript" src="<%=basePath%>view/frame/js/function.js"></script>
</head>

<body>
	<form action="<%=basePath%>cus/addCus.do" name="form1"
		onsubmit="return validator(this)" method="post">
		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr class=editHeaderTr>
				<td class=editHeaderTd colSpan=7>请输入客户信息</td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">客户姓名：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					maxlength="10" style="width: 145px" valid="required"
					errmsg="客户姓名不能为空!" name="name"></td>
				<td bgcolor="#FFFDF0"><div align="center">教育水平：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					maxlength="10" style="width: 145px" name="education"></td>
			</tr>

			<tr>
				<td bgcolor="#FFFDF0"><div align="center">手机号码：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					valid="regexp" regexp="^1[3|4|5|8][0-9]\d{8}$" errmsg="请输入正确的手机号码!"
					style="width: 145px" name="phoneNo"></td>
				<td bgcolor="#FFFDF0"><div align="center">QQ号码：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					valid="regexp" regexp="^\d{9}$" errmsg="请输入正确的QQ!"
					style="width: 145px" name="qq"></td>
			</tr>

			<tr>
				<td bgcolor="#FFFDF0"><div align="center">电子邮箱：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					maxlength="10" style="width: 145px" name="email" /></td>
			</tr>
		</table>

		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr bgcolor="#ECF3FD">
				<td width="25%"></td>
				<td width="17%"><input type="submit" name="submit" value="添加"></td>
				<td width="17%"><input type="reset" name="reset" value="重置"></td>
				<td width="4%"><input type="button" name="button"
					onClick="history.back() " value="返回"></td>
				<td width="43%"></td>
			</tr>
		</table>
	</form>
</body>
</html>


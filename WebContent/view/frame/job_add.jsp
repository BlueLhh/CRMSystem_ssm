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

<title>增加职位</title>

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
	<form action="<%=basePath%>job/addJob.do" name="form1"
		onsubmit="return jobIsNull(this)" method="post">
		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr class=editHeaderTr>
				<td class=editHeaderTd colSpan=7>请输入职位信息</td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">职位名称：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					maxlength="10" style="width: 145px" name="job" id="job"></td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">职位部门：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
				<select name="deptId" style="width: 145px">
						<!-- 部门名称 -->
						<c:forEach var="dept" items="${sessionScope.deptList }">
							<option value="${dept.id }" label="">${dept.dname }</option>
						</c:forEach>
				</select>
				</td>
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


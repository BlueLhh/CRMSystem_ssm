<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>客户关系管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<frameset rows="98,*,8" border="7" bordercolor="#1873aa" scrolling="No"
	noresize="noresize" framespacing="0">
	<frame src="<%=basePath%>view/frame/top.jsp" name="topFrame"
		scrolling="No" noresize="noresize" id="topFrame" />
	<frameset cols="166,*">
		<frame src="<%=basePath%>view/frame/left.jsp" noresize="noresize" />
		<c:if test="${sessionScope.job=='超级管理员' || sessionScope.job=='咨询师主管'}">
			<frame src="view/frame/emp_info.jsp" name="mainFrame" />
		</c:if>
		<c:if test="${sessionScope.job=='管理员'}">
			<frame src="view/frame/comEmp_info.jsp" name="mainFrame" />
		</c:if>
		<c:if test="${sessionScope.job=='销售助理' }">
			<frame src="view/frame/saleEmp_info.jsp" name="mainFrame" />
		</c:if>
		<c:if test="${sessionScope.job=='销售员' || sessionScope.job=='咨询师' || sessionScope.job=='网络咨询'}">
			<frame src="view/frame/cus_infoAndRecord.jsp" name="mainFrame" />
		</c:if>
		<c:if test="${sessionScope.job=='销售主管' || sessionScope.job=='网络咨询主管'}">
			<frame src="view/frame/cus_allInfoAndRecord.jsp" name="mainFrame" />
		</c:if>
	</frameset>
	<frame src="<%=basePath%>view/frame/down.jsp" name="bottomFrame"
		scrolling="No" noresize="noresize" id="bottomFrame" />
</frameset>
</html>

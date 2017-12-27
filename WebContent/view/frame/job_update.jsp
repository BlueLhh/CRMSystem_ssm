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

<title>修改职位权限</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<LINK href="<%=basePath%>resource/css/admin.css" type=text/css
	rel=stylesheet>
<link href="<%=basePath%>view/frame/css/bootstrap-dropdownhover.min.css" type="text/css" rel="stylesheet">
<link href="<%=basePath%>view/frame/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<script type="text/javascript"
	src="<%=basePath%>resource/js/CheckForm.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resource/js/My97DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript"
	src="<%=basePath%>resource/js/FormValid.js"></script>
<script type="text/javascript" src="<%=basePath%>view/frame/js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="<%=basePath%>view/frame/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>view/frame/js/function.js"></script>
<script type="text/javascript" src="<%=basePath%>view/frame/js/bootstrap.js"></script>
</head>

<body>
	<form action="<%=basePath%>jobRig/delJR.do" name="form1" method="post">
		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr class=editHeaderTr>
				<td class=editHeaderTd colSpan=7>职位及权限信息</td>
			</tr>

			<tr>
				<td bgcolor="#FFFDF0"><div align="center">职位名称：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
				<select id="jobId" name="jobId" style="width: 145px" onchange="rigChange();">
						<c:forEach var="jobInfo" items="${sessionScope.jobList }">
							<option value="${jobInfo.id }" label="">${jobInfo.job }</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">职位权限：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
				<select id="rigSelect" name="rigSelect" style="width: 145px">
				</select>
				</td>
			</tr>
		</table>

		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr bgcolor="#ECF3FD">
				<td width="25%"></td>
				<td width="17%"><input type="button" name="add" value="添加" data-toggle="modal"
							data-target="#myModal"></td>
				<td width="17%"><input type="submit" name="delete" value="删除"></td>
				<td width="4%"><input type="button" name="button"
					onClick="history.back() " value="返回"></td>
				<td width="43%"></td>
			</tr>
		</table>
	</form>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 300px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="myModalLabel">
						<strong><font color="black">添加权限</font></strong>
					</h3>
				</div>
				<div class="modal-body">
					职位名称： <input type="text" id="jobName" name="jobName" value="超级管理员" style="margin-left: -2px;" disabled="disabled">
				</div>
				<div class="modal-body">
					权限名称： <input type="text" id="rigName" name="rigName" style="margin-left: -2px;">
				</div>
				<div class="modal-body">
					选项卡url：<input type="text" id="rigUrl" name="rigUrl">
				</div>
				<div class="modal-body">
					权限级别：
					<select id="rigLevel" name="rigLevel" style="width: 145px;margin-left: -3px;" onchange="allSecondRig();">
						<option>一级权限</option>
						<option>二级权限</option>
					</select>
				</div>
				<div class="modal-body">
					&nbsp;&nbsp;隶 属 于：<select id="allRig" name="allRig" style="width: 145px">
					</select>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" onclick="ensure();">确定</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


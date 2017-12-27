<%@page import="com.hzu.crm.entity.Employee"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
    Employee employee=(Employee)session.getAttribute("employee");
    String url="";
    String job=employee.getJobInfo().getJob();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/view/frame/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/view/frame/themes/icon.css">
<script type="text/javascript"
	src="<%=path%>/view/frame/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/view/frame/js/jquery.easyui.min.js"></script>
<script>
	$(function() {
		var lastIndex;
		/* 职位信息 */
		var job=$("#job").val();
		$('#tt').datagrid({
			pagination:true,//开启分页工具栏
			pageSize:10,//默认选中的每页显示条数
			pageList:[10,20,30],//设置可选择的每页条数
			toolbar : [{
				text : '今日数据',
				iconCls : 'icon-search',
				handler : function() {
					if (job=='销售主管') {
						<%url=path+"/cusInfo/allStatu.do?today=today";%>
						url="<%=url%>";
					}else{
						// 咨询师主管，网络咨询主管
						<%url=path+"/record/allRecordStatu.do?today=today";%>
						url="<%=url%>";
					}
					$("#tt").datagrid({
						url:url
					});
				}
			},"-",{
				text : '历史遗留',
				iconCls : 'icon-search',
				handler : function() {
					var url;
					if (job=='销售主管') {
						<%url=path+"/cusInfo/allStatu.do?history=history";%>
						url="<%=url%>";
					}else{
						<%url=path+"/record/allRecordStatu.do?history=history";%>
						url="<%=url%>";
					}
					$("#tt").datagrid({
						url:url
					});
				}
			},"-",{
				text : '诺在今日',
				iconCls : 'icon-search',
				handler : function() {
					var url;
					if (job=='销售主管') {
						<%url=path+"/cusInfo/allStatu.do?start=start";%>
						url="<%=url%>";
					}else{
						<%url=path+"/record/allRecordStatu.do?start=start";%>
						url="<%=url%>";
					}
					$("#tt").datagrid({
						url:url
					});
				}
			},"-",{
				text : '本月统计',
				iconCls : 'icon-search',
				handler : function() {
					var url;
					if (job=='销售主管') {
						<%url=path+"/cusInfo/allStatu.do?month=month";%>
						url="<%=url%>";
					}else{
						<%url=path+"/record/allRecordStatu.do?month=month&jobInfo=jobInfo";%>
						url="<%=url%>";
					}
					$("#tt").datagrid({
						url:url
					});
				}
			} ],
			onBeforeLoad : function() {
				$(this).datagrid('rejectChanges');
			},
			onClickRow : function(rowIndex) {
				if (lastIndex != rowIndex) {
					$('#tt').datagrid('endEdit', lastIndex);
					$('#tt').datagrid('beginEdit', rowIndex);
				}
				lastIndex = rowIndex;
			}
		}); 
	});
</script>
</head>
<body>
	<h2>客户开发统计数据</h2>
	<!-- 设置隐藏域保存员工职位权限 -->
	<input type="hidden" value="${sessionScope.job}" id="job">
	<table id="tt"
		data-options="iconCls:'icon-edit',singleSelect:true,idField:'cusId',url:'<%=path + url%>'">
		<thead>
			<tr>
			<c:if test="${sessionScope.job=='销售主管' }">
				<th data-options="field:'empId',width:80">销售员编号</th>
				<th data-options="field:'noConnCount',width:80">未联系/笔</th>
				<th data-options="field:'noCallCount',width:80">未接通/笔</th>
				<th data-options="field:'followCount',width:80">紧跟/笔</th>
				<th data-options="field:'visitCount',width:80">已上门/笔</th>
				<th data-options="field:'deathCount',width:80">死单/笔</th>
				<th data-options="field:'callLoseCount',width:80">电话无效/笔</th>
			</c:if>
			<c:if test="${sessionScope.job=='咨询师主管' || sessionScope.job=='网络咨询主管'}">
				<th data-options="field:'empId',width:80">咨询师编号</th>
				<th data-options="field:'noConnCount',width:80">新增/笔</th>
				<th data-options="field:'noCallCount',width:80">紧跟/笔</th>
				<th data-options="field:'followCount',width:80">已报名/笔</th>
				<th data-options="field:'visitCount',width:80">死单/笔</th>
				<th data-options="field:'deathCount',width:80">报名后退费/笔</th>
			</c:if>
			</tr>
		</thead>
	</table>
</body>
</html>
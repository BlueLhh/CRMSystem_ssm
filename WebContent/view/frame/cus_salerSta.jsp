<%@page import="com.hzu.crm.entity.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					var url;
					if (job=='销售员') {
						<%url=path+"/cusInfo/infoCount.do?id="+employee.getId()+"&today=today";%>
						url="<%=url%>";
					}else{
						// 咨询师，网络咨询
						<%url=path+"/record/recordCount.do?id="+employee.getId()+"&today=today";%>
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
					if (job=='销售员') {
						<%url=path+"/cusInfo/infoCount.do?id="+employee.getId()+"&history=history";%>
						url="<%=url%>";
					}else{
						<%url=path+"/record/recordCount.do?id="+employee.getId()+"&history=history";%>
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
					if (job=='销售员') {
						<%url=path+"/cusInfo/infoCount.do?id="+employee.getId()+"&start=start";%>
						url="<%=url%>";
					}else{
						<%url=path+"/record/recordCount.do?id="+employee.getId()+"&start=start";%>
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
					if (job=='销售员') {
						<%url=path+"/cusInfo/infoCount.do?id="+employee.getId()+"&month=month";%>
						url="<%=url%>";
					}else{
						<%url=path+"/record/recordCount.do?id="+employee.getId()+"&month=month";%>
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
				<c:if test="${sessionScope.job=='销售员' }">
					<th data-options="field:'statu',width:80">销售单状态</th>
				</c:if>
				<c:if test="${sessionScope.job=='咨询师' || sessionScope.job=='网络咨询'}">
					<th data-options="field:'statu',width:80">咨询单状态</th>
				</c:if>
				<th data-options="field:'count',width:80">笔数</th>
			</tr>
		</thead>
	</table>	
</body>
</html>
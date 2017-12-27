<%@page import="com.hzu.crm.entity.Employee"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
    Employee employee=(Employee)session.getAttribute("employee");
    String url="";
    String job=employee.getJobInfo().getJob();
    if(job.equals("销售主管")){
    	// 销售主管
    	url="/cusInfo/allCusInfo.do";
    }else{
		// 网络咨询主管
		url="/record/allRecord.do";
    }
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
		$('#tt').datagrid({
			pagination:true,//开启分页工具栏
			pageSize:10,//默认选中的每页显示条数
			pageList:[10,20,30],//设置可选择的每页条数
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
	<h2>客户开发信息</h2>
	<table id="tt"
		data-options="iconCls:'icon-edit',singleSelect:true,idField:'cusId',url:'<%=path + url%>'">
		<thead>
		<c:if test="${sessionScope.job=='销售主管' }">
			<tr>
				<th data-options="field:'id',width:80">销售单编号</th>
				<th data-options="field:'cusId',width:60">客户编号</th>
				<th data-options="field:'cusName',width:120">客户姓名</th>
				<th data-options="field:'empName',width:120">负责员工</th>
				<th
					data-options="field:'statu',width:180,
				formatter:function(value,row,index){
					if(row.statu == 0){
						return '未联系';
					}else if(row.statu == 1){
						return '未接通';
					}else if(row.statu == 2){
						return '紧跟';
					}else if(row.statu == 3){
						return '已上门';
					}else if(row.statu == 4){
						return '死单';
					}else{
						return '电话无效';
					}
				}">跟单状态</th>
				<th data-options="field:'lastFollowDate',width:180,
				formatter:function(value,row,index){
					var unixTimestamp = new Date(value);  
                    return unixTimestamp.toLocaleString(); 
				}">最近联系日期</th>
				<th data-options="field:'planDate',width:180,
				formatter:function(value,row,index){
					var unixTimestamp = new Date(value);  
                    return unixTimestamp.toLocaleString(); 
				}">计划联系日期</th>
				<th data-options="field:'mark',width:180">备注</th>
				<th data-options="field:'startDate',width:180,
				formatter:function(value,row,index){
					var unixTimestamp = new Date(value);  
                    return unixTimestamp.toLocaleString(); 
				}">开始日期</th>
			</tr>
		</c:if>
		<c:if test="${sessionScope.job=='咨询师主管' || sessionScope.job=='网络咨询主管'}">
			<tr>
				<th data-options="field:'id',width:80">咨询单编号</th>
				<th data-options="field:'cusId',width:60">客户编号</th>
				<th data-options="field:'cusName',width:120">客户姓名</th>
				<th data-options="field:'empName',width:120">负责员工</th>
				<th
					data-options="field:'consultStatu',width:180,
				formatter:function(value,row,index){
					if(row.consultStatu == 0){
						return '新增';
					}else if(row.consultStatu == 1){
						return '紧跟';
					}else if(row.consultStatu == 2){
						return '已报名';
					}else if(row.consultStatu == 3){
						return '死单';
					}else if(row.consultStatu == 4){
						return '报名后退费';
					}
				}">咨询状态</th>
				<th data-options="field:'consultDate',width:180,
				formatter:function(value,row,index){
					var unixTimestamp = new Date(value);  
                    return unixTimestamp.toLocaleString(); 
				}">咨询日期</th>
				<th data-options="field:'result',width:180">备注</th>
			</tr>
		</c:if>
		</thead>
	</table>
</body>
</html>
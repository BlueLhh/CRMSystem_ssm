<%@page import="com.hzu.crm.entity.Employee"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
    Employee employee=(Employee)session.getAttribute("employee");
    String url="";
    String job=employee.getJobInfo().getJob();
    if(job.equals("销售员")){
    	// 销售员
    	url="/cusInfo/allCusInfo.do?id="+employee.getId();
    }else{
    	// 咨询师/网络咨询
    	url="/record/allRecord.do?id="+employee.getId();
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
		/* 职位信息 */
		var job=$("#job").val();
		$('#tt').datagrid({
			pagination:true,//开启分页工具栏
			pageSize:10,//默认选中的每页显示条数
			pageList:[10,20,30],//设置可选择的每页条数
			toolbar : [{
				text : '修改确认',
				iconCls : 'icon-save',
				handler : function() {
					$('#tt').datagrid('endEdit', lastIndex);
					var rows = $('#tt').datagrid('getChanges');
					if (job=='咨询师' || job=='网络咨询') {
						for(var i=0;i<rows.length;i++){
							 $.post("<%=path %>/record/updateRecord.do", {
								"id" : rows[i].id,
								"cusId" : rows[i].cusId,
								"empName" : rows[i].empName,
								"consultStatu" : rows[i].consultStatu,
								"consultDate" : rows[i].consultDate,
								"result" : rows[i].result,
							});
						}
					}else{
						for(var i=0;i<rows.length;i++){
							 $.post("<%=path %>/cusInfo/updateInfo.do", {
								"id" : rows[i].id,
								"cusId" : rows[i].cusId,
								"empName" : rows[i].empName,
								"statu" : rows[i].statu,
								"lastFollowDate" : rows[i].lastFollowDate,
								"planDate" : rows[i].planDate,
								"mark" : rows[i].mark,
								"startDate" : rows[i].startDate,
							});
						}
					}
					$('#tt').datagrid('acceptChanges');
				}
			},"-",{
				text : '信息导出',
				iconCls : 'icon-folder',
				handler : function() {
					var rows = $("#tt").datagrid("getRows");
					var bodyData = JSON.stringify(rows); //转成json字符串
					if (job=='咨询师' || job=='网络咨询') {
						var postData = bodyData.replace(/id/g, "销售单编号")
						.replace(/cusId/g, "客户编号").replace(
								/cusName/g, "客户姓名").replace(
								/empName/g, "负责员工").replace(/consultStatu/g,
								"咨询状态").replace(/consultDate/g, "咨询日期").
								replace(/result/g, "备注");

							JSONToCSVConvertor(postData, "客户咨询信息表", true);
					}else{
						var postData = bodyData.replace(/id/g, "销售单编号")
								.replace(/cusId/g, "客户编号").replace(
										/cusName/g, "客户姓名").replace(
										/empName/g, "负责员工").replace(/statu/g,
										"跟单状态").replace(/lastFollowDate/g, "最近联系日期")
								.replace(/planDate/g, "计划联系日期").replace(
										/createDate/g, '创建日期').replace(
										/mark/g, "备注").replace(
												/startDate/g, "开始日期");

						JSONToCSVConvertor(postData, "客户销售单信息表", true);
					}
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
	
	function JSONToCSVConvertor(JSONData, ReportTitle, ShowLabel) {
		//If JSONData is not an object then JSON.parse will parse the JSON string in an Object  
		var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData)
				: JSONData;

		var CSV = '';
		//Set Report title in first row or line  

		CSV += ReportTitle + '\r\n\n';

		//This condition will generate the Label/Header  
		if (ShowLabel) {
			var row = "";

			//This loop will extract the label from 1st index of on array  
			for ( var index in arrData[0]) {

				//Now convert each value to string and comma-seprated  
				row += index + ',';
			}

			row = row.slice(0, -1);

			//append Label row with line break  
			CSV += row + '\r\n';
		}

		//1st loop is to extract each row  
		for (var i = 0; i < arrData.length; i++) {
			var row = "";

			//2nd loop will extract each column and convert it in string comma-seprated  
			for ( var index in arrData[i]) {
				row += '"' + arrData[i][index] + '",';
			}

			row.slice(0, row.length - 1);

			//add a line break after each row  
			CSV += row + '\r\n';
		}

		if (CSV == '') {
			alert("Invalid data");
			return;
		}

		//Generate a file name  
		var fileName = "MyReport_";
		//this will remove the blank-spaces from the title and replace it with an underscore  
		fileName += ReportTitle.replace(/ /g, "_");

		//Initialize file format you want csv or xls  
		//var uri = 'data:text/csv;charset=utf-8,' + escape(CSV);
		var uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURI(CSV);

		// Now the little tricky part.  
		// you can use either>> window.open(uri);  
		// but this will not work in some browsers  
		// or you will not get the correct file extension      

		//this trick will generate a temp <a /> tag  
		var link = document.createElement("a");
		link.href = uri;

		//set the visibility hidden so it will not effect on your web-layout  
		link.style = "visibility:hidden";
		link.download = fileName + ".csv";

		//this part will append the anchor tag and remove it after automatic click  
		document.body.appendChild(link);
		link.click();
		document.body.removeChild(link);
	}
</script>
</head>
<body>
	<h2>客户开发信息</h2>
	<!-- 设置隐藏域保存员工职位权限 -->
	<input type="hidden" value="${sessionScope.job}" id="job">
	<table id="tt"
		data-options="iconCls:'icon-edit',singleSelect:true,idField:'cusId',url:'<%=path + url%>'">
		<thead>
		<c:if test="${sessionScope.job=='销售员' }">
			<tr>
				<th data-options="field:'id',width:80">销售单编号</th>
				<th data-options="field:'cusId',width:60">客户编号</th>
				<th data-options="field:'cusName',width:120">客户姓名</th>
				<th data-options="field:'empName',width:120">负责员工</th>
				<th
					data-options="field:'statu',width:180,editor:'text',
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
				<th data-options="field:'lastFollowDate',width:180,editor:'datebox',
				formatter:function(value,row,index){
					var unixTimestamp = new Date(value);  
                    return unixTimestamp.toLocaleString(); 
				}">最近联系日期</th>
				<th data-options="field:'planDate',width:180,editor:'datebox',
				formatter:function(value,row,index){
					var unixTimestamp = new Date(value);  
                    return unixTimestamp.toLocaleString(); 
				}">计划联系日期</th>
				<th data-options="field:'mark',width:180,editor:'text'">备注</th>
				<th data-options="field:'startDate',width:180,
				formatter:function(value,row,index){
					var unixTimestamp = new Date(value);  
                    return unixTimestamp.toLocaleString(); 
				}">开始日期</th>
			</tr>
		</c:if>
		<c:if test="${sessionScope.job=='咨询师' || sessionScope.job=='网络咨询'}">
			<tr>
				<th data-options="field:'id',width:80">咨询单编号</th>
				<th data-options="field:'cusId',width:60">客户编号</th>
				<th data-options="field:'cusName',width:120">客户姓名</th>
				<th data-options="field:'empName',width:120">负责员工</th>
				<th
					data-options="field:'consultStatu',width:180,editor:'text',
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
				<th data-options="field:'consultDate',width:180,editor:'datebox',
				formatter:function(value,row,index){
					var unixTimestamp = new Date(value);  
                    return unixTimestamp.toLocaleString(); 
				}">咨询日期</th>
				<th data-options="field:'result',width:180,editor:'text'">备注</th>
			</tr>
		</c:if>
		</thead>
	</table>
</body>
</html>
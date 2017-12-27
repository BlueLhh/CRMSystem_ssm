<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.Date" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
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
var checkedCus=null;
var checkedUsers=null;
var conditions = [
    		    {conditionId:'0',name:'新增未上门'},
    		    {conditionId:'1',name:'新增已上门'},
    		    {conditionId:'2',name:'销售跟进中'},
    		    {conditionId:'3',name:'咨询跟进中'},
    		    {conditionId:'4',name:'死单'},
    		    {conditionId:'5',name:'已报名'}
    		];
    		function conditionFormatter(value){
    			for(var i=0; i<conditions.length; i++){
    				if (conditions[i].conditionId == value) return conditions[i].name;
    			}
    			return value;
    		}
	$(function() {
		var lastIndex;
		$('#tt').datagrid({
			url:'<%=path%>/customer/searchCusInfo.do',
			queryParams:{
				"begintime":$("#begintime").datebox('getValue'),
				"cname":$("#cname").val(),
				"cphone":$("#cphone").val()
			},
			selectOnCheck:true,
			checkOnSelect:false,
			toolbar : [ {
				text : '单个添加',
				iconCls : 'icon-add',
				handler : function() {
					$("#addcus").click();
				}
			}, '-',{
				text : '批量导入',
				iconCls : 'icon-print',
				handler : function() {
					$('#dlg').dialog('open');
				}
			},'-', {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					var rows = $('#tt').datagrid('getChecked');
					if (rows.length!=0) {
						for(var i=0;i<rows.length;i++){
							$.post("<%=path%>/customer/deleteId.do",{
								"id":rows[i].customerId
						     });
							var index = $('#tt').datagrid('getRowIndex', rows[i]);
							$('#tt').datagrid('deleteRow', index);
						}
						
					}
				}
			}, '-', {
				text : '修改确认',
				iconCls : 'icon-save',
				handler : function() {
						$('#tt').datagrid('endEdit', lastIndex);
					var rows = $('#tt').datagrid('getChanges');
				//	var row=rows[0];
					 for(var i=0;i<rows.length;i++){
						 $.post("<%=path%>/customer/updateById.do", {
							"customerId" : rows[i].customerId,
							"customerName" : rows[i].customerName,
							"customerSex" : rows[i].customerSex,
							"customerMobile" : rows[i].customerMobile,
							"customerTel" : rows[i].customerTel,
							"customerCompany" : rows[i].customerCompany
						});
					}
					$('#tt').datagrid('acceptChanges');
				}
			},'-', {
				text : '分发任务',
				iconCls : 'icon-edit',
				handler : function() {
					checkedCus = $('#tt').datagrid('getChecked');
					$('#window').window('open');
				
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
		$('#tt1').datagrid({
			url:'<%=path %>/user/allTmk.do',
			queryParams:{
				"rid":4,
			},
			selectOnCheck:true,
			checkOnSelect:false,
			toolbar : [{
				text : '分发',
				iconCls : 'icon-edit',
				handler : function() {
					$('#window').window('open');
					if(checkedCus!=null){
						checkedUsers = $('#tt1').datagrid('getChecked');
						comfirmps("把"+checkedCus.length+"名客户分配给"+checkedUsers.length);
					}
					
				}
			} ],
		});
	});
	function search(){
		$('#tt').datagrid('load', {
			"begintime":$("#begintime").datebox('getValue'),
			"cname":$("#cname").val(),
			"cphone":$("#cphone").val()
		});
	}
	function comfirmps(text){
		$.messager.confirm('提示',text, function(r){
			if (r){
				var cusids="";
				var userids="";
				for(var i=0;i<checkedCus.length;i++){
					cusids+=checkedCus[i].customerId+"-";
				}
				for(var i=0;i<checkedUsers.length;i++){
					userids+=checkedUsers[i].userId+"-";
				}
				$.post("<%=path %>
	/customer/pemission.do", {
					"cusids" : cusids,
					"userids" : userids
				});
			}
		});
	}
</script>
</head>
<body>
	<h2>客户信息表</h2>
	<a href="<%=path%>/customer/cusEdit.do" target="_self"><span
		id='addcus'></span></a>
	<table id="tt"
		data-options="iconCls:'icon-edit',singleSelect:false,rownumbers:true,idField:'customerId',pagination:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'customerId',width:60">客户编号</th>
				<!-- formatter:指定格式化的方法
				options-valueField：参数
				options-data：数据源 ，删掉也没事-->
				<th data-options="field:'customerName',width:60, editor:'text'">客户名字</th>
				<th data-options="field:'customerSex',width:60, editor:'text'">客户性别</th>
				<th data-options="field:'customerMobile',width:80, editor:'text'">客户手机号</th>
				<th data-options="field:'customerAddtime',width:200, editor:'text'">添加时间</th>
				<th
					data-options="field:'conditionId',width:120,formatter:conditionFormatter,editor:{
							type:'combobox',
							options:{
								valueField:'conditionId',
								textField:'name',
								data:conditions,
								required:true
							}
						}">客户状态</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog" title="Basic Dialog"
		data-options="iconCls:'icon-save'"
		style="width: 300px; height: 200px; padding: 10px;">
		<div style="margin-bottom: 20px">
			<form action="<%=path%>/customer/importCusByExl.do" method="post"
				enctype="multipart/form-data">
				<input type="file" name="excelUpload" style="width: 80%"> <br>
				<button type="submit">导入数据</button>
			</form>
		</div>
	</div>
	<div id="window" class="easyui-window" title="Basic Dialog"
		data-options="iconCls:'icon-save'"
		style="width: 300px; padding: 10px;">
		<table id="tt1" class="easyui-datagrid"
			data-options="iconCls:'icon-edit',singleSelect:false,rownumbers:true,idField:'customerId',pagination:true">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'userId',width:60">员工编号</th>
					<th data-options="field:'userName',width:60">员工姓名</th>
				</tr>
			</thead>
		</table>
	</div>
	<script>
		$(function() {
			$('#dlg').dialog('close');
			$('#window').window('close');
			$('#search').bind('click', function() {
				var begintime = $("#begintime").datebox('getValue');
				var cname = $("#cname").val();
				var cphone = $("#cphone").val();
				$("#searchform").submit();
			});
		});
	</script>
	<div id="tb" style="padding: 5px; height: auto">
		<div>
			导入日期: <input class="easyui-datebox" style="width: 80px"
				id="begintime" name="begintime"> 客户姓名: <input
				class="easyui-textbox" style="width: 80px" id="cname" name="cname">

			客户手机: <input class="easyui-textbox" style="width: 150px" id="cphone"
				name="cphone">

			<button id="search" class="easyui-linkbutton" iconCls="icon-search"
				onclick="javascript:search()">查询</button>
		</div>
	</div>
</body>
</html>
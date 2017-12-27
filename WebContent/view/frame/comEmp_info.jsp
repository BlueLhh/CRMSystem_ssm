<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="<%=path%>/view/frame/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/view/frame/js/jquery.easyui.min.js"></script>
<script>
	
	$(function() {
		var lastIndex;
		$('#tt').datagrid({
			pagination:true,//开启分页工具栏
			pageSize:5,//默认选中的每页显示条数
			pageList:[3,5,8],//设置可选择的每页条数
			toolbar : [{
				text : '删除员工',
				iconCls : 'icon-remove',
				handler : function() {
					var row = $('#tt').datagrid('getSelected');
					if (row) {
						/* 根据id删除普通用户 */
						location.href="<%=path %>/emp/delEmp.do?id="+row.id;
					}
				}
				
			},'-', {
				text : '重置密码',
				iconCls : 'icon-save',
				handler : function() {
					var row = $('#tt').datagrid('getSelected');
					if (row) {
						/*重置选中的用户的密码，修改为初始密码*/
						location.href="<%=path %>/emp/rePass.do?id="+row.id;
					}
				}
			}],
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
	<h2>普通员工信息表</h2>
	<table id="tt" data-options="iconCls:'icon-edit',singleSelect:true,idField:'empId',url:'<%=path %>/emp/comEmpInfo.do'">
		<thead>
			<tr>
				<th data-options="field:'id',width:60">员工编号</th>
				<th data-options="field:'username',width:100">员工姓名</th>
				<th data-options="field:'nickname',width:100">员工昵称</th>
				<th data-options="field:'realname',width:100">真实姓名</th>
				<th data-options="field:'pass',width:180">员工密码</th>
				<th data-options="field:'phoneNo',width:180">联系电话</th>
				<th data-options="field:'officeTel',width:180">办公电话</th>
				<th data-options="field:'workstatu',width:80,
				formatter:function(value,row,index){
					if(row.workstatu == 1){
						return '在职';
					}else{
						return '离职';
					}
				}">在职状态</th>
				<th data-options="field:'jobInfo.job',width:150,
				formatter:function(value,row,index){
					if(row.jobInfo){
						return row.jobInfo.job;
					}else{
						return value;
					}
				}
				">职位信息</th>
				<th data-options="field:'dept.dname',width:180,
				formatter:function(value,row,index){
					if(row.dept){
						return row.dept.dname;
					}else{
						return value;
					}
				}">部门信息</th>
			</tr>
		</thead>
	</table>
</body>
</html>
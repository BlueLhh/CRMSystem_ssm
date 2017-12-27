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
			pageSize:10,//默认选中的每页显示条数
			pageList:[10,20,30],//设置可选择的每页条数
			toolbar : [{
				text : '删除权限',
				iconCls : 'icon-remove',
				handler : function() {
					var row = $('#tt').datagrid('getSelected');
					if (row) {
						/* 根据id删除权限 */
						location.href="<%=path %>/rig/delRig.do?rid="+row.rid;
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
	<h2>全部权限</h2>
	<table id="tt" data-options="iconCls:'icon-edit',singleSelect:true,idField:'rigId',url:'<%=path %>/rig/allRig.do'">
		<thead>
			<tr>
				<th data-options="field:'rid',width:60" id="rigId">权限编号</th>
				<th data-options="field:'rightName',width:200">权限名称</th>
				<th data-options="field:'rightType',width:150,
				formatter:function(value,row,index){
					if(row.rightType == 1){
						return '一级权限';
					}else{
						return '二级权限';
					}
				}">权限类别</th>
				<th data-options="field:'url',width:150">选项卡url</th>
			</tr>
		</thead>
	</table>
</body>
</html>
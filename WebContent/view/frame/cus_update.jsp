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
				text : '修改确认',
				iconCls : 'icon-save',
				handler : function() {
					$('#tt').datagrid('endEdit', lastIndex);
					var rows = $('#tt').datagrid('getChanges');
					for(var i=0;i<rows.length;i++){
						 $.post("<%=path %>/cus/updateCus.do",{
							 	"id":rows[i].id,
								"name":rows[i].name,
								"education":rows[i].education,
								"phoneNo":rows[i].phoneNo,
								"qq":rows[i].qq,
								"email":rows[i].email,
								"customStatu":rows[i].customStatu,
								"createDate":rows[i].createDate,
								"inviteName":rows[i].inviteName
							});
					} 
					$('#tt').datagrid('acceptChanges');
				}
			},'-', {
				text : '分配客户',
				iconCls : 'icon-remove',
				handler : function() {
					var row = $('#tt').datagrid('getSelected');
					if (row) {
						location.href="<%=path %>/cus/allotCus.do?id="+row.id;
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
	<h2>客户信息修改</h2>
	<table id="tt" data-options="iconCls:'icon-edit',singleSelect:true,idField:'cusId',url:'<%=path %>/cus/allCus.do'">
		<thead>
			<tr>
				<th data-options="field:'id',width:60">客户编号</th>
				<th data-options="field:'name',width:60,editor:'text'">客户姓名</th>
				<th data-options="field:'education',width:80,editor:'text'">教育水平</th>
				<th data-options="field:'phoneNo',width:80,editor:'text'">电话号码</th>
				<th data-options="field:'qq',width:100,editor:'text'">QQ号码</th>
				<th data-options="field:'email',width:180,editor:'text'">电子邮箱</th>
				<th data-options="field:'customStatu',width:180,editor:'text',
				formatter:function(value,row,index){
					if(row.customStatu == 0){
						return '新增未上门';
					}else if(row.customStatu == 1){
						return '新增已上门';
					}else if(row.customStatu == 2){
						return '销售跟进中';
					}else if(row.customStatu == 3){
						return '咨询跟进中';
					}else if(row.customStatu == 4){
						return '死单';
					}else{
						return '已报名';
					}
				}">客户状态</th>
				<th data-options="field:'inviteName',width:180,editor:'text'">邀请人姓名</th>
				<th data-options="field:'createDate',width:180,
				formatter:function(value,row,index){
					  var unixTimestamp = new Date(value);  
                      return unixTimestamp.toLocaleString(); 
				}">创建日期</th>
			</tr>
		</thead>
	</table>
</body>
</html>
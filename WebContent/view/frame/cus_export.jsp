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
<script type="text/javascript"
	src="<%=path%>/view/frame/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/view/frame/js/jquery.easyui.min.js"></script>
<script>
	$(function() {
		var lastIndex;
		var job = $("#job").val();
		$('#tt').datagrid(
				{
					pagination : true,//开启分页工具栏
					pageSize : 10,//默认选中的每页显示条数
					pageList : [ 10, 20, 30 ],//设置可选择的每页条数
					toolbar : [ {
						text : '信息导出',
						iconCls : 'icon-folder',
						handler : function() {
							var rows = $("#tt").datagrid("getRows");
							var bodyData = JSON.stringify(rows); //转成json字符串
							//替换中文标题
							var postData = bodyData.replace(/id/g, "客户编号")
									.replace(/name/g, "客户姓名").replace(
											/education/g, "教育水平").replace(
											/phoneNo/g, "电话号码").replace(/qq/g,
											"QQ号码").replace(/email/g, "电子邮箱")
									.replace(/customStatu/g, "客户状态").replace(
											/createDate/g, '创建日期').replace(
											/inviteName/g, "邀请人姓名");

							JSONToCSVConvertor(postData, "客户导出信息表", true);
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
	<h2>客户信息表</h2>
	<table id="tt"
		data-options="iconCls:'icon-edit',singleSelect:true,idField:'cusId',url:'<%=path%>/cus/allCus.do'">
		<thead>
			<tr>
				<th data-options="field:'id',width:60">客户编号</th>
				<th data-options="field:'name',width:60">客户姓名</th>
				<th data-options="field:'education',width:80">教育水平</th>
				<th data-options="field:'phoneNo',width:80">电话号码</th>
				<th data-options="field:'qq',width:100">QQ号码</th>
				<th data-options="field:'email',width:180">电子邮箱</th>
				<th
					data-options="field:'customStatu',width:180,
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
				<th data-options="field:'createDate',width:180,
				formatter:function(value,row,index){
					  var unixTimestamp = new Date(value);  
                      return unixTimestamp.toLocaleString(); 
				}">创建日期</th>
				<th data-options="field:'inviteName',width:180">邀请人姓名</th>
			</tr>
		</thead>
	</table>
</body>
</html>
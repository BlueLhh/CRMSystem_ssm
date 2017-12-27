function rigChange() {
	// 获取选中的职位id
	var jobId = $("#jobId option:checked").val();
	// 获取选中的职位名称
	var jobName = $("#jobId option:checked").text();
	// 获取权限下拉框
	var rigSelect = $("#rigSelect");
	var url = "job/jobRig.do";
	var param = "jobId=" + jobId;
	$.post(url, param, function(data) {
		// 清空下拉框
		rigSelect.empty();
		/* data则为后台返回的权限名称集合 */
		$.each(data, function(index, rights) {
			var option = $("<option>" + rights.rightName + "</option>");
			rigSelect.append(option);
		})
	});
	// 弹出框上的职位名称input控件
	var job=$("#jobName");
	job.val(jobName);
}

// 判断职位名称是否为空
function jobIsNull() {
	var job = $("#job").val();
	if (job == "" || job == null) {
		alert("职位名称不能为空!");
		return false;
	}
}

//判断权限是否为空
function rigIsNull() {
	var updateRig = $("#updateRig").val();
	if (updateRig == "" || updateRig == null) {
		alert("要修改的权限不能为空!");
		return false;
	}
}

//查询部门对应的职业
function deptChange(){
	// 获取选中的部门id
	var deptId = $("#deptId option:checked").val();
	// 获取职位下拉框 jobName
	var jobSelect = $("#jobSelect");
	var url = "job/jobName.do";
	var param = "deptId=" + deptId;
	$.post(url, param, function(data) {
		// 清空下拉框
		jobSelect.empty();
		/* data则为后台返回的权限名称集合 */
		$.each(data, function(index, jobInfo) {
			var option = $("<option>" + jobInfo.job + "</option>");
			jobSelect.append(option);
		})
	});
}

//查询所有二级权限
function allSecondRig() {
	var rigLevel=$("#rigLevel option:checked").text();
	// 获取隶属于权限下拉框
	var rigSelect = $("#allRig");
	if (rigLevel=="二级权限") {
		// 如果选中二级权限，则遍历数据库获取一级权限
		var url = "rig/firstRig.do";
		var param = "jobId=" + jobId;
		$.post(url, param, function(data) {
			// 清空下拉框
			rigSelect.empty();
			/* data则为后台返回的权限名称集合 */
			$.each(data, function(index, rights) {
				var option = $("<option>" + rights.rightName + "</option>");
				rigSelect.append(option);
			})
		});
	}else{
		// 清空下拉框
		rigSelect.empty();
	}
}

//确定添加，为某种职位，添加某种权限 
function ensure() {
	// 获取选中的职位id
	var jobId = $("#jobId option:checked").val();
	// 获取权限名称
	var rigName=$("#rigName").val();
	// 获取url
	var rigUrl=$("#rigUrl").val();
	// 获取权限级别
	var rigLevel=$("#rigLevel option:checked").text();
	// 获取隶属权限
	var rigSelect = $("#allRig option:checked").text();
	
	location.href="jobRig/addJR.do?jobId="+jobId+"&rigName="+
	     rigName+"&rigUrl="+rigUrl+"&rigLevel="+rigLevel+"&rigSelect="+rigSelect;
}



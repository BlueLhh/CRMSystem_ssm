<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@page import="com.sxxy.po.UserInfo"%> --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'left.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px;
	color: #FFFFFF;
}

.STYLE3 {
	font-size: 12px;
	color: #033d61;
}
-->
</style>
<style type="text/css">
.menu_title SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #ffffff;
	POSITION: relative;
	TOP: 2px
}

.menu_title2 SPAN {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #FFCC00;
	POSITION: relative;
	TOP: 2px
}

.style1 {
	font-size: 12px;
}

a {
	text-decoration: none;
	color: #033d61;
}
}
</style>
<script>
var he=document.body.clientHeight-105;
document.write("<div id=tt style=height:"+he+";overflow:hidden>")
</script>

</head>
<body>
	<%-- <%
						UserInfo  userInfo  =   (UserInfo)request.getSession().getAttribute("userInfo2");
			 %> --%>
	<table width="165" height="100%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="28" background="<%=basePath%>resource/images/main_40.gif">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="19%">&nbsp;</td>
						<td width="81%" height="20"><span class="STYLE1">管理菜单</span>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td valign="top">
				<table width="151" border="0" align="center" cellpadding="0"
					cellspacing="0">
				<!-- 权限管理 -->
				<c:if test="${sessionScope.job=='超级管理员' }">
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="23"
										background="<%=basePath%>resource/images/main_47.gif"
										id="imgmenu1" class="menu_title"
										onMouseOver="this.className='menu_title2';"
										onClick="showsubmenu(1)"
										onMouseOut="this.className='menu_title';" style="cursor: hand">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="18%">&nbsp;</td>
												<td width="82%" class="STYLE1">权限管理</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="<%=basePath%>resource/images/main_51.gif"
										id="submenu1">
										<div class="sec_menu">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
														<table width="90%" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="16%" height="25">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td width="84%" height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"><a
																					href="<%=basePath%>/view/frame/job_add.jsp"
																					target="mainFrame">增加职位</a> </span></td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"> <a
																					href="<%=basePath%>/job/allJob.do"
																					target="mainFrame">修改职位权限</a>
																			</span></td>
																		</tr>
																	</table>
																</td>
															</tr>

															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"><a
																					href="<%=basePath%>/view/frame/rights_del.jsp"
																					target="mainFrame">删除权限</a> </span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td height="5"><img
														src="<%=basePath%>resource/images/main_52.gif" width="151"
														height="5" /></td>
												</tr>
											</table>
										</div>
									</td>
								</tr>

							</table>
						</td>
					</tr>
				</c:if>  <!-- 判断权限结束 -->
				
				<c:if test="${(sessionScope.job=='超级管理员') || (sessionScope.job=='管理员') || (sessionScope.job=='销售助理')}">
					<!-- 账号管理 -->
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="23"
										background="<%=basePath%>resource/images/main_47.gif"
										id="imgmenu3" class="menu_title"
										onmouseover="this.className='menu_title2';"
										onclick="showsubmenu(3)"
										onmouseout="this.className='menu_title';" style="cursor: hand">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="18%">&nbsp;</td>
												<td width="82%" class="STYLE1">账号管理</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="<%=basePath%>resource/images/main_51.gif"
										id="submenu3">
										<div class="sec_menu">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
														<table width="90%" border="0" align="center"
															cellpadding="0" cellspacing="0">
														<!-- 管理员权限 -->
														<c:if test="${sessionScope.job=='超级管理员'}">
															<tr>
																<td width="16%" height="25">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td width="84%" height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"><a
																					href="<%=basePath%>/view/frame/ordadmin_add.jsp"
																					target="mainFrame">添加管理员账号</a> </span></td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"> <a
																					href="<%=basePath%>/view/frame/ordadmin_del.jsp"
																					target="mainFrame">删除管理员账号</a>
																			</span></td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"> <a
																					href="<%=basePath%>/view/frame/emp_info.jsp"
																					target="mainFrame">查看所有员工</a>
																			</span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</c:if>
															<!-- 账号管理：在这以上为超级管理员权限 -->
															<!-- 账号管理：在这以下为管理员权限  -->
														<c:if test="${sessionScope.job=='管理员'}">
															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"> <a
																					href="<%=basePath%>/view/frame/emp_add.jsp"
																					target="mainFrame">添加普通用户账号</a>
																			</span></td>
																		</tr>
																	</table>
																</td>
															</tr>

															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"> <a
																					href="<%=basePath%>/view/frame/comEmp_info.jsp"
																					target="mainFrame">删除普通用户账号</a>
																			</span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</c:if>
															<!-- 账号管理：在这以上为管理员权限  -->
															<!-- 账号管理：在这以下为销售助理权限  -->
														<c:if test="${sessionScope.job=='销售助理'}">
														
															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"> <a
																					href="<%=basePath%>/view/frame/saleEmp_info.jsp"
																					target="mainFrame">查询所有销售员工</a>
																			</span></td>
																		</tr>
																	</table>
																</td>
															</tr>
															
															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"> <a
																					href="<%=basePath%>/view/frame/conEmp_info.jsp"
																					target="mainFrame">查询所有咨询员工</a>
																			</span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</c:if>
														<!-- 销售助理权限结束 -->
														</table>
													</td>
												</tr>

												<tr>
													<td height="5"><img
														src="<%=basePath%>resource/images/main_52.gif" width="151"
														height="5" /></td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</c:if>	<!-- 账号管理权限结束 -->
				
				<c:if test="${sessionScope.job=='超级管理员' || sessionScope.job=='销售助理'}">
					<!-- 报表管理 -->
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="23"
										background="<%=basePath%>resource/images/main_47.gif"
										id="imgmenu2" class="menu_title"
										onMouseOver="this.className='menu_title2';"
										onClick="showsubmenu(2)"
										onMouseOut="this.className='menu_title';" style="cursor: hand">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="18%">&nbsp;</td>
												<td width="82%" class="STYLE1">报表管理</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="<%=basePath%>resource/images/main_51.gif"
										id="submenu2">
										<div class="sec_menu">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
														<table width="90%" border="0" align="center"
															cellpadding="0" cellspacing="0">
														<c:if test="${sessionScope.job=='超级管理员'}">
															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"> <a
																					href="<%=basePath%>/view/frame/cus_info.jsp"
																					target="mainFrame">查询所有客户</a>
																			</span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</c:if>
															<!-- 报表管理：在这以上为超级管理员权限 -->
															<!-- 报表管理：在这以下为销售助理权限 -->
														<c:if test="${sessionScope.job=='销售助理' }">
															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"><a
																					href="<%=basePath%>"
																					target="mainFrame">客户信息导入</a> </span></td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"><a
																					href="<%=basePath%>/view/frame/cus_export.jsp"
																					target="mainFrame">客户信息导出</a> </span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</c:if>
														</table>
													</td>
												</tr>
												<tr>
													<td height="5"><img
														src="<%=basePath%>resource/images/main_52.gif" width="151"
														height="5" /></td>
												</tr>
											</table>
										</div>
									</td>
								</tr>

							</table>
						</td>
					</tr>
				</c:if> <!-- 报表管理结束 -->
				
					<!-- 客户资料 -->
				<c:if test="${sessionScope.job=='销售助理' }">
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="23"
										background="<%=basePath%>resource/images/main_47.gif"
										id="imgmenu4" class="menu_title"
										onmouseover="this.className='menu_title2';"
										onclick="showsubmenu(4)"
										onmouseout="this.className='menu_title';" style="cursor: hand">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="18%">&nbsp;</td>
												<td width="82%" class="STYLE1">客户资料</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="<%=basePath%>resource/images/main_51.gif"
										id="submenu2">
										<div class="sec_menu">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
														<table width="90%" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="16%" height="25">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td width="84%" height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"><a
																					href="<%=basePath%>view/frame/cus_add.jsp"
																					target="mainFrame">添加客户信息</a> </span></td>
																		</tr>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"> <a
																					href="<%=basePath%>view/frame/cus_update.jsp"
																					target="mainFrame">修改客户信息</a>
																			</span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td height="5"><img
														src="<%=basePath%>resource/images/main_52.gif" width="151"
														height="5" /></td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</c:if>	<!-- 客户资料权限结束 -->
				<!-- 客户开发 -->
				<c:if test="${sessionScope.job=='网络咨询' || sessionScope.job=='销售员' || sessionScope.job=='销售主管' ||sessionScope.job=='网络咨询主管'}">
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="23"
										background="<%=basePath%>resource/images/main_47.gif"
										id="imgmenu2" class="menu_title"
										onMouseOver="this.className='menu_title2';"
										onClick="showsubmenu(2)"
										onMouseOut="this.className='menu_title';" style="cursor: hand">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="18%">&nbsp;</td>
												<td width="82%" class="STYLE1">客户开发</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="<%=basePath%>resource/images/main_51.gif"
										id="submenu2">
										<div class="sec_menu">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
														<table width="90%" border="0" align="center"
															cellpadding="0" cellspacing="0">
														<c:if test="${sessionScope.job=='销售员' || sessionScope.job=='网络咨询'}">
															<tr>
																<td width="16%" height="25">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td width="84%" height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"><a
																					href="<%=basePath%>view/frame/cus_infoAndRecord.jsp"
																					target="mainFrame">查看客户开发信息</a> </span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</c:if>
															<!-- 客户开发：在这以上为销售员、网络咨询权限 -->
															<!-- 客户开发：在这以下为销售主管、网络主管权限 -->
														<c:if test="${sessionScope.job=='销售主管' || sessionScope.job=='网络咨询主管'}">
															<tr>
																<td height="23">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"><a
																					href="<%=basePath%>view/frame/cus_allInfoAndRecord.jsp"
																					target="mainFrame">客户开发信息</a> </span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</c:if>
														</table>
													</td>
												</tr>
												<tr>
													<td height="5"><img
														src="<%=basePath%>resource/images/main_52.gif" width="151"
														height="5" /></td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</c:if> <!-- 客户开发结束 -->
				<!-- 客户咨询 -->
				<c:if test="${sessionScope.job=='咨询师'}">
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="23"
										background="<%=basePath%>resource/images/main_47.gif"
										id="imgmenu2" class="menu_title"
										onMouseOver="this.className='menu_title2';"
										onClick="showsubmenu(2)"
										onMouseOut="this.className='menu_title';" style="cursor: hand">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="18%">&nbsp;</td>
												<td width="82%" class="STYLE1">客户咨询</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="<%=basePath%>resource/images/main_51.gif"
										id="submenu2">
										<div class="sec_menu">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
														<table width="90%" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="16%" height="25">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td width="84%" height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"><a
																					href="<%=basePath%>view/frame/cus_infoAndRecord.jsp"
																					target="mainFrame">查看客户咨询信息</a> </span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td height="5"><img
														src="<%=basePath%>resource/images/main_52.gif" width="151"
														height="5" /></td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</c:if>  <!-- 客户咨询结束 -->
				<!-- 部门管理 -->
				<c:if test="${sessionScope.job=='销售主管' || sessionScope.job=='网络咨询主管' ||sessionScope.job=='咨询师主管'}">
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="23"
										background="<%=basePath%>resource/images/main_47.gif"
										id="imgmenu2" class="menu_title"
										onMouseOver="this.className='menu_title2';"
										onClick="showsubmenu(2)"
										onMouseOut="this.className='menu_title';" style="cursor: hand">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="18%">&nbsp;</td>
												<td width="82%" class="STYLE1">部门管理</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="<%=basePath%>resource/images/main_51.gif"
										id="submenu2">
										<div class="sec_menu">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
														<table width="90%" border="0" align="center"
															cellpadding="0" cellspacing="0">
															<tr>
																<td width="16%" height="25">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td width="84%" height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"><a
																					href="<%=basePath%>view/frame/emp_info.jsp"
																					target="mainFrame">查看部门员工信息</a> </span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td height="5"><img
														src="<%=basePath%>resource/images/main_52.gif" width="151"
														height="5" /></td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</c:if>  <!-- 部门管理结束 -->	
				<!-- 统计数据 -->
				<c:if test="${sessionScope.job!='超级管理员' && sessionScope.job!='管理员' && sessionScope.job!='销售助理'}">
					<tr>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td height="23"
										background="<%=basePath%>resource/images/main_47.gif"
										id="imgmenu2" class="menu_title"
										onMouseOver="this.className='menu_title2';"
										onClick="showsubmenu(2)"
										onMouseOut="this.className='menu_title';" style="cursor: hand">
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="18%">&nbsp;</td>
												<td width="82%" class="STYLE1">统计数据</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="<%=basePath%>resource/images/main_51.gif"
										id="submenu2">
										<div class="sec_menu">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td>
														<table width="90%" border="0" align="center"
															cellpadding="0" cellspacing="0">
														<c:if test="${sessionScope.job=='销售主管' || sessionScope.job=='网络咨询主管' || sessionScope.job=='咨询师主管'}">
															<tr>
																<td width="16%" height="25">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td width="84%" height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"><a
																					href="<%=basePath%>view/frame/cus_allSalerSta.jsp"
																					target="mainFrame">客户开发统计数据</a> </span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</c:if>
														<c:if test="${sessionScope.job=='销售员' || sessionScope.job=='网络咨询' || sessionScope.job=='咨询师'}">
															<tr>
																<td width="16%" height="25">
																	<div align="center">
																		<img src="<%=basePath%>resource/images/left.gif"
																			width="10" height="10" />
																	</div>
																</td>
																<td width="84%" height="23">
																	<table width="95%" border="0" cellspacing="0"
																		cellpadding="0">
																		<tr>
																			<td height="20" style="cursor: hand"
																				onmouseover="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#7bc4d3'; "
																				onmouseout="this.style.borderStyle='none'"><span
																				class="STYLE3"><a
																					href="<%=basePath%>view/frame/cus_salerSta.jsp"
																					target="mainFrame">客户开发统计数据</a> </span></td>
																		</tr>
																	</table>
																</td>
															</tr>
														</c:if>
														<!-- 统计数据：在这以上销售员/网络咨询/销售主管/网络咨询主管权限 -->
														</table>
													</td>
												</tr>
												<tr>
													<td height="5"><img
														src="<%=basePath%>resource/images/main_52.gif" width="151"
														height="5" /></td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</c:if>
				</table>
			</td>
		</tr>
		<tr>
			<td height="18" background="<%=basePath%>resource/images/main_58.gif">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="18" valign="bottom">
							<div align="center" class="STYLE3">版本：20013V1.0</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
<script>

function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
imgmenu = eval("imgmenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
imgmenu.background="<%=basePath%>../../resource/images/main_47.gif";
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
imgmenu.background="<%=basePath%>
	../../resource/images/main_48.gif";
		}
	}
</script>
</html>

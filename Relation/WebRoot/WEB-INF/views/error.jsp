<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>异常页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript">
$(function(){
	$("#error_tips").click(function(){
		$("#error_details").show();
	});
});
</script>

<style type="text/css">
#error_tips {
	text-decoration: underline;
	cursor: pointer;
}
#error_box {
	color: red;
	border: #c6c6c6 solid 1px;
	padding: 10px 10px;
	min-height: 200px;
}
</style>
</head>

<body>
	<div>
		<img src="./media/img/error.png">
		<span>后台出现异常，请联系管理员！<span id="error_tips">点击查看具体异常信息</span></span>
	</div>
	<div id="error_details" style="display: none;">
		<p>异常详细信息：</p>
		<p id="error_box">${ message }</p>
	</div>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
		System.out.println("basePath:"+basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'b.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="./js/fxdigital.js"></script>
		<style type="text/css">
		
		.topdiv {
			width: 100%;
			height: 850px;
			background-color: #EDEFF4;
			min-width: 850px;
			min-height: 900px;
			position: absolute;
		}
}
		</style>
		<script type="text/javascript">
	$(function() {
		 $.get("getserverdata",{"key":""},function(data){
					jump("数据还原",data);
				}, "text");
	});
	
</script>
	</head>
	<body bgcolor="#989CAC">
		<div class="topdiv">
			<div>
				&nbsp;
			</div>
			<div>
				&nbsp;
			</div>
			<div>
				&nbsp;
			</div>
			<div align="center">
				<p>
					<img src="media/img/wait.gif">
				</p>
				<p>
					加载数据中。。。
				</p>
			</div>
		</div>
	</body>
</html>

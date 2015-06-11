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
		body,div {
			margin: 0;
			border: 0;
			padding: 0;
		}
		.topdiv {
			background-color: #EDEFF4;
			position: relative;
		}
		.fieldsetStyle {
	width: 90%;
	height: 70%;
	margin-left: 5%;
}
		</style>
		<script type="text/javascript">
	$(function()  {
	$.ajax({
			 url:"getdownversion",
			 type:"POST",
			 data:{"Action" : "post"},
			 timeout:60000,
			 dataType:"text",
			 success:function(data){
			    jump("同步页面",data);
			  } ,
			  error:function(){
			  onloadfail();
			 }
			 });
	});
	
	function onloadfail(){
				 $.get("onloadfail",function(data){
					jump("链接错误",data);
				});
			}
</script>
	</head>
	<body  bgcolor="#989CAC">
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

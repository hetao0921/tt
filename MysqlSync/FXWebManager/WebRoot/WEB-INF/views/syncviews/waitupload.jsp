<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>My JSP 'b.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="waitupload">
		<script src="./js/fxdigital.js"></script>
		<script type="text/javascript">
			$(function() {
			$.ajax({
			  url:"getnativever",
			  type:"POST",
			  data:{"Action" : "post"},
			  timeout:60000,
			  dataType:"text",
			  success:function(data){
			    if (data == "-1") {
			      messageDialog("查询错误，请稍后再试");
			    } else {
			        showsyncnumber(data);
			    }
			  },
			  error:function(){
			 	 messageDialog("链接服务超时！请退出重新登录！");
			 	onloadfail();
			  }
			});
			});
			
			function showsyncnumber(data){
				 $.post("showsyncnumber",{"key":data},function(data){
					jump("同步页面",data);
				}, "text");
			}
			
			function onloadfail(){
				 $.get("onloadfail",function(data){
					jump("链接错误",data);
				});
			}
		</script>
	</head>

	<body bgcolor="#989CAC">
		<p>
			加载数据中。。。
		</p>
	</body>
</html>

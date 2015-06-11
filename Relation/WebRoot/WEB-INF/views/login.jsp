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

<title>后台管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="icon" href="./media/img/logo.png" />
<script src="./js/jquery-1.11.1.min.js"></script>

<script>
function check(){
	var username = $("#username").val();
	var pwd = $("#pwd").val();
	if(username == ""){
		$('#username').focus();
		$("#info").text("请输入用户名！");
		return;
	}
	if(pwd == ""){
		$('#pwd').focus();
		$("#info").text("请输入密码！");
		return;
	}
	$.post("checkLogin",
			{"username":$("#username").val(),"password":$("#pwd").val()},
			function(data){
				if(data == "true")
					window.location.href = "main";
				else
					$("#info").text("用户名或密码错误！");
			}
	);
}
$(function(){
	$("#b_login").click(function(){
		check();
	});
	$("#b_clear").click(function(){
		$("#username").val("");
		$("#pwd").val("");
	});
	
	$('#username').keypress(function(event){
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode == '13'){
			$("#b_login").click();
		}
	});
	$('#pwd').keypress(function(event){
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if(keycode == '13'){
			$("#b_login").click();
		}
	});
});
</script>

<style type="text/css">
#main {
	width:570px;
	height:252px;
	background: url('./media/img/login.png');
	position:absolute;left:50%;top:50%;
	margin-top:-126px;
	margin-left:-285px;
}
#logo {
	margin-left: 85px;
	margin-top: 70px;
}
#login {
	margin-left: 300px;
	margin-top: -100px;
	font-family:Arial;
	font-size:12px;
}
.but {
	border:0;
	margin-left:2px;
	width:66px;
	height:27px;
	background: url('./media/img/button_normal.png');
	cursor: pointer;
}
</style>
</head>

<body style="background-color: #5D6B88">
	<div id="main">
		<div>
			<img id="logo" src="./media/img/login_logo.png"/>
		</div>
		<table id="login">
			<tr>
				<td>用户名：</td>
				<td><input type="text" id="username" maxlength="12" style="width: 176px"></td>
			</tr>
			<tr height="5px"></tr>
			<tr>
				<td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
				<td><input type="password" id="pwd" maxlength="12" style="width: 176px"></td>
			</tr>
			<tr height="5px"></tr>
			<tr>
				<td></td>
				<td>
					<input type="button" id="b_login" class="but" value="登录">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" id="b_clear" class="but" value="清空">
				</td>
			</tr>
			<tr height="5px"></tr>
			<tr>
				<td></td>
				<td><span id="info" style="color: red;margin-left: 2px"></span></td>
			</tr>
		</table>
	</div>
</body>
</html>

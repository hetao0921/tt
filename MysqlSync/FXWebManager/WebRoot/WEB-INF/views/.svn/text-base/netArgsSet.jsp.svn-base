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

<title>本地信息修改</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="./js/fxdigital.js"></script>

<script type="text/javascript">
	function checkNull(){
		var ip=document.getElementById("ip").value;
		var gate=document.getElementById("gate").value;
		var mask=document.getElementById("mask").value;
		var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
		var reg = ip.match(exp);
		var gater= gate.match(exp);
		if(ip==""){
			messageDialog("IP不能为空！","alert");
		}else if(reg==null){
			messageDialog("输入的IP不合法！","alert");
		}else if(gate==""){
			messageDialog("网关不能为空！","alert");
		}else if(gater==null){
			messageDialog("输入的网关不合法！","alert");
		}else if(mask==""){
			messageDialog("子网掩码不能为空！","alert");
		}else{
			confirmDialog("修改IP后将重启，确定修改？",function(result){
				if(result){
					$.post("netArgsSetSubmit",
							{"ip":$('#ip').val(),
							"gate":$('#gate').val(),
							"mask":$('#mask').val(),},
							function(data){
						if("true".equals(data)){
							messageDialog("修改成功！","success");
						}else{
							messageDialog("修改失败！","fail");
						}
					});
				}
			});
		}
		
	}
	$(document).ready(function(){
		$("input[type=text]").focus(function() {
			$(this).parent().addClass("curFocus");
		});
		$("input[type=text]").blur(function() {
			$(this).parent().removeClass("curFocus");
		});
	});
</script>
<style type="text/css">
form {
	border: 1px solid black;
	margin: 10px 0;
	padding: 10px;
	position: relative;
}

.page-wrap {
	background: none repeat scroll 0 0 white;
	margin: 20px auto;
	padding: 20px;
	width: 300px;
	margin: 20px auto;
}

div.single-field {
	padding: 10px;
	position: relative;
}

label.beforeInput {
	display: block;
	float: left;
	font-size: 16px;
	width: 100px;
}

div.curFocus {
	background: none repeat scroll 0 0 #79c9ec;
}
</style>
</head>

<body bgcolor="#EEF4F8">
	<div class="page-wrap">
		<form id="form1" action="netArgsSet" method="post">
			<div class="single-field">
				<label class="beforeInput">IP地址：</label> <input type="text" id="ip"
					name="txtIp" value="${localServer.ip }">
			</div>
			<div class="single-field">
				<label class="beforeInput">网关：</label> <input type="text" id="gate"
					name="txtGate" value="${localServer.gate }">
			</div>
			<div class="single-field">
				<label class="beforeInput">子网掩码：</label> <input type="text"
					id="mask" name="txtMask" value="${localServer.mask }">
			</div>
			<div style="border: 0;margin: 20px auto;" align="center">
				<input type="button" value="保存" onclick="checkNull()"/>
			</div>
		</form>
	</div>
</body>
</html>

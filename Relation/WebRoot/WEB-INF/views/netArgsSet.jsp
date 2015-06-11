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
</script>
</head>

<body bgcolor="#EEF4F8">
	<div style="padding: 20px;">
		<span>网络参数设置：</span>
		<hr style="margin-top: 20px">
		<form id="form1" action="netArgsSet" method="post">
			<table style="font-size: 12px;margin-top: 20px;" cellpadding="10px" cellspacing="0">
				<tr>
					<td valign="middle">IP地址：</td>
					<td><input type="text" id="ip" name="txtIp" value="${localServer.ip }"></td>
				</tr>
				<tr>
					<td>网关：</td>
					<td><input type="text" id="gate" name="txtGate" value="${localServer.gate }"></td>
				</tr>
				<tr>
					<td>子网掩码：</td>
					<td><input type="text" id="mask" name="txtMask" value="${localServer.mask }"></td>
				</tr>
				<tr>
					<td></td>
					<td><input style="float: right;" type="button" value="保存" onclick="checkNull()"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

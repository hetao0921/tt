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

<title>软件授权信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="./js/fxdigital.js"></script>
<script type="text/javascript">
	$(function() {
		$.post("getSoftInfo",function(data){
			if(data == null || data == ""){
				messageDialog("获得授权信息异常！","fail");
			}else{
				var softInfo = eval("(" + data + ")");
				if(softInfo.deviceType == null){
					$("#deviceType").css("color","red");
					$("#deviceType").text("获取异常！");
				}else{
					$("#deviceType").text(softInfo.deviceType);
				}
				if(softInfo.softVersion == null){
					$("#softVersion").css("color","red");
					$("#softVersion").text("获取异常！");
				}else{
					$("#softVersion").text(softInfo.softVersion);
				}
				if(softInfo.licenseTime == null){
					$("#licenseTime").css("color","red");
					$("#licenseTime").text("获取异常！");
				}else{
					$("#licenseTime").text(softInfo.licenseTime);
				}
				if(softInfo.macAddress == null){
					$("#macAddress").show();
				}else{
					$("#macAddress").hide();
					var i = 0;
					for(var addr in softInfo.macAddress){
						i ++;
						$("<br><br>").appendTo($("#softinfo"));
						$("<label style=\"float: left;width: 120px\">MAC地址"+i+"：</label>").appendTo($("#softinfo"));
						$("<label>"+softInfo.macAddress[addr]+"</label>").appendTo($("#softinfo"));
					}
				}
			}
		});
	});
</script>

</head>
<body>
	<div id="softinfo">
		<br>
		<label style="float: left;width: 120px">设备型号：</label>
		<label id="deviceType"></label>
		<br><br>
		<label style="float: left;width: 120px">软件版本：</label>
		<label id="softVersion"></label>
		<br><br>
		<label style="float: left;width: 120px">授权日期：</label>
		<label id="licenseTime"></label>
		<div id="macAddress" style="display: none;">
			<br><br>
			<label style="float: left;width: 120px;">MAC地址：</label>
			<label style="color: red;">获取异常！</label>
		</div>
	</div>
</body>
</html>

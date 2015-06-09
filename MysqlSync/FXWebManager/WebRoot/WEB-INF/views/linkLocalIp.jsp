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

<title>级联设置本级IP设置</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script src="./js/fxdigital.js"></script>
<script src="./layer/layer.min.js"></script>

<script type="text/javascript">
$(function() {
	$.post("getLocalIP",function(data){
		if(data == null || data == ""){
			messageDialog("获取本级IP失败！","fail");
		}else{
			$("#localIP").val(data);
		}
	});
	
	$("#edit").click(function(){
		checkNull();
	});
	
	function edit(){
		$.post("updateLocalIP",{"localIP":$("#localIP").val()},function(data){
			if ("true" == data) {
				layer.msg('修改成功！', 1, -1);
			} else {
				messageDialog("修改失败！","fail");
			}
		});
	}
	
	function checkNull(){
		var ip=$("#localIP").val();
		var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
		var reg = ip.match(exp);
		if(ip==""){
			messageDialog("IP不能为空！","alert");
			return;
		}else if(reg==null){
			messageDialog("输入的IP不合法！","alert");
			return;
		}else{
			confirmDialog("确定要修改？",function(result){
				if(result){
					edit();
				}
			});
		}
	}
});
</script>

</head>

<body>
	<div align="center" style="padding: 20px;">
		<label>本级设备IP：</label><input id="localIP" name="localIP" type="text">
		<input id="edit" type="button" value="修改">
	</div>
</body>
</html>

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

<title>重启系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="./js/fxdigital.js"></script>
<script src="./layer/layer.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#reboot").click(function(){
			confirmDialog("确定要重启？",function(result){
				if(result){
					$.post("runReboot",function(data){
						if ("true" == data) {
							layer.msg('重启成功！', 1, -1);
						} else {
							messageDialog("重启失败！","fail");
						}
					});
				}
			});
		});
	});
</script>
</head>

<body>
	<div style="padding: 20px;">
		<span>重启系统（谨慎操作）：</span>
		<input id="reboot" type="button" value="重启">
	</div>
	<div style="padding-left: 20px;">
		<img src="./media/img/warn.png">
		<span>注意：重启系统后，将有一段时间无法访问运维平台！</span>
	</div>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'localIpSet.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="./js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">

	function checkNull() {
		var ip = document.getElementById("ip").value;
		var gate = document.getElementById("gate").value;
		var mask = document.getElementById("mask").value;
		var exp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
		var reg = ip.match(exp);
		var gater = gate.match(exp);
		if (ip == "") {
			alert("IP不能为空！");
		} else if (reg == null) {
			alert("输入的IP不合法！");
		} else if (gate == "") {
			alert("网关不能为空！");
		} else if (gater == null) {
			alert("输入的网关不合法！");
		} else if (mask == "") {
			alert("子网掩码不能为空！");
		} else {
			if(confirm("确定修改IP,修改成功后会重启？")){
				$.post("netArgsSetSubmit", {
						"ip" : $('#ip').val(),
						"gate" : $('#gate').val(),
						"mask" : $('#mask').val(),
					}, function(data) {
						if ("true"==data) {
							alert("修改成功！");
						} else {
							alert("修改失败！");
						}
					});
			}
		}
	}


    function setNetArgs(){

					$.post("netArgsSet", function(data) {
					var dataObj=eval("("+data+")");
					var dataType=dataObj.result;
					if(dataType=="error"){
					alert("获取服务器网络参数的方式不支持Windows系统！");
					}else{
					var ipFront=dataObj.ip;
					var maskFront=dataObj.mask;
					var gateFront=dataObj.gate;
				    document.getElementById("ip").value=ipFront;
		            document.getElementById("gate").value=gateFront;
		            document.getElementById("mask").value=maskFront;
					}
					});
				
    }


	$(function() {
		$('#refresh').click(function() {
			refresh();
		});
		$('#reboot').click(function() {
			if (!confirm("确认重启？")) {
				return;
			}
			$.post("reboot", function(data) {
				alert(data);
			});
		});
		$('#setMq')
				.click(
				
						function() {
						
							var ip = $('#mq_ip').val();
							var port = $('#mq_port').val();
							var autotime = $('#autotime').val();
							var exp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
							if (ip.match(exp) == null) {
								alert("IP地址不正确！");
								return;
							}
							if (port.match("^[0-9]*$") == null) {
								alert("端口号必须为数字！");
								return;
							}
							if (autotime.match("^[0-9]*$") == null
									|| parseInt(autotime) < 60) {
								alert("时间必须为数字！且最小值为60s");
								return;
							}
							$.post("setMq", {
								"mqIp" : ip,
								"mqPort" : port,
								"autotime" : autotime
							}, function(data) {
								alert(data);
								refresh();
							});
						});
		$('#apply')
				.click(
						function() {
							var ip = $('#apply_ip').val();
							var port = $('#apply_port').val();
							var exp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
							if (ip.match(exp) == null) {
								alert("IP地址不正确！");
								return;
							}
							if (port.match("^[0-9]*$") == null) {
								alert("端口号必须为数字！");
								return;
							}
							$.post("apply", {
								"ip" : ip,
								"port" : port
							}, function(data) {
								alert(data);
								refresh();
							});
						});

	});
	function refresh() {
		$('#form1').submit();
	}

	function cancel(mqIP) {
		if (!confirm("确认取消？")) {
			return;
		}
		$.post("cancel", {
			"ip" : mqIP
		}, function(data) {
			alert(data);
			refresh();
		});
	}
	function agree(serverID) {
		if (!confirm("确认通过？")) {
			return;
		}
		$.post("agree", {
			"id" : serverID
		}, function(data) {
			alert(data);
			refresh();
		});
	}
	function reject(serverID) {
		if (!confirm("确认拒绝？")) {
			return;
		}
		$.post("reject", {
			"id" : serverID
		}, function(data) {
			alert(data);
			refresh();
		});
	}
	function delete_sub(serverID) {
		if (!confirm("确认删除？")) {
			return;
		}
		$.post("delete", {
			"id" : serverID
		}, function(data) {
			alert(data);
			refresh();
		});
	}
</script>

  </head>
  
  <body>
   <form id="form1" action="" dir="ltr" method="post">

			<fieldset>
				<legend>
					<b>修改本级MQ服务器信息：</b>
				</legend>
				<label>IP：</label><input id="mq_ip" name="mq_ip" type="text"
					size="20px" value="${mqIp}"> &nbsp;&nbsp;&nbsp; <label>端口：</label><input
					id="mq_port" name="mq_port" type="text" size="10px"
					value="${mqPort}"> &nbsp;&nbsp;&nbsp; <label>自动同步时间：</label><input
					id="autotime" name="autotime" type="text" size="10px"
					value="${autotime}"> &nbsp;&nbsp;&nbsp; <input id="setMq"
					type="button" value="修改" style="width: 70px"> <input
					id="reboot" type="button" value="重启" style="width: 70px">
			</fieldset>
		</form>
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>Relation</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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

<body style="background:#C7EDCC;" onload="setNetArgs()">
	<div align="center">
				<fieldset style="width: 1110px;text-align: left">
			<legend>
				<b>修改本地IP地址信息：</b>
			</legend>
			<label class="beforeInput">IP地址：</label> <input type="text" id="ip"
				name="txtIp" value="${localServer.ip}"> &nbsp;&nbsp;&nbsp;
			<label class="beforeInput">网关：</label> <input type="text" id="gate"
				name="txtGate" value="${localServer.gate}">
			&nbsp;&nbsp;&nbsp; <label class="beforeInput">子网掩码：</label> <input
				type="text" id="mask" name="txtMask" value="${localServer.mask}">
			&nbsp;&nbsp;&nbsp; <input id="setMqInfo" type="button" value="保存"
				style="width: 70px" onclick="checkNull()" />
		</fieldset>
		<br>
		<form id="form1" action="" dir="ltr" method="post">

			<fieldset style="width: 1110px;text-align: left">
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
			<br>
			<fieldset style="width: 1110px;text-align: left">
				<legend>
					<b>申请上级（MQ）：</b>
				</legend>
				<label>IP：</label><input id="apply_ip" name="apply_ip" type="text"
					size="20px" value="${ip}"> &nbsp;&nbsp;&nbsp; <label>端口：</label><input
					id="apply_port" name="apply_port" type="text" size="10px"
					value="${port}"> &nbsp;&nbsp;&nbsp; <input id="apply"
					type="button" value="申请" style="width: 70px"> <input
					id="refresh" type="button" value="刷新" style="width: 70px">
			</fieldset>
			<br>
			<fieldset style="width: 1110px;text-align: left">
				<legend>
					<b>上级信息：</b>
				</legend>
				<table cellpadding="4" cellspacing="0" border="1" width="1100px">
					<thead align="center">
						<tr>
							<th colspan="2">MQ服务器</th>
							<th colspan="2">申请</th>
							<th colspan="3">同步服务器</th>
							<th width="16%" rowspan="2">操作</th>
						</tr>
						<tr>
							<th width="13%">IP</th>
							<th width="6%">端口</th>
							<th width="6%">状态</th>
							<th width="17%">时间</th>
							<th width="15%">ID</th>
							<th width="13%">IP</th>
							<th width="14%">名称</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${sups!=null}">
								<c:forEach items="${sups}" var="sups">
									<tr>
										<td>${sups.mqIP}</td>
										<td>${sups.mqPort}</td>
										<td>${sups.status}</td>
										<td>${sups.applyTime}</td>
										<td>${sups.serverID}</td>
										<td>${sups.serverIP}</td>
										<td>${sups.serverName}</td>
										<td><c:choose>
												<c:when test="${sups.status=='申请中' || sups.status=='已申请'}">
													<input type="button" value="撤销"
														onclick="cancel('${sups.mqIP}')">
												</c:when>
												<c:otherwise>
													<input type="button" value="撤销" disabled="disabled">
												</c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr align="center">
									<td colspan="8">无数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</fieldset>
			<br>
			<fieldset style="width: 1110px;text-align: left">
				<legend>
					<b>下级信息：</b>
				</legend>
				<table cellpadding="4" cellspacing="0" border="1" width="1100px">
					<thead align="center">
						<tr>
							<th colspan="2">MQ服务器</th>
							<th colspan="2">申请</th>
							<th colspan="3">同步服务器</th>
							<th width="16%" rowspan="2">操作</th>
						</tr>
						<tr>
							<th width="13%">IP</th>
							<th width="6%">端口</th>
							<th width="6%">状态</th>
							<th width="17%">时间</th>
							<th width="15%">ID</th>
							<th width="13%">IP</th>
							<th width="14%">名称</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${subs!=null}">
								<c:forEach items="${subs}" var="subs">
									<tr>
										<td>${subs.mqIP}</td>
										<td>${subs.mqPort}</td>
										<td>${subs.status}</td>
										<td>${subs.applyTime}</td>
										<td>${subs.serverID}</td>
										<td>${subs.serverIP}</td>
										<td>${subs.serverName}</td>
										<td><c:choose>
												<c:when test="${subs.status=='未审核'}">
													<input type="button" value="通过"
														onclick="agree('${subs.serverID}')">
													<input type="button" value="拒绝"
														onclick="reject('${subs.serverID}')">
													<input type="button" value="删除" disabled="disabled">
												</c:when>
												<c:when test="${subs.status=='已通过'}">
													<input type="button" value="通过" disabled="disabled">
													<input type="button" value="拒绝" disabled="disabled">
													<input type="button" value="删除"
														onclick="delete_sub('${subs.serverID}')">
												</c:when>
												<c:otherwise>
													<input type="button" value="通过" disabled="disabled">
													<input type="button" value="拒绝" disabled="disabled">
													<input type="button" value="删除" disabled="disabled">
												</c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr align="center">
									<td colspan="8">无数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</fieldset>
			<br>
			<fieldset style="width: 1110px;text-align: left">
				<legend>
					<b>本级客户端信息：</b>
				</legend>
				<table cellpadding="4" cellspacing="0" border="1" width="800px">
					<thead align="center">
						<tr>
							<th width="20%">ID</th>
							<th width="20%">IP</th>
							<th width="35%">通道名称</th>
							<th width="25%">注册时间</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${clients!=null}">
								<c:forEach items="${clients}" var="client">
									<tr>
										<td>${client.centerID}</td>
										<td>${client.centerIP}</td>
										<td>${client.channelName}</td>
										<td>${client.registerTime}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr align="center">
									<td colspan="4">无数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>

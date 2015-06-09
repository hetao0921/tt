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

<title>My JSP 'update.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="./js/fxdigital.js"></script>
<script type="text/javascript">
	$(document).ready(
					function() {
					//alert(1111);
						$.get("refresh",function(data) {
                                 //alert(data);
											var objs = eval(data);

											var tbl = document.getElementById('myTable');

											for ( var int = 0; int < objs.length; int++) {
												var newRow = tbl.insertRow(tbl.rows.length);

												newRow.insertCell(0).innerHTML = objs[int].realIp;
												newRow.insertCell(1).innerHTML = objs[int].VIP;
												newRow.insertCell(2).innerHTML = "<input type='button' class='ui-button ui-widget ui-state-default ui-corner-all' value='删除' onclick='delrealIpf(this)'>";

											}

										});

					});

	function button() {
//alert(11111);
		var realIp = $("#realIp").val();
		var VIP = $("#VIP").val();

		var exp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
		var isip = realIp.match(exp);
		//alert(obj);

		//for ( var int = 0; int < obj.length; int++) {
		//ipList.push(obj[int].realIp);
		//}
		var ipList = [];
		//alert(ipList);
		//alert($.inArray(realIp,ipList));
		var tbl = document.getElementById('myTable');
		for ( var int = 1; int <tbl.rows.length; int++) {
		    //alert( tbl.rows[int].cells[0].innerHTML);
		    ipList.push( tbl.rows[int].cells[0].innerHTML);
		}

		

		if (realIp == "") {

			messageDialog("IP地址不能为空！", "alert");
		} else if ($.inArray(realIp, ipList) != -1) {
			messageDialog("IP地址已存在！", "alert");
		} else if (isip == null) {
			messageDialog("IP地址输入不合法！", "alert");
		}

		else if (VIP == "") {
			messageDialog("VIP信息不能为空！", "alert");
		} else {
			//return true;
			confirmDialog(
					"确定要添加？",
					function(result) {
						if (result) {
							$.get("insert",{
												"realIp" : realIp,
												"VIP" : VIP
											},
											function(data) {
												messageDialog("添加成功 ");
												var objs = eval(data);

												var tbl = document.getElementById('myTable');

												var newRow = tbl.insertRow(tbl.rows.length);

												// newRow.insertCell(0).innerHTML = "ipMap";
												newRow.insertCell(0).innerHTML = objs[objs.length - 1].realIp;
												newRow.insertCell(1).innerHTML = objs[objs.length - 1].VIP;
												newRow.insertCell(2).innerHTML = "<input type='button' class='ui-button ui-widget ui-state-default ui-corner-all' value='删除' onclick='delrealIpf(this)'>";

											});
						} else {
							messageDialog("添加取消！");
						}
					});
		}
		//return false;

	}

	function delrealIpf(obj) {

		confirmDialog(
				"确定要删除？",
				function(result) {
					if (result) {
						//alert(obj.parentNode.parentNode.children[1].innerHTML);
						var delrealIp = obj.parentNode.parentNode.children[0].innerHTML;
						document.getElementById("myTable").deleteRow(
								obj.parentElement.parentElement.rowIndex);

						$.get("delrealIp", {
							"delrealIp" : delrealIp
						}, function(data) {

							messageDialog("删除成功 ", "success");
							//ipList.splice($.inArray(delrealIp,ipList),1);

						});
					} else {
						messageDialog("删除取消！");
					}
				});

	}
</script>

<style type="text/css">
#myTable {
	border-collapse: collapse;
	border: 1px solid #448DAE
}

#myTable td {
	border-collapse: collapse;
	border: 1px solid #448DAE;
	padding-bottom: 0px;
	padding-top: 0px
}
</style>

</head>

<body>
	<h1>网闸IP映射</h1>
	<table id="myTable" style="text-align: center;">
		<tr style="background-color:#0078AE;text-align: center;">

			<td width="149px">真实IP</td>
			<td width="149px">映射IP</td>
			<td>操作</td>

		</tr>



	</table>


	<h3>新增IP映射</h3>
	<form name="form2">
		设置真实IP:<input type="text" size="20" id="realIp" name="realIp"><br>
		<br> <br> <br> 设置映射IP:<input type="text" size="20"
			id="VIP" name="VIP"><br> <br> <input type="button"
			value="提交" onclick="button()">

	</form>


	<!--  <div id="div2"></div>
	<div id="div3"></div>
	<div id="div4"></div>

	<h3>删除IpMap</h3>
	<form name="form3">
		请输入IP:<input type="text" size="20" id="delrealIp"><br> <br>
		<input type="button" value="删除" onclick="delrealIpf()">

	</form>

-->
</body>
</html>

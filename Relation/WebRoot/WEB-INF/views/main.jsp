<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<base href="<%=basePath%>" />

<title>WEB管理</title>

<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<link rel="icon" href="./media/img/logo.png" />
<link href="./jquery-ui/jquery-ui.css" rel="stylesheet" />
<link href="./css/jquery-ui-timepicker-addon.css" rel="stylesheet" />
<link href="./css/ui.jqgrid.css" rel="stylesheet" />

<style type="text/css">
table {
	font-size: 14px;
}

#head {
	margin: 0 12.5%;
	min-width: 850px;
	height: 48px;
	background: url('./media/img/head.jpg');
	background-repeat: repeat-x;
	z-index: 100;
}

#main {
	margin: 0 12.5%;
	padding-top: 20px;
	min-width: 850px;
	min-height: 800px;
	background-color: #EDEFF4;
}

#locate {
	margin: 10px 40px;
	font-size: 12px;
}

#main-content {
	margin: 10px 40px;
	padding: 10px 10px;
	min-height: 350px;
	border: #c6c6c6 1px solid;
	background-color: #EDEFF4;
	font-size: 12px;
}
input[type="button"]{
	width: 75px;
	height: 23px;
	background: url('./media/img/yellowBut_normal.png');
	border: 0;
	cursor: pointer;
	text-align: center;
	padding-top: 3px;
	margin-bottom: 5px;
	color: #333333;
}
input[type="button"]:hover{
	width: 75px;
	height: 23px;
	background: url('./media/img/yellowBut_up.png');
	border: 0;
	cursor: pointer;
	text-align: center;
	padding-top: 3px;
	margin-bottom: 5px;
	color: #333333;
}
</style>
</head>

<body style="margin: 0 0;font-family: Arial;background-color: #989CAC">
	<div id="head">
		<table style="height: 48px;" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td style="width: 100px">
					<div><img src="./media/img/main_logo.png" width="76px" height="40px"/></div>
				</td>
				<td style="min-width: 300px;">
					<span style="font-weight: bold;color: #E2E5EF;">飞讯运维平台</span>
				</td>
				<td style="width: 200px">
					<div id="time" style="color: white;font-size: 12px;margin-top: 10px"></div>
				</td>
				<td style="width: 100px;font-size: 12px;">
					<div style="margin-top: 10px;color: white;">
						<img src="./media/img/user.png"/>
						<span style="margin-left: 5px">${sessionScope.username}</span>
					</div>
				</td>
				<td style="width: 100px;font-size: 12px;">
					<div style="margin-top: 10px;color: white;cursor:pointer;" onclick="logout()">
						<img src="./media/img/exit.png"/>
						<span style="margin-left: 5px">退出</span>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div id="main">
		<div class="NavBar">
			<ul id="menus"></ul>
		</div>
		<div id="locate">
			<span>位置：</span><span id="location">首页</span>
		</div>
		<div id="main-content"></div>
	</div>
	
	<script src="./js/jquery-1.11.1.min.js"></script>
	<script src="./js/jquery-ui.js"></script>
	<script src="./js/jquery-ui-timepicker-addon.js"></script>
	<script src="./js/jquery-ui-timepicker-zh-CN.js"></script>
	<script src="./js/jquery.jqGrid.min.js"></script>
	<script src="./js/grid.addons.js"></script>
	<script src="./js/grid.locale-cn.js"></script>
	<script src="./js/fxdigital.js"></script>
	<script src="./js/Event.js"></script>
	<script>
		function p_menuClick(name, url) {
			if (url != null && url != "") {
				$("#location").text(name);
				$.post(url, function(data) {
					jump(name, data);
				});
			}
		}
		function c_menuClick(c_name, p_name, url) {
			if (url != null && url != "") {
				$("#location").text(p_name+" > "+c_name);
				$.post(url, function(data) {
					jump(c_name, data);
				});
			}
		}
		function showTime() {
			var time;
			var now = new Date();
			var year = now.getFullYear();
			var month = now.getMonth() + 1;
			var day = now.getDate();
			var hours = now.getHours();
			var minutes = now.getMinutes();
			var seconds = now.getSeconds();
			time = year + '年' + month + '月' + day + '日  ' + hours + ':' + minutes
					+ ':' + seconds;
			$("#time").text(time);
		}
		function isIE8() {
			var user_agent = navigator.userAgent.toLowerCase();
			if ((user_agent.indexOf("msie 10.0")>-1||user_agent.indexOf("msie 9.0")>-1||user_agent.indexOf("msie 8.0")>-1||user_agent.indexOf("msie 7.0")>-1)&&user_agent.indexOf("trident/6.0")>-1)
				return true;
			return false;
		}
		function logout(){
			confirmDialog("确认退出？",function(flag){
				if(flag)
					window.location.href = "logout";
			});
		}
		
		$(function() {
			setInterval("showTime()", 500);
			//$(document).tooltip();
			//最后执行
			$.ajax({url:"getMenu",timeout:5000,type:'get',dataType:'json',
				success:function(data) {
					if(data == null || data == ""){
						messageDialog("目录数据为空！","fail");
					}
					var menus = eval(data);
					var p_menus = [];
					for (var i = 0; i < menus.length; i++) {
						var menu = menus[i];
						if (menu.pid == 0) {
							p_menus[menu.id] = menu.name;
							var p_menu = "<li><a onclick=\"p_menuClick('" + menu.name
									+ "','" + menu.diyUrl + "')\">" + menu.name
									+ "</a><ul id='p_menu_"+menu.id+"'></ul></li>";
							$("#menus").append(p_menu);
						} else {
							var c_menu = "<li><a onclick=\"c_menuClick('" + menu.name
									+ "','" + p_menus[menu.pid] + "','" + menu.diyUrl
									+ "')\">" + menu.name + "</a></li>";
							$("#p_menu_" + menu.pid).append(c_menu);
						}
					}
					//ie8以上及其它浏览器的css、js动态加载方法
					//$("<script>",{src:"./js/menu.js"}).appendTo("head");
					//$("<link>",{href:"./css/menu.css",rel:"stylesheet"}).appendTo("head");
					
					//ie8及以下的css、js动态加载方法
					var style = document.createElement('link');
					style.href = './css/menu.css';
					style.rel = 'stylesheet';
					document.getElementsByTagName('head').item(0).appendChild(style);
	
					var script = document.createElement('script');
					script.src = './js/menu.js';
					document.getElementsByTagName('head').item(0).appendChild(script);
				},
				complete:function(XMLHttpRequest,status){
					if(status=='timeout'){
						messageDialog("获取目录数据超时！","fail");
					}
				}
			});
		});
	</script>
</body>
</html>

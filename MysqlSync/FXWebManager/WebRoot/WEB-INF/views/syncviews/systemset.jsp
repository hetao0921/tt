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

		<title>My JSP 'left.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="./js/fxdigital.js"></script>
		<style type="text/css">
table {
	border-collapse: collapse;
	font-family: Arial;
	font-size: 14px;
	border-spacing: 0;
	cellpadding: 0;
	cellspacing: 0;
}

.top_tab {
	width: 100%;
	border-collapse: collapse;
	height: 48px;
	min-width: 1024px;
	position: absolute;
	z-index: 1
}

.top_tab tr {
	background-repeat: repeat-x;
}

.top_tab td {
	white-space: nowrap;
}

.tab_head {
	font-size: 14px;
	font-weight: bolder;
	color: #3057A9;
	background-color: #D6DAE5
}


.tab tr {
	height: 30px;
}

.tab td {
	padding-left: 4px;
}

.tab th {
	background: url('images/th.jpg');
	white-space: nowrap;
	height: 28px;
}



#title_table {
	border: 1px solid #979CB1;
	width: 95%;
	margin-top: 20px;
	margin-left: 2.5%
}

#title_table th {
	border: 1px solid #979CB1;
	color: #6D6D6E
}

.topdiv {
	width: 100%;
	height: 850px;
	background-color: #EDEFF4;
	min-width: 850px;
	min-height: 900px;
	position: absolute;
}


</style>
		<script type="text/javascript">
		function vaerify(){
		var syncip=document.getElementById("syncip").value;
		var syncport=document.getElementById("syncport").value;
		var pattern=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
		var patterns="^[0-9]+$";
		var re=new RegExp(patterns);
		flag_ip=pattern.test(syncip);
		if(flag_ip){
		if(syncport.search(re)!=-1&&parseInt(syncport)<65536){
		setsysncinfo();
		}else{
		messageDialog("输入端口非法！要是正整数及小于65536");
		document.getElementById("syncport").focus();
		return false;
		}
		}else{
		messageDialog("输入Ip非法！");
		document.getElementById("syncip").focus();
		return false;
			}
		}
		
		function setsysncinfo(){
		$.ajax({
			  url:"setsyncinfo",
			  type:"POST",
			  data:{"syncip":$('#syncip').val(),"syncport":$('#syncport').val()},
			  timeout:60000,
			  dataType:"text",
			  success:function(data){
			    if (data >0) {
			     messageDialog("设置成功！");
			     getsyncinfo();
			    } else{
			    messageDialog("数据库链接断开！设置失败！");
			    }
			  },
			  error:function(){
			  messageDialog("链接超时！请退出重新登录！");
			  onloadfail();
			  }
			});
		}
		
		function onloadfail(){
				 $.get("onloadfail",function(data){
					jump("链接错误",data);
				});
			}
			
	$(function()  {
		$.ajax({
			  url:"getsyncinfo",
			  type:"POST",
			  data:{"Action" : "post"},
			  timeout:60000,
			  dataType:"text",
			  success:function(data){
			   if (data == "-1") {
			     messageDialog("数据库没有数据！");
			     document.getElementById('syncip').value = "请输入同步服务器Ip";
				 document.getElementById('syncport').value = "61616";
			    } else{
			    	var str1 = new Array();
					str1 = data.split(",");
					document.getElementById('syncip').value = str1[0];
					document.getElementById('syncport').value = str1[1];
			    }
			  },
			  error:function(){
			  messageDialog("链接超时！请退出重新登录！");
			  onloadfail();
			  }
			});
	});
</script>
	</head>

	<body bgcolor="#989CAC">
		<div class="topdiv">
				<table class="tab" id="title_table">
					<tr class="tab_head">
						<td colspan="6" style="text-align: left;">
							同步服务器信息设置
						</td>
					</tr>
					<tr style="text-align: center;">
						<td>
							<label>
								同步服务器IP：
							</label>
							<input type="text" id="syncip" name="syncip" value="正在获取数据。。">
						</td>
					</tr>
					<tr style="text-align: center;">
						<td>
							<label>
								同步服务端口：
							</label>
							<input type="text" id="syncport" name="syncport" value="正在获取数据。。">
						</td>

					</tr>
					<tr style="text-align: right;">
						<td>
							<input type="button"  value="保存" onclick="vaerify()">
						</td>
					</tr>
				</table>
		</div>
	</body>
</html>

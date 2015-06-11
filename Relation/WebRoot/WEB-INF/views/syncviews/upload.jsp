<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>buttonishidden</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="./js/fxdigital.js"></script>
			<style type="text/css">
		
			body,div{margin:0;border:0;padding:0;}
			.topdiv {
				background-color: #EDEFF4;
				position: relative;
			}
			
				#title_table {
				border: 1px solid #979CB1;
				width: 95%;
				margin-top: 20px;
				margin-left: 2.5%
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
		</style>
		<script type="text/javascript">
		$(function(){
		$("#uploadbutton").attr('disabled',true); 
		$("#resetbutton").attr('disabled',true); 
			getsyncversion();
		});
	
	function getsyncversion() {
		$.post("getsyncnumber", {
			"Action" : "post",
			"Name" : "lulu"
		}, function(data) {
		if(data=="-1"){
		onloadfail();
		}else{
		var str= new Array();   
        str=data.split(",");      
		document.getElementById('serid').value=str[0];
		document.getElementById('aftid').value=str[1];
		getlock();
		}
		}, "text");
	}
	
	function getlock(){
		$.post("getuplock",{},function(data) {
			if (data == "0") {
				$("#uploadbutton").attr('disabled',false); 
				$("#resetbutton").attr('disabled',true); 
				$("#loadstate").hide();
			} else {
				document.getElementById("uploadbutton").disabled = true;
				document.getElementById("resetbutton").disabled = false;
				$("#loadstate").show();
				}
			}, "text");
		}
	
	function isupload() {
	//document.getElementById("uploadbutton").disabled = true;
	$("#uploadbutton").attr('disabled',true); 
	$("#loadstate").show();
		$.post("isupload", {
			"selfversion1" : $('#uploadid').val(),
			"serversion1" : $('#serid').val(),
			"aftversion1" : $('#aftid').val()
		}, function(data) {
		document.getElementById('serid').value="正在获取值。。。";
		document.getElementById('aftid').value="正在获取值。。。";
			if(data=="-1"){
				onloadfail();
			}else if(data=="1"){
				uploading();
			}else if(data=="0"){
				if (confirm("本地数据库收到最新版本，点击确认刷新数据")) {
					waitupload();
				}
			}else if(data=="2"){
				alert("上传中！");
				getlock();
			}else{
			alert("未知错误！");
			}
		}, "text");
	}

 	//进行上传操作
	function uploading(){
		$.post("uploading",{"Action" : "post"}, function(data){
		var str=new Array();
		str=data.split("_");
			if(str[0]=="0"){
			document.getElementById('uploadstate').value=str[1];
			 partialrefresh();
			}else if(str[0]=="1"){
		    document.getElementById('uploadstate').value=str[1];
			}else if(str[0]=="2"){
			onloadfail();
			}else{
			document.getElementById('uploadstate').value=str[1];
			uploading();
			}
		},"text");
 	}
 	//重新请求本地数据,锁ID，同步服务器版本信息，级刷新状态监控值
	function partialrefresh(){
		$.post("getnativever",{"Action" : "post"}, function(data){
		var str=new Array();
		str=data.split(",");
		document.getElementById('uploadid').value=str[0];
		getsyncversion();
		},"text");
 	}
 	function waitupload(){
 	$.post("getnativever",{"Action" : "post"}, function(data){
		showsyncnumber(data);
 	},"text");
 	}
 	
 	function reset() {
		if (confirm("确认复位？可能其他管理员正在进行上传操作！")) {
			$.post("unlock", {
				"unlock" : "0"
			}, function(data) {
				alert(data);
				waitupload();
			});
		}
	}
		</script>
	</head>
	<body  bgcolor="#989CAC">
		<div class="topdiv">
		<table class="tab" id="title_table">
					<tr class="tab_head">
						<td colspan="2" style="text-align: left;">
							本地上传版本信息
						</td>
					</tr>
					<tr>
						<td>
							<label>
								本地上传版本：
							</label>
							<input type="text" id="uploadid" name="uploadversion"
					value="${selfversion}" readonly="readonly">
						</td>
						 <td rowspan="4" align="center">
						 	<div id="loadstate" align="center" style="display: none">
								<p>
										<img src="media/img/uploading.gif">
								</p>
								<p>正在上传>>>>>>>>>>></p>
							</div>
						 </td>
					</tr>
					<tr>
						<td>
							<label>
								同步服务器上版本：
							</label>
							<input type="text" id="serid" name="syncversion"
					value="正在获取值。。。" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>
							<label>
								上传后的版本：
							</label>
							<input type="text" id="aftid" name="aftversion"
					value="正在获取值。。。" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" id="uploadbutton" value="上传" onclick="isupload()" >
							<input type="button" id="resetbutton" value="复位" onclick="reset()" >
						</td>
					</tr>
				</table>
		
			<table class="tab" id="title_table">
					<tr class="tab_head">
						<td colspan="2" style="text-align: left;">
							本地上传状态监控
						</td>
					</tr>
					
					<tr>
						<td>
						<textarea style="width: 99%;resize: none;" readonly="readonly" id="uploadstate" rows="15">
						</textarea>
						</td>
					</tr>
				</table>
		</div>
	</body>
</html>

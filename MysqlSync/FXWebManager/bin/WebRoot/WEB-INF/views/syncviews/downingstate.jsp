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
	
		.topdiv {
			width: 100%;
			height: 850px;
			background-color: #EDEFF4;
			min-width: 850px;
			min-height: 900px;
			position: absolute;
		}
		
		.butdiv{
		margin-top:28px;
		background-color: #EDEFF4;
		content: center;
		}
		</style>
<script type="text/javascript">
		
		 $(function(){
			init();	 
		 });
		 
		 function init(){
			$.post("getdownstate",{"json" : "json"},function(data){
			//$('#accordion')).accordion("refresh");
			var str=new Array();
				str=data.split("_");
			if(str[0]=="0"){
				$("#accordion").html(str[1]);
				$("#accordion").accordion();
				$('#accordion').accordion({ clearStyle: true });
			}else if(str[0]=="1"){
			$('#accordion').accordion("refresh");
			$("#accordion").html(str[1]);
			$("#accordion").accordion();
				$('#accordion').accordion({ clearStyle: true });
			}else if(str[0]=="2"){
			onloadfail();
			}else{
			//$("#accordion").html(str[1]);
			//$('#accordion').accordion({ clearStyle: true },{collapsible:true});
			init();
		}
		},"text");
		}
			function onloadfail(){
				 $.get("onloadfail",function(data){
					jump("链接错误",data);
				});
			}
		
</script>
	</head>
	<body bgcolor="#989CAC">
	  	<div class="topdiv">
	
			<div style="width:100%; height:600; overflow-y:scroll;">
				
				<div id="accordion">
				 </div>
				 
			</div>
			 <div align="center">
				<!-- <input type="button" id="uploadbutton" value="确定" onclick="returns()" > -->
			</div>
		
		</div> 
	</body>
</html>

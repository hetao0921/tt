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
var str=null;
$(document).ready(
					function() {
						$.get("show",function(data) {
						
						
						//$("#myDiv").text(data);
						str=data;
						var s = document.getElementById("s1");
						//alert(s.length);
    for ( var int = 0; int < s.length; int++) {
     //alert(s.options[int].value);
    if (s.options[int].value==data) {
       s.options[int].selected = true;
		
	}
    
		
	}
						
						
						});

					});
	/*function select(obj) {
		var num = obj.value;

		$.get("select", {
			"num" : num
		}, function(data) {

			messageDialog("修改成功 ", "success");
		});

	} */
	function select(obj) {
	
	confirmDialog("确定要更改？",function(result){
				if(result){
					var num = obj.value;

		$.get("select", {"num" : num}, function(data) {

			messageDialog("更改成功 ", "success");
		str=data;
			//$("#myDiv").text(data);
			var s = document.getElementById("s1");
						//alert(s.length);
    for ( var int = 0; int < s.length; int++) {
    // alert(s.options[int].value);
    if (s.options[int].value==data) {
       s.options[int].selected = true;
		
	}
    
		
	}
			
			
		});
						} else {
						
					messageDialog("更改取消！");
					var s = document.getElementById("s1");
							for ( var int = 0; int < s.length; int++) {
    // alert(s.options[int].value);
    if (s.options[int].value==str) {
       s.options[int].selected = true;
		
	}
    
		
	}
							
						}
				});
				
		
			}
</script>




</head>

<body>
	 
<div id="center" style="padding: 20px">
		<form name="form1">
			<strong>传输协议模式设置:</strong> <select onchange="select(this)" id="s1">
				<option value="0" >TCP</option>
				<option value="1">UDP</option>
				<option value="2">组播</option>


			</select>

		</form>
		<br>
		<div id="myDiv" style="font-weight: bold"></div>
		</div>
	
</body>

</html>

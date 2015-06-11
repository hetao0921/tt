<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String model=(String)request.getAttribute("model");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>视频流传输协议设置</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<script src="./js/fxdigital.js"></script>
		<script src="./js/deviceModel.js"></script>
		<script src="./layer/layer.min.js"></script>
<style type="text/css">
.big{width:100%;height:80px;}
.a{float:left;width:70%;height:80px;}

.b{float:right;width:30%;height:80px;}

</style>
	</head>

	<body>
		<!--<input id="edit" type="button" value="修改">-->
	<div class="big">
			
		<div id="mysearch" class="a"></div> 
		<div  class="b" >
		
				<label  style="float: center; width: 70px">
				传输协议模式设置:
				</label>
				<select onchange="select(this)" id="s1" style="width: 80px">
				<option value="0" <%="0".equals(model)==true?"selected":null %>>TCP</option>
				<option value="1" <%="1".equals(model)==true?"selected":null %>>UDP</option>
				<option value="2" <%="2".equals(model)==true?"selected":null %>>组播</option>
				<option value="-1" <%="-1".equals(model)==true?"selected":null %>>请设置</option>
				</select>
	 </div>
</div>
				
				
				 <!--
		
		<div id="buttons">
		
		<input id="add" type="button" value="添加">
			<input id="edit" type="button" value="修改">
			<input id="del" type="button" value="删除">
			
		</div>
		
		--><table id="jqGrid"></table>
		<br>
		<div id="plist47"></div>
		<!-- <div id="modal-dialog" style="display: none;"> -->


<!-- 			<form>
				<br>
				<label style="float: left; width: 70px">
					流类型：
				</label>
			<input id="type" type="text" name="type" disabled="true"　readOnly="true" 
class="text ui-widget-content ui-corner-all" />
				<br>
				<br>
				<label style="float: left; width: 70px">
					视频流名称：
				</label>
			<input id="devName" type="text" name="devName" disabled="true"　readOnly="true" 
					class="text ui-widget-content ui-corner-all" />
				<br>
				<br>
				<label style="float: left; width: 70px">
					IP：
				</label>
				<input id="devIP" type="text" name="devIP" disabled="true"　readOnly="true" 
					class="text ui-widget-content ui-corner-all"  />
				<br>
				<br>
				<label style="float: left; width: 70px">
					设备类型：
				</label>
				<select id="netlinkmode" name="netlinkmode">
					<option value="-1">请选择</option>
					<option value="0">TCP</option>
					<option value="1">UDP</option>
					<option value="2">组播</option>
				</select>
			</form> -->

		<!-- </div> -->
	</body>
</html>

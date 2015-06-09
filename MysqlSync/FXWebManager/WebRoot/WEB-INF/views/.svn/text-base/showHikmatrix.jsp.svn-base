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

<title>解码矩阵</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script src="./js/fxdigital.js"></script>
<script src="./layer/layer.min.js"></script>

<script type="text/javascript">
	$(function() {
		jQuery("#jqGrid").jqGrid(
				{
					url : 'getHikmatrix',
					datatype : "json",
					mtype : "POST",
					autowidth : true,
					colNames : [ '用户名', '密码', '类型', '设备ID', '设备类型', '输出类型',
							'IP地址', '端口号' ],
					colModel : [ {
						name : 'admin',
						index : 'admin',
						width : '12.5%',
						sorttype : "text",
						resizable : true,
					}, {
						name : 'pass',
						index : 'pass',
						width : '12.5%',
						sorttype : "text",
						resizable : true,
					}, {
						name : 'classType',
						index : 'classType',
						width : '12.5%',
						sorttype : "text",
						resizable : true,
					}, {
						name : 'deviceId',
						index : 'deviceId',
						width : '20.5%',
						sorttype : "text",
						resizable : true,
					}, {
						name : 'deviceType',
						index : 'deviceType',
						width : '10%',
						sorttype : "text",
						resizable : true,
					}, {
						name : 'outmodel',
						index : 'outmodel',
						width : '10%',
						sorttype : "text",
						resizable : true,
					}, {
						name : 'address',
						index : 'address',
						width : '15%',
						sorttype : "text",
						resizable : true,
					}, {
						name : 'port',
						index : 'port',
						width : '7%',
						sorttype : "int",
						resizable : true,
					} ],
					rowNum : 20,
					rowList : [ 10, 20, 30 ],
					pager : '#plist47',
					pagerpos : 'right',
					recordpos : 'left',
					//sortname : 'id',
					viewrecords : true,
					//sortorder : "desc",
					loadonce : true,
				//caption : "测试实例"
				}).navGrid('#plist47', {
			add : false,
			edit : false,
			del : false,
			search : false,
			refresh : false,
		});
		//$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ 'overflow-y' : 'scroll' });
		$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
			'overflow-x' : 'hidden'
		});
		
		doResize();
		Event.onResizend(function() {
			doResize();
		});
		function doResize() {
			var mainContentHeight = $("#main-content").height();
			var mainContentWidth = $("#main-content").width();
			var buttonsHeight = $("#buttons").height();
			var tableHeadHeight = $("#jqgh_jqGrid_admin").height();
			var pagerHeight = $("#plist47").height();
			$("#jqGrid").setGridHeight(mainContentHeight - buttonsHeight-pagerHeight-tableHeadHeight-8);
			$("#jqGrid").setGridWidth(mainContentWidth-2);
		}
		
		var add_dialog = null;
		var edit_dialog = null;
		function createAddDialog(){
			add_dialog = $("#modal-dialog").dialog({
				autoOpen : false,
				height : 420,
				width : 450,
				modal : true,
				buttons : {
					"保存" : function() {
						if(checkNull()){
							add();
						}
					},
					"取消" : function() {
						add_dialog.dialog("close");
					}
				},
				close : function() {
					add_dialog.find("form")[0].reset();
					add_dialog.dialog("destroy");
				}
			});
		}
		function createEditDialog(selectedId){
			edit_dialog = $("#modal-dialog").dialog({
				autoOpen : false,
				height : 420,
				width : 450,
				modal : true,
				buttons : {
					"修改" : function() {
						if(checkNull()){
							edit(selectedId);
						}
					},
					"取消" : function() {
						edit_dialog.dialog("close");
					}
				},
				close : function() {
					edit_dialog.find("form")[0].reset();
					edit_dialog.dialog("destroy");
				}
			});
		}
		function getClasses(selectedValue){
			$.post("getClasses", function(data) {
				if (data != null && data != "") {
					$("#class1 option").remove();
					var classes = eval("(" + data + ")");
					var flag = false;
					for ( var cla in classes) {
						var value = classes[cla];
						$("#class1").append(
								"<option value='"+value+"'>" + value + "</option>");
						if(selectedValue == value){
							$("#class1 option[value='"+selectedValue+"']")
									.attr("selected","selected");
							flag = true;
						}
					}
					if(!flag && selectedValue != null){
						appendMessage("不存在的类型，将显示默认类型！","alert");
					}
				}
			});
		}
		function getDeviceTypes(selectedValue){
			$.post("getDeviceTypes", function(data) {
				if (data != null && data != "") {
					$("#deviceType option").remove();
					var deviceTypes = eval("(" + data + ")");
					var flag = false;
					for ( var key in deviceTypes) {
						var value = deviceTypes[key];
						$("#deviceType").append(
								"<option value='"+value+"'>" + key + "</option>");
						if(value == selectedValue){
							$("#deviceType option[value='"+selectedValue+"']")
									.attr("selected","selected");
							flag = true;
						}
					}
					if(!flag && selectedValue != null){
						appendMessage("不存在的设备类型，将显示默认设备类型！","alert");
					}
				}
			});
		}
		function getOutModels(selectedValue){
			$.post("getOutModels", function(data) {
				if (data != null && data != "") {
					$("#outModel option").remove();
					var outModels = eval("(" + data + ")");
					var flag = false;
					for ( var key in outModels) {
						var value = outModels[key];
						$("#outModel").append(
								"<option value='"+value+"'>" + key + "</option>");
						if(key == selectedValue){
							$("#outModel option[value='"+value+"']")
									.attr("selected","selected");
							flag = true;
						}
					}
					if(!flag && selectedValue != null){
						appendMessage("不存在的输出模式，将显示默认输出模式！","alert");
					}
				}
			});
		}
		$('#refresh_matrix').click(function(){
			$.post("refreshHikmatrix",function(data){
				if ("true" == data) {
					layer.msg('IP矩阵刷新成功！', 1, -1);
					refresh();
				} else {
					messageDialog("IP矩阵刷新失败！","fail");
				}
			});
		});
		$('#del').click(function(){
			var selectedId = $("#jqGrid").jqGrid("getGridParam", "selrow");
			if(selectedId == null){
				messageDialog("请选择一行数据！","notice");
				return;
			}
			del(selectedId);
		});
		$('#edit').click(function(){
			var selectedId = $("#jqGrid").jqGrid("getGridParam", "selrow");
			var rowData = $("#jqGrid").jqGrid("getRowData", selectedId);
			if(selectedId == null){
				messageDialog("请选择一行数据！","notice");
				return;
			}
			getClasses(rowData.classType);
			getDeviceTypes(rowData.deviceType);
			getOutModels(rowData.outmodel);
			$("#user").val(rowData.admin);
			$("#pass").val(rowData.pass);
			$("#deviceId").val(rowData.deviceId);
			$("#address").val(rowData.address);
			$("#port").val(rowData.port);
			$("#modal-dialog").attr("title","修改矩阵");
			createEditDialog(selectedId);
			edit_dialog.dialog("open");
		});
		$('#add').click(function() {
			getClasses(null);
			getDeviceTypes(null);
			getOutModels(null);
			$("#modal-dialog").attr("title","添加矩阵");
			createAddDialog();
			add_dialog.dialog("open");
		});
		function add(){
			confirmDialog("确定要添加？",function(result){
				if(result){
					$.post("addHikmatrix", {
						"txtUser" : $("#user").val(),
						"txtPass" : $("#pass").val(),
						"txtClass" : $("#class1").val(),
						"txtOutModel" : $("#outModel").val(),
						"txtDeviceId" : $("#deviceId").val(),
						"txtDeviceType" : $("#deviceType").val(),
						"txtAddress" : $("#address").val(),
						"txtPort" : $("#port").val(),
					}, function(data) {
						if ("true" == data) {
							layer.msg('添加成功！', 1, -1);
							add_dialog.dialog("close");
							refresh();
						} else {
							messageDialog("添加失败！","fail");
						}
					});
				}
			});
		}
		function edit(selectedId){
			confirmDialog("确定要修改？",function(result){
				if(result){
					$.post("editHikmatrix", {
						"selectedId" : selectedId,
						"txtUser" : $("#user").val(),
						"txtPass" : $("#pass").val(),
						"txtClass" : $("#class1").val(),
						"txtOutModel" : $("#outModel").val(),
						"txtDeviceId" : $("#deviceId").val(),
						"txtDeviceType" : $("#deviceType").val(),
						"txtAddress" : $("#address").val(),
						"txtPort" : $("#port").val(),
					}, function(data) {
						if ("true" == data) {
							layer.msg('修改成功！', 1, -1);
							edit_dialog.dialog("close");
							refresh();
						} else {
							messageDialog("修改失败！","fail");
						}
					});
				}
			});
		}
		function del(selectedId){
			confirmDialog("确定要删除？",function(result){
				if(result){
					$.post("delHikmatrix",{"selectedId":selectedId},function(data){
						if ("true" == data) {
							layer.msg('删除成功！', 1, -1);
							refresh();
						} else {
							messageDialog("删除失败！","fail");
						}
					});
				}
			});
		}
		function refresh(){
			$.post("showHikmatrix", function(data) {
				jump("解码矩阵设置", data);
			});
		}
		function checkNull() {
			var user = $("#user").val();
			var pass = $("#pass").val();
			var deviceId = $("#deviceId").val();
			var address = $("#address").val();
			var port = $("#port").val();
			var reg = /^\d*$/;
			var exp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
			var isip = address.match(exp);
			if (user == "") {
				messageDialog("用户名不能为空！","alert");
			} else if (pass == "") {
				messageDialog("密码不能为空！","alert");
			} else if (deviceId == "") {
				messageDialog("设备ID不能为空！","alert");
			} else if (address == "") {
				messageDialog("IP地址不能为空！","alert");
			} else if (isip == null) {
				messageDialog("IP地址输入不合法！","alert");
			} else if (port == "") {
				messageDialog("端口号不能为空！","alert");
			} else if (!(reg.test(port))) {
				messageDialog("端口号只能是数字","alert");
			} else if (port < 1024) {
				messageDialog("端口号不能小于1024！","alert");
			} else if (port > 65536) {
				messageDialog("端口号不能大于65536！","alert");
			} else {
				return true;
			}
			return false;
		}
	});
</script>

<style type="text/css">
</style>

</head>

<body>
	<div id="buttons">
		<input id="add" type="button" value="添加">
		<input id="edit" type="button" value="修改">
		<input id="del" type="button" value="删除">
		<input id="refresh_matrix" type="button" value="刷新矩阵">
	</div>
	<table id="jqGrid"></table>
	<div id="plist47"></div>
	<div id="modal-dialog" style="display: none;">
		<form>
			<br>
			<label style="float: left;width: 70px">用户名：</label>
			<input id="user" type="text" name="txtUser"
				class="text ui-widget-content ui-corner-all"/>
			<br> <br>
			<label style="float: left;width: 70px">密码：</label>
			<input id="pass" type="text" name="txtPass"
				class="text ui-widget-content ui-corner-all" />
			<br> <br>
			<label style="float: left;width: 70px">类型：</label>
			<select id="class1" name="txtClass"><option>--[无数据]--</option></select>
			<br> <br>
			<label style="float: left;width: 70px">设备ID：</label>
			<input id="deviceId" type="text" name="txtDeviceId"
				class="text ui-widget-content ui-corner-all" />
			<br> <br>
			<label style="float: left;width: 70px">设备类型：</label>
			<select id="deviceType" name="txtDeviceType"><option>--[无数据]--</option></select>
			<br> <br>
			<label style="float: left;width: 70px">输出模式：</label>
			<select id="outModel" name="txtOutModel"><option>--[无数据]--</option></select>
			<br> <br>
			<label style="float: left;width: 70px">IP地址：</label>
			<input id="address" type="text" name="txtAddress"
				class="text ui-widget-content ui-corner-all" />
			<br> <br>
			<label style="float: left;width: 70px">端口号：</label>
			<input id="port" type="text" name="txtPort"
				class="text ui-widget-content ui-corner-all" />
			(范围在1024——65536之间)
		</form>
	</div>
</body>
</html>

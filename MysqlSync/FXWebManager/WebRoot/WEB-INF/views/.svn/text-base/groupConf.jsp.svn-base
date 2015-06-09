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

<title>集中配置</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script src="./js/fxdigital.js"></script>
<script src="./layer/layer.min.js"></script>

<script type="text/javascript">
	$(function() {
		jQuery("#deviceInfos").jqGrid(
				{
					url : 'getDevInfos',
					datatype : "json",
					mtype : "POST",
					autowidth : true,
					colNames : [ 'ID', '设备名称', '设备URL', '设备描述' ],
					colModel : [ {
						name : 'id',
						index : 'id',
						//width : '20%',
						//sorttype : "text",
						//resizable : false,
						hidden:true
					},{
						name : 'name',
						index : 'name',
						width : '20%',
						sorttype : "text",
						resizable : true,
					}, {
						name : 'url',
						index : 'url',
						width : '50%',
						sorttype : "text",
						resizable : true,
						formatter : function(cellvalue, options, rowObject) {
							return "<a href='"+cellvalue+"' target='_blank'>" + cellvalue + "</a>";
						}
					}, {
						name : 'desc',
						index : 'desc',
						width : '30%',
						sorttype : "text",
						resizable : true,
					} ],
					rowNum : 20,
					rowList : [ 10, 20, 30 ],
					pager : '#pglist',
					pagerpos : 'right',
					recordpos : 'left',
					viewrecords : true,
					loadonce : true,
				}).navGrid('#pglist', {
					add : false,
					edit : false,
					del : false,
					search : false,
					refresh : false,
				});
		$("#deviceInfos").closest(".ui-jqgrid-bdiv").css({
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
			var tableHeadHeight = $("#jqgh_deviceInfos_name").height();
			var pagerHeight = $("#pglist").height();
			$("#deviceInfos").setGridHeight(mainContentHeight - buttonsHeight-pagerHeight-tableHeadHeight-8);
			$("#deviceInfos").setGridWidth(mainContentWidth-2);
		}
		
		var add_dialog = null;
		var edit_dialog = null;
		function createAddDialog(){
			add_dialog = $("#modal-dialog").dialog({
				autoOpen : false,
				height : 350,
				width : 420,
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
		function createEditDialog(id){
			edit_dialog = $("#modal-dialog").dialog({
				autoOpen : false,
				height : 350,
				width : 420,
				modal : true,
				buttons : {
					"修改" : function() {
						if(checkNull()){
							edit(id);
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
		$('#edit').click(function(){
			var selectedId = $("#deviceInfos").jqGrid("getGridParam", "selrow");
			var rowData = $("#deviceInfos").jqGrid("getRowData", selectedId);
			if(selectedId == null){
				messageDialog("请选择一行数据！","notice");
				return;
			}
			$.post("getDevInfo",{"id":rowData.id},function(data){
				var jsonobj=eval('('+data+')');
				if(jsonobj){
					$("#name").val(jsonobj.name);
					$("#url").val(jsonobj.url);
					$("#desc").val(jsonobj.desc);
					$("#modal-dialog").attr("title","修改设备");
					createEditDialog(jsonobj.id);
					//$("#url").attr("readonly","readonly");
					edit_dialog.dialog("open");
				}else{
					messageDialog("未获取到数据，请刷新页面！","alert");
				}
			});
		});
		$('#add').click(function() {
			$("#modal-dialog").attr("title","添加设备");
			createAddDialog();
			//$("#url").removeAttr("readonly");
			add_dialog.dialog("open");
		});
		$('#del').click(function(){
			var selectedId = $("#deviceInfos").jqGrid("getGridParam", "selrow");
			var rowData = $("#deviceInfos").jqGrid("getRowData", selectedId);
			if(selectedId == null){
				messageDialog("请选择一行数据！","notice");
				return;
			}
			del(rowData.id);
		});
		function add(){
			confirmDialog("确定要添加？",function(result){
				if(result){
					$.post("addDev", {
						"name" : $("#name").val(),
						"url" : $("#url").val(),
						"desc" : $("#desc").val(),
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
		function edit(id){
			confirmDialog("确定要修改？",function(result){
				if(result){
					$.post("editDev", {
						"id" : id,
						"name" : $("#name").val(),
						"url" : $("#url").val(),
						"desc" : $("#desc").val(),
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
		function del(id){
			confirmDialog("确定要删除？",function(result){
				if(result){
					$.post("delDev",{"id":id},function(data){
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
		function checkNull() {
			var name = $("#name").val();
			var url = $("#url").val();
			var reg = /(https?|ftp|mms):\/\/([A-z0-9]+[_\-]?[A-z0-9]+\.)*[A-z0-9]+\-?[A-z0-9]+\.[A-z]{2,}(\/.*)*\/?/;
			if (name == "") {
				messageDialog("设备名称不能为空！","alert");
			} else if (url == "") {
				messageDialog("设备URL不能为空！","alert");
			} else if (!(reg.test(url))) {
				messageDialog("设备URL格式不正确！","alert");
			} else {
				return true;
			}
			return false;
		}
		function refresh(){
			$.post("groupConf", function(data) {
				jump("集中配置", data);
			});
		}
	});
</script>

</head>

<body>
	<div id="buttons">
		<input id="add" type="button" value="添加">
		<input id="edit" type="button" value="修改">
		<input id="del" type="button" value="删除">
	</div>
	<div id="modal-dialog" style="display: none;">
		<form>
			<br>
			<label style="float: left;width: 70px">设备名称：</label>
			<input id="name" type="text" style="width: 300px"
				class="text ui-widget-content ui-corner-all"/>
			<br> <br>
			<label style="float: left;width: 70px">设备URL：</label>
			<textarea id="url" rows="2" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"></textarea>
			<br> <br>
			<label style="float: left;width: 70px">设备描述：</label>
			<textarea id="desc" rows="5" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"></textarea>
		</form>
	</div>
	<table id="deviceInfos"></table>
	<div id="pglist"></div>
</body>
</html>

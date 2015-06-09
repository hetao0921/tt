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

<title>级联设置级联IP设置</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script src="./js/fxdigital.js"></script>
<script src="./layer/layer.min.js"></script>

<script type="text/javascript">
	$(function() {
		jQuery("#linkUpIp")
				.jqGrid(
						{
							url : 'getLinkIP',
							datatype : "json",
							mtype : "POST",
							autowidth : true,
							colNames : [ '序号', '级联IP', '级联关系'],
							colModel : [
									{
										name : 'id',
										index : 'id',
										width : '30%',
										sorttype : "text",
										resizable : true,
									},
									{
										name : 'centerIP',
										index : 'centerIP',
										width : '40%',
										sorttype : "text",
										resizable : true,
									},
									{
										name : 'relation',
										index : 'relation',
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
		$("#linkUpIp").closest(".ui-jqgrid-bdiv").css({
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
			var tableHeadHeight = $("#jqgh_linkUpIp_id").height();
			var pagerHeight = $("#pglist").height();
			$("#linkUpIp").setGridHeight(mainContentHeight - buttonsHeight-pagerHeight-tableHeadHeight-8);
			$("#linkUpIp").setGridWidth(mainContentWidth-2);
		}
		
		var add_dialog = null;
		var edit_dialog = null;
		function createAddDialog(){
			add_dialog = $("#modal-dialog").dialog({
				autoOpen : false,
				height : 250,
				width : 250,
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
		function createEditDialog(){
			edit_dialog = $("#modal-dialog").dialog({
				autoOpen : false,
				height : 250,
				width : 250,
				modal : true,
				buttons : {
					"修改" : function() {
						if(checkNull()){
							edit();
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
		
		$("#add").click(function(){
			$("#relation").val("上级ip");
			$("#modal-dialog").attr("title","添加级联IP");
			createAddDialog();
			add_dialog.dialog("open");
		});
		
		$("#edit").click(function(){
			var selectedId = $("#linkUpIp").jqGrid("getGridParam", "selrow");
			var rowData = $("#linkUpIp").jqGrid("getRowData", selectedId);
			if(selectedId == null){
				messageDialog("请选择一行数据！","notice");
				return;
			}
			if(rowData.relation == "下级ip"){
				messageDialog("不允许修改下级IP！","notice");
				return;
			}
			$("#id").val(rowData.id);
			$("#centerIP").val(rowData.centerIP);
			$("#relation").val(rowData.relation);
			$("#modal-dialog").attr("title","修改级联IP");
			createEditDialog();
			edit_dialog.dialog("open");
		});
		
		$("#del").click(function(){
			var selectedId = $("#linkUpIp").jqGrid("getGridParam", "selrow");
			var rowData = $("#linkUpIp").jqGrid("getRowData", selectedId);
			if(selectedId == null){
				messageDialog("请选择一行数据！","notice");
				return;
			}
			confirmDialog("确定要删除？",function(result){
				if(result){
					$.post("deleteLinkIP",{"centerIP":rowData.centerIP},function(data){
						if(data == "true"){
							layer.msg('删除成功！', 1, -1);
							refresh();
						}else{
							messageDialog("删除失败！","fail");
						}
					});
				}
			});
		});
		
		function add(){
			confirmDialog("确定要添加？",function(result){
				if(result){
					$.post("updateLinkIP",{"centerIP":$("#centerIP").val()},function(data){
						if(data == "true"){
							layer.msg('添加成功！', 1, -1);
							add_dialog.dialog("close");
							refresh();
						}else{
							messageDialog("添加失败！","fail");
						}
					});
				}
			});
		}
		function edit(){
			confirmDialog("确定要修改？",function(result){
				if(result){
					$.post("updateLinkIP",{
						"id":$("#id").val(),
						"centerIP":$("#centerIP").val()
					},function(data){
						if(data == "true"){
							layer.msg('修改成功！', 1, -1);
							edit_dialog.dialog("close");
							refresh();
						}else{
							messageDialog("修改失败！","fail");
						}
					});
				}
			});
		}
		function checkNull(){
			var ip=$("#centerIP").val();
			var exp=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
			var reg = ip.match(exp);
			if(ip==""){
				messageDialog("IP不能为空！","alert");
				return false;
			}else if(reg==null){
				messageDialog("输入的IP不合法！","alert");
				return false;
			}else{
				return true;
			}
		}
		function refresh(){
			$.post("linkUpIp", function(data) {
				jump("级联IP设置", data);
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
			<label style="float: left;width: 70px">序号：</label>
			<input id="id" type="text" style="width: 120px" disabled="disabled"
				class="text ui-widget-content ui-corner-all"/>
			<br> <br>
			<label style="float: left;width: 70px">级联IP：</label>
			<input id="centerIP" type="text" style="width: 120px"
				class="text ui-widget-content ui-corner-all"/>
			<br> <br>
			<label style="float: left;width: 70px">级联关系：</label>
			<input id="relation" type="text" style="width: 120px" disabled="disabled"
				class="text ui-widget-content ui-corner-all"/>
		</form>
	</div>
	<table id="linkUpIp"></table>
	<div id="pglist"></div>
</body>
</html>

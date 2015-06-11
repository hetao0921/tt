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
<script src="./layer/layer.min.js"></script>
<script type="text/javascript">
	
	jQuery("#list2").jqGrid({

		url : 'refresh',
		datatype : "json",
		mtype : "POST",

		colNames : [ '实际IP', '映射IP' ],
		colModel : [ {
			name : 'realIp',
			index : 'realIp',
			width : '40%',
			sorttype : "text",
			resizable : true,
		}, {
			name : 'VIP',
			index : 'VIP',
			width : '60%',
			sorttype : "text",
			resizable : true,
		} ],
		rowNum : 10,
		rowList : [ 10, 20, 30 ],
		pager : '#pager2',
		sortname : 'realIp',
		viewrecords : true,
		sortorder : "desc",
		autowidth : true,
		height : 'auto',
		pagerpos : 'right',
		recordpos : 'left',
		loadonce : true,
	// caption:"WSDL显示"
	});
	jQuery("#list2").jqGrid('navGrid', '#pager2', {
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : false,
	});
	$("#list2").closest(".ui-jqgrid-bdiv").css({
		'overflow-x' : 'hidden'
	});

	doResize();
	Event.onResizend(function() {
		doResize();
	});
	function doResize() {
		var mainContentHeight = $("#main-content").height();
		var mainContentWidth = $("#main-content").width();
		var mysearchHeight = $("#div_new").height();
		var tableHeadHeight = $("#jqgh_list2_realIp").height();
		var pagerHeight = $("#pager2").height();
		$("#list2").setGridHeight(
				mainContentHeight - mysearchHeight - pagerHeight
						- tableHeadHeight - 8);
		$("#list2").setGridWidth(mainContentWidth - 2);
	}
</script>
<script type="text/javascript">
	var add_dialog = null;

	var sURI;

	$('#add').click(function() {

		$("#modal-dialog-add").attr("title", "添加内容");
		createAddDialog();
		add_dialog.dialog("open");
	});

	$('#del').click(function() {
		var selectedId = $("#list2").jqGrid("getGridParam", "selrow");
		var rowData = $("#list2").jqGrid("getRowData", selectedId);
		var realIp = rowData.realIp;
		
		if (selectedId == null) {
			messageDialog("请选择一行数据！", "notice");
			return;
		}
		del(realIp);
	});

	function createAddDialog() {
		add_dialog = $("#modal-dialog-add").dialog({
			autoOpen : false,
			height : 200,
			width : 420,
			modal : true,
			buttons : {
				"保存" : function() {

					var realIp = $("#realIp").val();
					var VIP = $("#VIP").val();

					if (checkAdd(realIp, VIP)) {

						$.post("checkIP", {
							"realIp" : realIp,

						}, function(data) {

							if (data == "1") {
								messageDialog("真实IP地址已存在！", "alert");
							} else {
								add(realIp, VIP);
							}

						});
						//add(realIp,VIP);
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

	function add(realIp, VIP) {
		confirmDialog("确定要添加？", function(result) {
			if (result) {

				$.post("insert", {
					"realIp" : realIp,
					"VIP" : VIP
				}, function(data) {
					layer.msg('添加成功！', 1, -1);
					//messageDialog("添加成功！", "success");
					add_dialog.dialog("close");
					refresh();

				});
			}
		});
	}

	function del(realIp) {
		confirmDialog("确定要删除？", function(result) {
			if (result) {
				$.post("delrealIp", {
					"delrealIp" : realIp
				}, function(data) {

					//	messageDialog("删除成功！", "success");
					layer.msg('删除成功！', 1, -1);
					refresh();

				});
			}
		});
	}

	function checkAdd(realIp, VIP) {

		var exp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;

		var isip = realIp.match(exp);

		if (realIp == "") {
			messageDialog("真实IP地址不能为空！", "alert");
		} else if (VIP == "") {
			messageDialog("映射IP信息不能为空！", "alert");
		} else if (isip == null) {
			messageDialog("真实IP地址输入不合法！", "alert");
		}

		else {
			return true;
		}
		return false;
	}

	function refresh() {
		$.post("gapIp", function(data) {
			jump("网闸IP映射", data);
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
	<div id="div_new">


		<input id="add" type="button"
			class='ui-button ui-widget ui-state-default ui-corner-all' value="添加">
		<input id="del" type="button"
			class='ui-button ui-widget ui-state-default ui-corner-all' value="删除">
	</div>
	<table id="list2"></table>
	<br>
	<div id="pager2"></div>
	<div id="modal-dialog" style="display: none;">
		<form>
			<br> <br> <label style="float: left;width: 70px">URI地址：</label>
			<textarea id="uri" rows="2" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"></textarea>
			<br> <br> <label style="float: left;width: 70px">URI描述：</label>
			<textarea id="desc" rows="5" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"></textarea>
		</form>
	</div>

	<div id="modal-dialog-add" style="display: none;">
		<form>
			<br> <label style="float: left;width: 70px">真实IP：</label> <input
				id="realIp" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"> <br> <br>
			<label style="float: left;width: 70px">映射IP：</label> <input id="VIP"
				style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all">

		</form>


	</div>

</body>

</html>

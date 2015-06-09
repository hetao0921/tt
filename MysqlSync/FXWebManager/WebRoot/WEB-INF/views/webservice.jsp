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

<title>My JSP 'wsdl_show.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	jQuery("#list2").jqGrid({

		url : 'wsdl_show',
		datatype : "json",
		mtype : "POST",

		colNames : [ 'URI地址', 'URI描述' ],
		colModel : [ {
			name : 'wsdlURI',
			index : 'wsdlURI',
			width : '40%',
			sorttype : "text",
			resizable : true,
		}, {
			name : 'wsdlDesc',
			index : 'wsdlDesc',
			width : '60%',
			sorttype : "text",
			resizable : true,
		} ],
		rowNum : 20,
		rowList : [ 10, 20, 30 ],
		pager : '#pager2',
		sortname : 'wsdlURI',
		viewrecords : true,
		sortorder : "desc",
		width : '800px',
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
		var tableHeadHeight = $("#jqgh_list2_wsdlURI").height();
		var pagerHeight = $("#pager2").height();
		$("#list2").setGridHeight(
				mainContentHeight - mysearchHeight - pagerHeight
						- tableHeadHeight - 8);
		$("#list2").setGridWidth(mainContentWidth - 2);
	}
</script>
<script type="text/javascript">
	var wsdlURI = null;
	var add_dialog = null;
	var edit_dialog = null;
	$('#detail').click(function() {
		var selectedId = $("#list2").jqGrid("getGridParam", "selrow");
		var rowData = $("#list2").jqGrid("getRowData", selectedId);
		//alert(selectedId);
		wsdlURI = rowData.wsdlURI;

		if (selectedId == null) {
			messageDialog("请选择一行数据！", "notice");
			return;
		} else {
			//detail(wsdlURI);
			//wsdlURI=null;
			$.post("wsdlInfo", {
				"wsdlURI" : wsdlURI
			}, function(data) {
				jump("URI描述", data);
			});

		}

	});

	var sURI;
	$('#edit').click(function() {
		var selectedId = $("#list2").jqGrid("getGridParam", "selrow");
		var rowData = $("#list2").jqGrid("getRowData", selectedId);
		if (selectedId == null) {
			messageDialog("请选择一行数据！", "notice");
			return;
		} else {

			$("#uri").val(rowData.wsdlURI);
			$("#desc").val(rowData.wsdlDesc);

			sURI = rowData.wsdlURI;
			$("#modal-dialog").attr("title", "修改URI");
			createEditDialog(sURI);
			//$("#url").attr("readonly","readonly");
			edit_dialog.dialog("open");
		}

	});

	$('#add').click(function() {
		//getClasses(null);
		//getDeviceTypes(null);
		//getOutModels(null);
		$("#modal-dialog-add").attr("title", "添加URI地址");
		createAddDialog();
		add_dialog.dialog("open");
	});

	$('#del').click(function() {
		var selectedId = $("#list2").jqGrid("getGridParam", "selrow");
		var rowData = $("#list2").jqGrid("getRowData", selectedId);
		var wsdlURI = rowData.wsdlURI;
		//alert(selectedId);
		if (selectedId == null) {
			messageDialog("请选择一行数据！", "notice");
			return;
		}
		del(wsdlURI);
	});

	function createEditDialog(sURI) {
		edit_dialog = $("#modal-dialog").dialog({
			autoOpen : false,
			height : 350,
			width : 420,
			modal : true,
			buttons : {
				"修改" : function() {
					if (checkNull()) {

						edit(sURI);
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

	function createAddDialog() {
		add_dialog = $("#modal-dialog-add").dialog({
			autoOpen : false,
			height : 525,
			width : 420,
			modal : true,
			buttons : {
				"保存" : function() {
					if (checkAdd()) {
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

	function add() {
		confirmDialog("确定要添加？", function(result) {
			if (result) {
				$.post("addWsdl", {
					"wsdlUri" : $("#wsdlUri").val(),
					"wsdlDesc" : $("#wsdlDesc").val(),
					"functionName" : $("#functionName").val(),
					"functionDesc" : $("#functionDesc").val(),
					"arguments" : $("#arguments").val(),
					"results" : $("#results").val(),

				}, function(data) {
					if (1 == data) {
						messageDialog("添加成功！", "success");
						add_dialog.dialog("close");
						refresh();
					} else {
						messageDialog("添加失败！", "fail");
					}
				});
			}
		});
	}

	function edit(sURI) {
		//alert(sURI);
		confirmDialog("确定要修改？", function(result) {
			if (result) {
				$.post("edit_wsdl", {
					"sURI" : sURI,
					"uri" : $("#uri").val(),
					"desc" : $("#desc").val(),
				}, function(data) {
					//alert(data);
					if ("1" === data) {
						messageDialog("修改成功！", "success");
						edit_dialog.dialog("close");
						refresh();
					} else {
						messageDialog("修改失败！", "fail");
					}
				});
			}
		});
	}

	function del(wsdlURI) {
		confirmDialog("确定要删除？", function(result) {
			if (result) {
				$.post("delWsdl", {
					"wsdlURI" : wsdlURI
				}, function(data) {
					if ("1" == data) {
						messageDialog("删除成功！", "success");
						refresh();
					} else {
						messageDialog("删除失败！", "fail");
					}
				});
			}
		});
	}

	function checkNull() {
		var uri = $("#uri").val();

		if (uri == "") {
			messageDialog("URI地址不能为空！", "alert");
		} else {
			return true;
		}
		return false;
	}

	function checkAdd() {
		var wsdlUri = $("#wsdlUri").val();
		var obj = $("#list2").jqGrid("getRowData");
		var uriList = [];
		for ( var int = 1; int < obj.length + 1; int++) {
			var ret = $("#list2").jqGrid("getRowData", int).wsdlURI;
			uriList.push(ret);

		}

		if (wsdlUri == "") {
			messageDialog("URI地址不能为空！", "alert");
		} else if ($.inArray(wsdlUri, uriList) != -1) {
			messageDialog("URI地址已存在！", "alert");
		}

		else {
			return true;
		}
		return false;
	}

	function refresh() {
		$.post("webservice", function(data) {
			jump("webservice描述", data);
		});
	}
</script>


</head>

<body>
	<div id="div_new">
		<input id="detail" type="button"
			class='ui-button ui-widget ui-state-default ui-corner-all' value="查看">
		<input id="edit" type="button"
			class='ui-button ui-widget ui-state-default ui-corner-all' value="修改">
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
			<br> <label style="float: left;width: 70px">URI地址：</label>
			<textarea id="wsdlUri" rows="2" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"></textarea>
			<br>
			<br> <label style="float: left;width: 70px">URI描述：</label>
			<textarea id="wsdlDesc" rows="2" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"></textarea>
			<br>
			<br> <label style="float: left;width: 70px">方法名称：</label> <input
				id="functionName" type="text" style="width: 300px"
				class="text ui-widget-content ui-corner-all" /> <br>
			<br> <label style="float: left;width: 70px">方法描述：</label>
			<textarea id="functionDesc" rows="2" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"></textarea>
			<br>
			<br> <label style="float: left;width: 70px">参数：</label>
			<textarea id="arguments" rows="2" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"></textarea>
			<br>
			<br> <label style="float: left;width: 70px">结果集：</label>
			<textarea id="results" rows="2" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"></textarea>
		</form>


	</div>

</body>
</html>

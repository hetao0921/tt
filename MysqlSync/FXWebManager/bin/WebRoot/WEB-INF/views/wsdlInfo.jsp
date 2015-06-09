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

<title>My JSP 'wsdlInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">

var wsdlURI = "${wsdlURI}";
	$(document).ready(function() {

		 wsdlURI = "${wsdlURI}";

		$.post("selectByWsdlURI", {
			"wsdlURI" : wsdlURI
		}, function(data) {

			var myjsongrid = eval("(" + data + ")");
			jqtable[0].addJSONData(myjsongrid);
		});
	});

	var jqtable = jQuery("#list3").jqGrid(
			{
				//	url : 'selectByWsdlURI',
				//postData: { wsdlURI: wsdlURI } ,
				datatype : "json",
				mtype : "POST",

				colNames : [ 'wsdlInfoId','URI地址', '方法名称', '方法描述',
						'参数', '结果集' ],
				colModel : [ {
					name : 'wsdlInfoId',
					index : 'wsdlInfoId',
					//width : '20%',
					//sorttype : "text",
					resizable : true,
					hidden:true
				},{
					name : 'wsdlURI',
					index : 'wsdlURI',
					width : '20%',
					sorttype : "text",
					resizable : true,
				}, {
					name : 'functionName',
					index : 'functionName',
					width : '20%',
					sorttype : "text",
					resizable : true,
				}, {
					name : 'functionDesc',
					index : 'functionDesc',
					width : '20%',
					sorttype : "text",
					resizable : true,
				}, {
					name : 'arguments',
					index : 'arguments',
					width : '20%',
					sorttype : "text",
					resizable : true,
				}, {
					name : 'results',
					index : 'results',
					width : '20%',
					sorttype : "text",
					resizable : true,
				} ],
				rowNum : 20,
				rowList : [ 10, 20, 30 ],
				pager : '#pager3',
				//sortname : 'wsdlInfoId',
				//sortable : true,
				viewrecords : true,
				//sortorder : "desc",
				autowidth : true,
				//height : 'auto',
				loadonce : true,
			//caption:"WSDL显示"
			});
	jQuery("#list3").jqGrid('navGrid', '#pager3', {
		edit : false,
		add : false,
		del : false,
		search : false,
		refresh : false,
	});

	function back() {
		$.post("webservice", function(data) {
			jump("webservice描述", data);
		});
	}
	
	$("#list3").closest(".ui-jqgrid-bdiv").css({
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
		var tableHeadHeight = $("#jqgh_list3_wsdlURI").height();
		var pagerHeight = $("#pager3").height();
		$("#list3").setGridHeight(
				mainContentHeight - mysearchHeight - pagerHeight
						- tableHeadHeight - 8);
		$("#list3").setGridWidth(mainContentWidth - 2);
	}
</script>

<script type="text/javascript">
	var wsdlInfoId;
	
	$('#edit').click(function() {
		var selectedId = $("#list3").jqGrid("getGridParam", "selrow");
		var rowData = $("#list3").jqGrid("getRowData", selectedId);
		if (selectedId == null) {
			messageDialog("请选择一行数据！", "notice");
			return;
		} else {
			
			
			$("#functionName").val(rowData.functionName);
			$("#functionDesc").val(rowData.functionDesc);
			$("#arguments").val(rowData.arguments);
			$("#results").val(rowData.results);
			
			wsdlInfoId = rowData.wsdlInfoId;
			//alert(wsdlInfoId);
		
			$("#modal-dialog").attr("title", "修改详细参数");
			createEditDialog(wsdlInfoId);
			//$("#url").attr("readonly","readonly");
			edit_dialog.dialog("open");
		}

	});
	
	$('#add').click(function() {
	
			//getClasses(null);
			//getDeviceTypes(null);
			//getOutModels(null);
			$("#modal-dialog").attr("title","添加方法");
			createAddDialog();
			add_dialog.dialog("open");
		});
		
		$('#del').click(function(){
			var selectedId = $("#list3").jqGrid("getGridParam", "selrow");
			var rowData = $("#list3").jqGrid("getRowData", selectedId);
			var ID=rowData.wsdlInfoId;
			//alert(selectedId);
			if(selectedId == null){
				messageDialog("请选择一行数据！","notice");
				return;
			}
			del(ID);
		});
		
		function del(ID){
			confirmDialog("确定要删除？",function(result){
				if(result){
					$.post("delWsdlInfo",{"ID":ID},function(data){
						if ("1" == data) {
							messageDialog("删除成功！","success");
							refresh();
						} else {
							messageDialog("删除失败！","fail");
						}
					});
				}
			});
		}
		function createAddDialog(){
			add_dialog = $("#modal-dialog").dialog({
				autoOpen : false,
				height : 405,
				width : 420,
				modal : true,
				buttons : {
					"保存" : function() {
						if(checkNull()){
						 wsdlURI = "${wsdlURI}";
						// alert(wsdlURI);
							add(wsdlURI);
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
		
		function add(wsdlURI){
			confirmDialog("确定要添加？",function(result){
				if(result){
					$.post("addWsdlInfo", {
						"wsdlUri" :wsdlURI,
						
						"functionName" : $("#functionName").val(),
						"functionDesc" : $("#functionDesc").val(),
						"arguments" : $("#arguments").val(),
						"results" : $("#results").val(),
						
						
					}, function(data) {
						if ("1" == data) {
							messageDialog("添加成功！","success");
							add_dialog.dialog("close");
							refresh();
						} else {
							messageDialog("添加失败！","fail");
						}
					});
				}
			});
		}

	function createEditDialog(wsdlInfoId) {
		edit_dialog = $("#modal-dialog").dialog({
			autoOpen : false,
			height : 405,
			width : 420,
			modal : true,
			buttons : {
				"修改" : function() {
					if (checkNull()) {

						editInfo(wsdlInfoId);
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

	function editInfo(wsdlInfoId) {
		confirmDialog("确定要修改？", function(result) {
			if (result) {
				$.post("edit_wsdlInfo", {
					
					"wsdlInfoId" : wsdlInfoId,
					"functionName" : $("#functionName").val(),
					"functionDesc" : $("#functionDesc").val(),
					"arguments" : $("#arguments").val(),
					"results" : $("#results").val(),
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

	function checkNull() {
		var functionName = $("#functionName").val();
		//var desc = $("#desc").val();
		//var reg = /(https?|ftp|mms):\/\/([A-z0-9]+[_\-]?[A-z0-9]+\.)*[A-z0-9]+\-?[A-z0-9]+\.[A-z]{2,}(\/.*)*\/?/;
		if (functionName == "") {
			messageDialog("方法名称不能为空！", "alert");
		} else {
			return true;
		}
		return false;
	}

	function refresh() {
		$.post("wsdlInfo",{"wsdlURI":wsdlURI},function(data) {
			jump("wsdlInfo",data);
			
		});
	}
</script>


</head>

<body>
<div id="div_new">
<input id="add" type="button"
    class='ui-button ui-widget ui-state-default ui-corner-all' value="添加方法">
	<input id="edit" type="button"
		class='ui-button ui-widget ui-state-default ui-corner-all' value="修改方法">
		<input id="del" type="button" 
		class='ui-button ui-widget ui-state-default ui-corner-all' value="删除方法">
	<input id="back" type="button"
		class='ui-button ui-widget ui-state-default ui-corner-all' value="返回"
		onclick="back()"></div>
	<table id="list3"></table>
	<br>
	<div id="pager3"></div>
	<br>
	<div id="modal-dialog" style="display: none;">
		<form>


			<br> <br> <label style="float: left;width: 70px">方法名称：</label>
			
			<input id="functionName" type="text" style="width: 300px"
				class="text ui-widget-content ui-corner-all"/><br> <br> 
			<label style="float: left;width: 70px">方法描述：</label>
			<textarea id="functionDesc" rows="2" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"></textarea><br> <br> 
			<label style="float: left;width: 70px">参数：</label>
			<textarea id="arguments" rows="2" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"></textarea><br> <br> 
			<label style="float: left;width: 70px"> 结果集：</label>
			<textarea id="results" rows="2" style="resize:none;width: 300px"
				class="text ui-widget-content ui-corner-all"></textarea><br> <br> 
		</form>


	</div>
</body>
</html>

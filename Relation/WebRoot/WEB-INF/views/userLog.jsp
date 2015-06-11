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

<title>用户日志</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script src="./js/fxdigital.js"></script>
<script src="./layer/layer.min.js"></script>

<script type="text/javascript">
	$(function() {
		jQuery("#jqGrid").jqGrid({
			url : 'getUserLog',
			datatype : "json",
			mtype : "POST",
			autowidth : false,
			shrinkToFit : true,
			colNames : [ '创建时间', '内容', '类型', '用户名称', '客户端ID' ],
			colModel : [ {
				name : 'time',
				index : 'time',
				width : '20%',
				sorttype : "text",
				resizable : true,
			}, {
				name : 'content',
				index : 'content',
				width : '40%',
				sorttype : "text",
				resizable : true,
			}, {
				name : 'type',
				index : 'type',
				width : '10%',
				sorttype : "text",
				resizable : true,
			}, {
				name : 'userName',
				index : 'userName',
				width : '10%',
				sortable : false,
				resizable : true,
			}, {
				name : 'clientId',
				index : 'clientId',
				width : '20%',
				sorttype : "text",
				resizable : true,
			} ],
			rowNum : 20,
			rowList : [ 10, 20, 30 ],
			pager : '#plist47',
			pagerpos : 'right',
			recordpos : 'left',
			viewrecords : true,
			ondblClickRow : function(rowid,iRow,iCol,e){dblClickRow(rowid,iRow,iCol,e);}
		}).navGrid('#plist47', {
			add : false,
			edit : false,
			del : false,
			search : false,
			refresh : false,
		});
		
		var show_dialog = null;
		function dblClickRow(rowid,iRow,iCol,e){
			var rowData = $("#jqGrid").jqGrid("getRowData", rowid);
			$("#time2").val(rowData.time);
			$("#type2").val(rowData.type);
			$("#userName2").val(rowData.userName);
			$("#clientID2").val(rowData.clientId);
			$("#content2").val(rowData.content);
			$("#modal-dialog").attr("title","用户日志详细信息");
			createShowDialog();
			show_dialog.dialog("open");
		}
		function createShowDialog(){
			show_dialog = $("#modal-dialog").dialog({
				autoOpen : false,
				height : 380,
				width : 420,
				modal : true,
				buttons : {
					"关闭" : function() {
						show_dialog.dialog("close");
					}
				},
				close : function() {
					show_dialog.find("form")[0].reset();
					show_dialog.dialog("destroy");
				}
			});
		}
		
		$("#mysearch").filterGrid('#jqGrid', {
			formtype : 'auto',
			autosearch : false,
			buttonclass : "searchButtonClass",
			searchButton : "检索",
			clearButton : "清空",
			enableSearch : true,
			enableClear : true,
			url : 'getUserLog',
			formtypeDetail : [3,2],
			filterModel : [ {
				label : '开始时间：',
				name : 'startLogTime',
				stype : 'text',
				defval : '',
			}, {
				label : '结束时间：',
				name : 'endLogTime',
				stype : 'text',
				defval : '',
			}, {
				label : '类型：',
				name : 'type',
				stype : 'select',
				defval : '',
				sopt : {
					value : getTypes()
				}
			}, {
				label : '用户名称：',
				name : 'userName',
				stype : 'text',
				defval : '',
			}, {
				label : '客户端ID：',
				name : 'clientID',
				stype : 'text',
				defval : '',
			} ],
		});

		function getTypes() {
			var str = "";
			$.ajax({
				type : "post",
				url : "getType",
				async : false,
				success : function(data) {
					if (data != null && data != "false") {
						var types = eval(data);
						var length = types.length;
						$.each(types, function(i) {
							if(i!=length-1){
								str += types[i] + ":" + types[i] + ";";
							}else{
								str += types[i] + ":" + types[i];
							}
						});
					}else{
						messageDialog("获取类型失败！","fail");
					}
				}
			});
			return str;
		}

		doResize();
		Event.onResizend(function() {
			doResize();
		});
		function doResize() {
			var mainContentHeight = $("#main-content").height();
			var mainContentWidth = $("#main-content").width();
			var mysearchHeight = $("#mysearch").height();
			var tableHeadHeight = $("#jqGrid_time").height();
			var pagerHeight = $("#plist47").height();
			$("#jqGrid").setGridHeight(
					mainContentHeight - mysearchHeight - pagerHeight
							- tableHeadHeight - 8);
			$("#jqGrid").setGridWidth(mainContentWidth - 2);
		}

		$("input[name='startLogTime']").datetimepicker({
			controlType : 'select',
			timeFormat : 'HH:mm:ss',
		});
		$("input[name='startLogTime']").attr("readonly", "readonly");
		$("input[name='endLogTime']").datetimepicker({
			controlType : 'select',
			timeFormat : 'HH:mm:ss',
		});
		$("input[name='endLogTime']").attr("readonly", "readonly");
		$(".searchButtonClass").button();
		//$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ 'overflow-y' : 'scroll' });
		$("#jqGrid").closest(".ui-jqgrid-bdiv").css({
			'overflow-x' : 'hidden'
		});
		//$(".ui-pg-table").css("table-layout","auto");
		$("table[class='ui-pg-table'] td").css("width", "auto");
		//$("#plist47_right").css("width","auto");
	});
</script>
<style type="text/css">
.filtertable {
	font-size: 14px;
}
</style>
</head>

<body>
	<div>
		<div id="mysearch" style="border:#c6c6c6 solid 1px;padding:10px;margin-bottom: 10px"></div>
	</div>
	<table id="jqGrid"></table>
	<div id="plist47"></div>
	<div id="modal-dialog" style="display: none;">
		<form>
			<br>
			<label style="float: left;width: 70px">创建时间：</label>
			<input id="time2" type="text" readonly="readonly"/>
			<br> <br>
			<label style="float: left;width: 70px">类型：</label>
			<input id="type2" type="text" readonly="readonly"/>
			<br> <br>
			<label style="float: left;width: 70px">用户名称：</label>
			<input id="userName2" type="text" readonly="readonly"/>
			<br> <br>
			<label style="float: left;width: 70px">客户端ID：</label>
			<input id="clientID2" type="text" readonly="readonly"/>
			<br> <br>
			<label style="float: left;width: 70px">内容：</label>
			<textarea id="content2" rows="5"  readonly="readonly"
				style="resize:none;width: 300px"></textarea>
		</form>
	</div>
</body>
</html>

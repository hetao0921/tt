var id='';
var lastsel3;
$(function() {
	jQuery("#jqGrid").jqGrid(
			{
				url : 'getDeviceVisitModel',
				datatype : "json",
				mtype : "POST",
				autowidth : true,
				colNames : [ "编号",'设备名称', 'IP',"设备类型","netlinkmode", '传输协议'],
				colModel : [ {
					name : 'deviceId',
					index : 'deviceId',
					width : '30%',
					sorttype : "text",
					resizable : true,
					search:false
				}, {
					name : 'devName',
					index : 'devName',
					width : '20%',
					sorttype : "text",
					resizable : true
				}, {
					name : 'devIP',
					index : 'devIP',
					width : '20%',
					sorttype : "text",
					resizable : true
				}, {
					name : 'type',
					index : 'type',
					width : '10%',
					sorttype : "text",
					resizable : true
				}, {
					name : 'netlinkmode',
					index : 'netlinkmode',
					width : '10%',
					sorttype : "text",
					resizable : true,
					search:false,
					hidden:true
				},{
					name : 'modelName',
					index : 'modelName',
					width : '10%',
					editable:true,edittype:"select",
					editoptions:{value:"0:TCP;1:UDP;2:组播;-1:未设置"},
//					sorttype : "text",
/*					dataEvents:[
					            {type:'change',
					            	fn:function(e){

					    				alert('1');
					    			var selectedId = $("#jqGrid").jqGrid("getGridParam", "selrow");
					    				var rowData = $("#jqGrid").jqGrid("getRowData", selectedId);
					    				
					    				var deviceId=rowData.deviceId;
					    				var netlinkmode=rowData.netlinkmode;
					    				alert(deviceId);
					    				alert(netlinkmode);
					    				confirmDialog("确定要修改？",function(result){
					    					if(result){
					    						$.post("addModel", {
					    							"deviceId" : deviceId,
					    							"netlinkmode" : netlinkmode
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
					    			
					}}],*/
					resizable : true
				
				}, ],
					 /*search : {      
						    
					     caption: "Search...",      
					    
					     Find: "Find",      
					    
					     Reset: "Reset",      
					    
					     odata : ['equal', 'not equal', 'less', 'less or equal','greater','greater or equal', 'begins with','does not begin with','is in','is not in','ends with','does not end with','contains','does not contain'],      
					    
					     groupOps: [ { op: "AND", text: "all" }, { op: "OR", text: "any" } ],      
					    
					     matchText: " match",      
					    
					     rulesText: " rules"      
					    
					   } ,*/
					rowNum : 10,
					rowList : [ 10, 20, 30 ],
					pager : '#plist47',
					pagerpos : 'right',
					recordpos : 'left',
					width : '800px',
					height : 'auto',
					//sortname : 'id',
					viewrecords : true,
					sortorder : "desc",
					loadonce : true,
					multiselect: false,
				//caption : "测试实例"
//				ondblClickRow : function(rowid,iRow,iCol,e){dblClickRow(rowid,iRow,iCol,e);},
				onSelectRow: function(id){
//					alert("id="+id);
//					alert("lastsel3="+lastsel3);
//					if(id && id!==lastsel3){
						jQuery('#jqGrid').jqGrid('restoreRow',lastsel3);
						jQuery('#jqGrid').jqGrid('editRow',id,true);
						$(".editable").change(function(){
							selectClickRow(this);
						});	
						lastsel3=id;
//						selectClickRow(id);
//					}
				},
				}).navGrid('#plist47', {
			add : false,
			edit : false,
			del : false,
			search : false,
			refresh : false
		});
		
		$("#mysearch").filterGrid('#jqGrid', {
			formtype : 'auto',
			autosearch : false,
			buttonclass : "searchButtonClass",
			searchButton : "检索",
			clearButton : "清空",
			enableSearch : true,
			enableClear : true,
			url : 'getDeviceVisitModel',
			formtypeDetail : [2,2],
			filterModel : [  {
				label : '视频流名称：',
				name : 'devName',
				stype : 'text',
				defval : ''
			}, {
				label : 'IP：',
				name : 'devIP',
				stype : 'text',
				defval : ''
			}, {
				label : '协议名称：',
				name : 'modelName',
				stype : 'text',
				defval : ''
			}, {
				label : '流类型：',
				name : 'type',
				stype : 'text',
				defval : ''
			} ]
		});
		
		//$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ 'overflow-y' : 'scroll' });
		$("#plist47").closest(".ui-jqgrid-bdiv").css({
			'overflow-x' : 'hidden'
		});
		$(".searchButtonClass").button();
//		jQuery("#jqGrid").filterToolbar("autosearch");  
//		$("#plist47").closest(".ui-jqgrid-bdiv").css({
//			'overflow-x' : 'hidden'
//		});
		doResize();
		Event.onResizend(function() {
			doResize();
		});
		
		
		function doResize() {
			var mainContentHeight = $("#main-content").height();
			var mainContentWidth = $("#main-content").width();
			var buttonsHeight = $("#big").height();
			var tableHeadHeight = $("#jqgh_jqGrid_deviceId").height();
			var pagerHeight = $("#plist47").height();
			$("#jqGrid").setGridHeight(mainContentHeight - buttonsHeight-pagerHeight-tableHeadHeight-10);
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
		function createEditDialog(deviceId,netlinkmode){
			edit_dialog = $("#modal-dialog").dialog({
				autoOpen : false,
				height : 420,
				width : 450,
				modal : true,
				buttons : {
					"修改" : function() {
						if(checkNull(netlinkmode)){
							edit(deviceId);
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
		
			function dblClickRow(rowid,iRow,iCol,e){

			var selectedId = $("#jqGrid").jqGrid("getGridParam", "selrow");
			var netlinkmode= $("#jqGrid").getCell(selectedId,'netlinkmode');
			var rowData = $("#jqGrid").jqGrid("getRowData", selectedId);
			if(selectedId == null){
				messageDialog("请选择一行数据！","notice");
				return;
			}
			 id=selectedId;
			$("#type").val(rowData.type);
			$("#devName").val(rowData.devName);
			$("#devIP").val(rowData.devIP);
			$("#netlinkmode").val(rowData.netlinkmode);
			var deviceId=rowData.deviceId;
			var netlinkmode=rowData.netlinkmode;
			$("#modal-dialog").attr("title","修改视频流模式");
			createEditDialog(deviceId,netlinkmode);
			edit_dialog.dialog("open");
		}
			
			function selectClickRow(obj){
			var selectedId = $("#jqGrid").jqGrid("getGridParam", "selrow");
				var rowData = $("#jqGrid").jqGrid("getRowData", selectedId);
				
				var deviceId=rowData.deviceId;
				var netlinkmode=obj.value;
//				alert("deviceId="+deviceId);
//				alert("netlinkmode="+netlinkmode);
				confirmDialog("确定要修改？",function(result){
					if(result){
						$.post("addModel", {
							"deviceId" : deviceId,
							"netlinkmode" : netlinkmode
						}, function(data) {
							if ("true" == data) {
//								layer.msg('添加成功！', 1, -1);
								messageDialog("修改成功！","true");
								$("#jqGrid").setRowData(selectedId,{"netlinkmode":netlinkmode},{"background":"YELLOW"});
//								add_dialog.dialog("close");
//								refresh();
							} else {
								messageDialog("修改失败！","fail");
							}
						});
					}
				});
			}


		$('#del').click(function(){
			var selectedId = $("#jqGrid").jqGrid("getGridParam", "selrow");
		
			if(selectedId == null){
				messageDialog("请选择一行数据！","notice");
				return;
			}
			var rowData = $("#jqGrid").jqGrid("getRowData", selectedId);
			
			var type=rowData.netlinkmode;
			if(type=="-1"){
				messageDialog("未设置","notice");
				return;
			}
			del(rowData.deviceId);
		});
		$('#edit').click(function(){
			var selectedId = $("#jqGrid").jqGrid("getGridParam", "selrow");
			var rowData = $("#jqGrid").jqGrid("getRowData", selectedId);
			if(selectedId == null){
				messageDialog("请选择一行数据！","notice");
				return;
			}
			$("#type").val(rowData.type);
			$("#devName").val(rowData.devName);
			$("#devIP").val(rowData.devIP);
			$("#netlinkmode").val(rowData.netlinkmode);
			var deviceId=rowData.deviceId;
			var netlinkmode=rowData.netlinkmode;
			$("#modal-dialog").attr("title","修改视频流模式");
			createEditDialog(deviceId,netlinkmode);
			edit_dialog.dialog("open");
		});
		$('#add').click(function() {
			
			
			$("#modal-dialog").attr("title","添加视频流模式");

		
			createAddDialog();
			add_dialog.dialog("open");
		});
		function add(){
			confirmDialog("确定要添加？",function(result){
				if(result){
					$.post("addModel", {
						"deviceid" : $("#deviceList").val(),
						"modelType" : $("#modelType").val()
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
		function edit(deviceId){
			var modelType=$("#netlinkmode").val();
			confirmDialog("确定要修改？",function(result){
				if(result){
					$.post("addModel", {
						"deviceId" : deviceId,
						"netlinkmode" : $("#netlinkmode").val()
				
					}, function(data) {
						if ("true" == data) {
							layer.msg('修改成功！', 1, -1);
							edit_dialog.dialog("close");
							if(id!=''){
								if(modelType=="0"){
									model="TCP";
								}
								if(modelType=="1"){
									model="UDP";
								}
								if(modelType=="2"){
									model="组播";
								}
								$("#jqGrid").setRowData(id,{"modelName":model,"netlinkmode":modelType},{"background":"YELLOW"});
							}
//							refresh();
						} else {
							messageDialog("修改失败！","fail");
						}
						id='';
						
					});
				}
			});
		}
		function del(selectedId){
			confirmDialog("确定要删除？",function(result){
				if(result){
					$.post("delModel",{"deviceid":selectedId},function(data){
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
			$.post("deviceVisitModel", function(data) {
				jump("解码矩阵设置", data);
			});
		}
		function checkNull(netlinkmode) {
	
			var modelType=$("#netlinkmode").val();
			if(modelType=="-1"){
				return false;
			}
			if(modelType==netlinkmode){
				return false;
			}
		return true;	
		}
	});

function getDeviceList (obj){
	if(obj=="null"){
		return;
	}
	$.post("getDevice",{"type":obj}, function(data) {
		var htmls = "<option value='null'>请选择</option>";
		$.each($.parseJSON(data), function(i, n) {
			htmls += "<option value='" + n.DeviceID + "'  >";
			htmls += n["Devname"];
			htmls += "</option>";
			
		});
//		alert(htmls);
		$("#deviceList").html(htmls);
	});
	

}


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
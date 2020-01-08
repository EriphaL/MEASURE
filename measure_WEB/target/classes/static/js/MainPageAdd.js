var flag=1;//用与判断保存按钮调用的方法
var kztxxGroupId=1;
var groupId=1;//分组id
var kztBh=1;//控制图编号
var kztList_id;//控制图列表id
var kzt_Bt="控制图管理";//初始化表标题
var public_Url;//公共controller
var flags=1;
var startArr=[];//存放控制图数据点勾选state  修改时
var startArr_Add=[];//新增时
var valArr=[];//用于存放备选值value
var flagx=1;//控制控制图层次信息和数据点层次信息模态框
var selectData="";//用于存放控制图层次信息和数据点层次信息的勾选值
var dataStr="";//用于拼接存放控制图层次信息和数据点层次信息的勾选值
var selectData1;//增加时使用
var selectData2;
var selectData11;//修改时使用
var selectData22;
var Kztsjd_str=[];//用于保存控制图层次信息输入框内的勾选信息显示
var kzt_py_id;//用于存放新增后返回的控制图id
var kztListData;
var start_pygz="";

var checkedFlag = 0; //判断是否需要选中

$(document).ready(function() {//当没有选中结点时，不显示修改，删除，新增控制图
	setTimeout(function(){
		showTree();
		$("#addLevel").show();
		$("#addLeaf").hide();
		$("#modify").hide();
		$("#remove").hide();
		$("#mainAdd").hide();
		
	}, 150);
});
showTypeLevel();

function searchType(url,i){//调用查询备选值接口
	$.ajax({
		url:url+"search.action",
        type: "GET",
        dataType:"JSON",
        data:{},
        success: function(data) {
        	if(data.code == "0000"){
//        		console.log(data);
        		var str = i+1;
        		eval("level"+str+'='+'(data.rows)');//level保存所有备选值
        		$("#level"+str).html(eval("level"+str));
        	}else{
        		toastr.error(data.message);
        	}
        },
        error: function(data) {
        	toastr.error(data.message);
        },
	});
}

function showBXZ(){//选项显示备选值
	for(var i=0;i<9;i++){
		$("#level"+(1+i)).html("");;
	}
	for(var i=0;i<9;i++){
		var arr=eval("level"+(i+1));
		var html="";
//		console.log(arr.length);
		for(var j=0;j<arr.length;j++){
//			console.log(arr[j]);
			html += "<option value='"+arr[j].id+"'>"+arr[j].name+"</option>";
		}
//		console.log(html);
//		$("#level"+(1+i)).html(html);//给选项赋值
		$("#level"+(1+i)).append(html);
	}	
}

function showTypeLevel(){
	$.ajax({
		url:"controlChartMessage/searchTypeLevelName.action",
        type: "GET",
        dataType:"JSON",
        data: {},
        success: function(data) {
        	if(data.success == "0000"){
        		console.log(data);
        		for(var i=0;i<9;i++){
        			var a=eval("data.cc"+(i+1));//用a保存data里面cc1到9的值
        			if(a!=""){//层次名称不为空，则查询他的备选值
        				var head={
        						field:'cc' + (i+1),
        						title:a,
        						align:"center",
        						valign:"middle",
        						width:100
        						};
	        			shuzu.splice(5+i,0,head);//从第五个开始插入表头
	        			var str="cc"+(i+1);
	        			$("#"+str).html(a+"：");//给控制图层次信息赋值
	        			switch(i){
		        			case 0:
		        				public_Url="productModel/";
		        			  break;
		        			case 1:
		        				public_Url="productName/";
		        			  break;
		        			case 2:
		        				public_Url="productLine/";
		        			  break;
		        			case 3:
		        				public_Url="productRank/";
		        			  break;
		        			case 4:
		        				public_Url="equipment/";
		        			  break;
		        			case 5:
		        				public_Url="supplier/";
		        			  break;
		        			case 6:
		        				public_Url="client/";
		        			  break;
		        			case 7:
		        				public_Url="batch/";
		        			  break;
		        			case 8:
		        				public_Url="workNumber/";
	        			  break;
	        			}
	        			searchType(public_Url,i);
        			}	
        		}	        		
//        		console.log(shuzu);
//        		showKztList();
//        		$('#kztList').bootstrapTable('refresh');
        	}else{
        		toastr.error(data.message);
        	}
        },
        error: function(data) {
        	toastr.error(data.message);
        },
	});
}

/********************************主页面****************************/
// var zTreePublic, rMenuPublic;
// function showTree(){
// 	$(document).ready(function() {
// 		$(document).ready(function() {
// 			$.ajax({
// 				url : "group/searchTree.action",
// 				type : 'GET',
// 				dataType : 'jsonp',
// 				data : {},
// 				success : function(data) {
// 					console.log(data);
// 					//加载有数据的树形
// 					var treeObj = $.fn.zTree.init($("#menu"), setting, data.rows);
// 					zTreePublic = $.fn.zTree.getZTreeObj("menu");
// 					fillter(treeObj);//设置展开前三级
// //					zTreePublic.expandAll(true);//默认展开全部结构
// 					rMenuPublic = $("#rMenu");
// 					$("#menu").css({height:($(".main_left>div").height()-26)+"px"});
// 					groupId = data.rows[0].id;
// 					setTimeout(function(){
// 						showKztList();
// 					}, 150);
					
// 				},
// 				error:function(data){
// 				},xhrFields: { withCredentials: true }
// 			})
// 		});
// 	});	
// }

function fillter(treeObj) {
	//获得树形图对象
	var nodeList = treeObj.getNodes(); //展开第一个根节点
	for(var i = 0; i < nodeList.length; i++) { //设置节点展开第二级节点
		treeObj.expandNode(nodeList[i], true, false, true);
		var nodespan = nodeList[i].children;
		for(var j = 0; j < nodespan.length; j++) { //设置节点展开第三级节点
			treeObj.expandNode(nodespan[j], true, false, true);
		}
	}
}

//配置树形
var setting = {
	async : {
		enable : true,
//			url : zTreeUrl(),
//		    type : "GET",
//			dataFilter : filter
	},
	view : {
		selectedMulti : false,
		showIcon : false,
	    fontCss : {"color":"black"},
	},
	edit : {
		enable : true,
		showRemoveBtn : false,
		showRenameBtn : false
	},
	data : {
		keep: { parent:true, leaf:true }, 
		simpleData : {
			enable : true,
		}
	},
	callback : {
		onClick : onClick,
		onRightClick: OnRightClick
//		onAsyncSuccess: zTreeOnAsyncSuccess
	}
};

//右击事件
function OnRightClick(event, treeId, treeNode) {
	if(!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
		//点击空白区域逻辑
//		zTree.cancelSelectedNode();
		showRMenu("root", event.clientX, event.clientY);
	} else if(treeNode && !treeNode.noR) {
		//点击节点逻辑
		zTreePublic.selectNode(treeNode);
		showRMenu("node", event.clientX, event.clientY);
	}
}

//显示右键菜单
function showRMenu(type, x, y) {
	$("#rMenu ul").show();
	if(type == "root") {
		var nodes = zTreePublic.getSelectedNodes();
		if(nodes && nodes.length > 0) {
//			zTree.checkNode(nodes[0], checked, true);
			
			$("#m_addE").show();
			$("#m_addN").show();
			$("#m_update").show();
			$("#m_del").show();
			$("#m_cancel").show();
		}else{
			$("#m_addE").show();
			$("#m_addN").show();
			$("#m_update").hide();
			$("#m_del").hide();
			$("#m_cancel").hide();
		}
	} else {
			$("#m_addE").show();
			$("#m_addN").show();
			$("#m_update").show();
			$("#m_del").show();
			$("#m_cancel").show();
	}
	
	console.log(y,document.body.scrollTop,$(window).height());
	y += document.body.scrollTop;
	x += document.body.scrollLeft;
	
	if(y>$(window).height()-50-$("#rMenu").height()){
		y-=$("#rMenu").height();
	}
	
	rMenuPublic.css({
		"top": y + "px",
		"left": x + "px",
		"display": "block"
	});

	$("body").bind("mousedown", onBodyMouseDown);
}

function hideRMenu() {
	if(rMenuPublic)
	rMenuPublic.css({
		"display": "none"
	});
	$("body").unbind("mousedown", onBodyMouseDown);
}

function onBodyMouseDown(event) {
	if(!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
		rMenuPublic.css({
			"display": "none"
		});
	}
}

//点击新增同级
function addEqualNode() {
	hideRMenu();
	$("#myModalTwo").modal('show');
}

//点击新增下级
function addNextNode() {
	hideRMenu();
	$("#myModalThree").modal('show');
}

//点击修改节点
function updateNode(){
	hideRMenu();
	var zTree = $.fn.zTree.getZTreeObj("menu")
	nodes = zTree.getSelectedNodes(),
	treeNode = nodes[0];
	$("#modifyText").val(treeNode.name);
	$("#myModalOne").modal('show');	
}

//点击删除节点
function removeNode(){
	hideRMenu();
	var zTree = $.fn.zTree.getZTreeObj("menu");//获取zTree对象
	nodes = zTree.getSelectedNodes();
	treeNode = nodes[0];
	if (nodes[0].children != undefined) {//判断该节点是否有下级目录
		if(nodes[0].children==""){
			Showbo.Msg.confirm('确认删除？',function(btn){
				if(btn == "yes"){
					var data = {};
					data.fzId = treeNode.id;
					data.fzExtend2 = 0;
					removeSure(data);//调用方法，传data
				}
			})	
			return;
		} else{
			toastr.warning("该分类已有下级不能删除！！！");
			return;
		}
	}
	Showbo.Msg.confirm('确认删除？',function(btn){
		if(btn == "yes"){
			var data = {};
			data.fzId = treeNode.id;
			data.fzExtend2 = 0;
			removeSure(data);
		}
	})
}

//点击取消节点
function cancelNode(){
	hideRMenu();
	zTreePublic.cancelSelectedNode();
	$("#mainAdd").hide();
}	

/*
 * @Description: 点击节点事件
 * @param : event	js event对象
 * @param : treeId	对应zTree的treeId
 * @param : treeNode	被点击的节点JSON数据对象
 * @param : clickFlag	节点被单击后的选中类型
 */
function onClick(event, treeId, treeNode, clickFlag) {//鼠标点击方法
	console.log(treeNode);
	if(treeId == "menu"){
		$('#kztList').bootstrapTable('destroy');
		menuName=treeNode.name;
		sblxstate=2;
		pId = treeNode.pId; 
		id = parseInt(treeNode.id);
		groupId=parseInt(treeNode.id);//分组id （控制图方法）
		showKztList();
		$("#mainAdd").show();
		$("#kztBt").html(treeNode.name);//根据点击的zTree节点更新表头
		btIndex=undefined;
	}
}

/**删除*/
function removeSure(data){//点击确认删除后调用接口
	var zTree = $.fn.zTree.getZTreeObj("menu");
	nodes = zTree.getSelectedNodes(),
	treeNode = nodes[0];
	$.ajax({
		url:"group/update.action",
        type: "POST",
        dataType:"JSON",
        data : data,
        success: function(data) {
        	if(data.code=="0000"){
        		toastr.success(data.message);
        		zTree.removeNode(treeNode);
        	}else{
        		toastr.warning(data.message);
        	}
        },
        error: function(request) {
        	toastr.error('系统出错,请联系管理员');
        },xhrFields: { withCredentials: true }
	});
}

/**修改*/
function modify(data){
	var zTree = $.fn.zTree.getZTreeObj("menu")
	nodes = zTree.getSelectedNodes(),
	treeNode = nodes[0];
	if($("#modifyText").val()==""){
		toastr.warning("请输入正确的名称");
	}else{
		$.ajax({
			url:"group/update.action",
	        type: "POST",
	        dataType:"JSON",
	        data : {
	        	fzId : treeNode.id,			//分组id【必传】
	        	fzName : $("#modifyText").val(),    //分组名称【必传】
	        	fzExtend2 : "1" ,		//状态
	        },
	        success: function(data) {
	        	if(data.code=="0000"){
	        		toastr.success(data.message);
	        		treeNode.name = $("#modifyText").val();
					zTree.updateNode(treeNode);
	        		$("#modifyText").val('');//清空文本框
	        		$("#myModalOne").modal('hide');//隐藏模态框
	        	}else{
	        		toastr.warning(data.message);
	        	}
	        },
	        error: function(request) {
	        	toastr.error('系统出错,请联系管理员');
	        },xhrFields: { withCredentials: true }
		});
	}
	
}

function addLevel(data){
	var zTree = $.fn.zTree.getZTreeObj("menu");
	nodes = zTree.getSelectedNodes();
	treeNode = nodes[0];
	if($("#addLevelText").val()==""){
		toastr.warning("请输入正确的名称");
	}else{
		if(nodes.length==0){
			$.ajax({
				url:"group/add.action",
		        type: "POST",
		        dataType:"JSON",
		        data : {
//		        	fzId : treeNode.id,			//分组id【必传】
		        	fzName : $("#addLevelText").val(),    //分组名称【必传】
		        	fzExtend2 : 1,
		        	fzExtend1 : 0,		//分组父id
		        },
		        success: function(data) {
		        	if(data.code == "0000"){
			        		var zTree = $.fn.zTree.getZTreeObj("menu");
			        		var nodes = zTree.getNodes();
			        		treeNode = nodes[0];
			        		treeNode = zTree.addNodes(null, {
								id : data.id,
								pId : -1,
								name : $("#addLevelText").val(),
							});
			        		showTree();
			        		toastr.success(data.message);
			        		$("#addLevelText").val('');//清空文本框
			        		$("#myModalTwo").modal('hide');//隐藏模态框
			        		$("#addLevel").show();
			    			$("#addLeaf").show();
			    			$("#modify").show();
			    			$("#remove").show();
			    			$("#mainAdd").show();
//			        		reset();
		        	}else if(data.code == "9999"){
		        		toastr.warning("存在同名类型");
		        	}else if(data.code == "9997" ){
								toastr.error(data.message);
								
								
		        	}
		        },
		        error: function(request) {
		        	toastr.error('系统出错,请联系管理员');
		        },xhrFields: { withCredentials: true }
			});
		}else{
			var pid;
			if(treeNode.pId==null || treeNode.pId==0){
				pid=0;
			}else{
				pid=treeNode.pId;
			}
			$.ajax({
				url:"group/add.action",
		        type: "POST",
		        dataType:"JSON",
		        data : {
		        	fzName : $("#addLevelText").val(),    //分组名称【必传】
		        	fzExtend1 : pid ,		//分组父id
		        	fzExtend2:1
		        },
		        success: function(data) {
		        	if(data.code == "0000"){
			        		var zTree = $.fn.zTree.getZTreeObj("menu")
			        		var  nodes = zTree.getSelectedNodes();
		                    var  treeNode = nodes[0];
			        		treeNode = zTree.addNodes(treeNode.getParentNode(), {
								id : data.id,
								pId : treeNode.pId,
								name : $("#addLevelText").val()
							});
			        		showTree();
			        		toastr.success(data.message);
			        		$("#addLevelText").val('');//清空文本框
			        		$("#myModalTwo").modal('hide');//隐藏模态框
//			        		reset();
			        		$("#addLevel").show();
			    			$("#addLeaf").show();
			    			$("#modify").show();
			    			$("#remove").show();
			    			$("#mainAdd").show();
		        	}else if(data.code == "9999"){
		        		toastr.warning("存在同名分组");
		        	}else if(data.code == "9997" ){
		        		toastr.error(data.message);
		        	}
		        },
		        error: function(request) {
		        	toastr.error('系统出错,请联系管理员');
		        },xhrFields: { withCredentials: true }
			});
		}	
	}
}

function addLeaf(data){
	var zTree = $.fn.zTree.getZTreeObj("menu");
	nodes = zTree.getSelectedNodes();
	treeNode = nodes[0];
	if($("#addLeafText").val()==""){
		toastr.warning("请输入正确的名称");
	}else{
		if(nodes.length==0){
			$.ajax({
				url:"group/add.action",
				type: "POST",
				dataType:"JSON",
				data : {
		        	fzName : $("#addLeafText").val(),    //分组名称【必传】
		        	fzExtend1 : 1 ,		//分组父id
		        	fzExtend2 : 1
		        },
				success: function(data) {
					if(data.code == "0000"){
						var zTree = $.fn.zTree.getZTreeObj("menu")
						var nodes = zTree.getNodes();
						treeNode = nodes[0];
						treeNode = zTree.addNodes(null, {
							id : Number(data.id),
							pId : -1,
							name :$("#addLeafText").val()
						});
						showTree();
						toastr.success(data.message);
						$("#addLeafText").val('');//清空文本框
		        		$("#myModalThree").modal('hide');//隐藏模态框
//						reset();
					}else if(data.code == "9999"){
						toastr.warning("存在同名类型");
					}else if(data.code == "9997" ){
						toastr.error(data.message);
					}
				},
				error: function(request) {
					toastr.error('系统出错,请联系管理员');
				},xhrFields: { withCredentials: true }
			});
		}else{
			var pid;
			pid=treeNode.id;//子节点pid等于当前节点id
			$.ajax({
				url:"group/add.action",
				type: "POST",
				dataType:"JSON",
				data : {
		        	fzName : $("#addLeafText").val(),    //分组名称【必传】
		        	fzExtend1 : pid ,		//分组父id
		        	fzExtend2 : 1
		        },
				success: function(data) {
					if(data.code == "0000"){
						var zTree = $.fn.zTree.getZTreeObj("menu")
						var  nodes = zTree.getSelectedNodes();
						var  treeNode = nodes[0];
						treeNode = zTree.addNodes(treeNode, {
							id : Number(data.id),
							pId : pid,
							name : $("#addLeafText").val()
						});
						showTree();
						toastr.success(data.message);
						$("#addLeafText").val('');//清空文本框
		        		$("#myModalThree").modal('hide');//隐藏模态框
//						reset();
						$("#addLevel").show();
		    			$("#addLeaf").show();
		    			$("#modify").show();
		    			$("#remove").show();
		    			$("#mainAdd").show();	        	
					}else if(data.code == "9999"){
						toastr.warning("存在同名类型");
					}else if(data.code == "9998" ){
						toastr.error(data.message);
					}
				},
				error: function(request) {
					toastr.error('系统出错,请联系管理员');
				},xhrFields: { withCredentials: true }
			});
		}
	}	
}

/*******************************zTree*********************************/

/********************************控制图设置****************************/
function clearInput(){//清空输入
	$("#kztbh").val('');			//（控制图编号）
	$("#kztlx").selectpicker('val', '');			//（控制图类型id）
	$("#testProject").selectpicker('val', '');		//（检测项目id）
	$("#cx_jcsb").selectpicker('val', '');		//（检测设备id）
	$("#ggsx").val('');				//（规格上限）
	$("#mbz").val('');				//（目标值）
	$("#ggxx").val('');				//（规格下限）
	$("#ybrl").selectpicker('val', '');				//（样本容量）
	$("#xsws").selectpicker('val', '');				//（小数位数）
	$("#stskzx").val('');			//（上图上控制限）
	$("#stmbz").val('');			//（上图目标值）
	$("#stxkzx").val('');			//（上图下控制限）
	$("#xtskzx").val('');			//（下图上控制限）
	$("#xtmbz").val('');			//（下图目标值）
	$("#xtxkzx").val('');			//（下图下控制限）
	$("#kztms").val('');			//（控制图描述）
	$("#zdykztbt").val('');			//（自定义控制图标题）
	$("#pygz").val('');				//判异规则
	$("#kztccxx").val('');
	$("#sjdccxx").val('');
}

function setting_back(){
	$(".main").show();
	$(".setting").hide();	
	$(".allDiv.container").css("overflow","hidden");	
	clearInput();
	pygz_update_state=1;
}

function searchTestProject(){//查询检测项
	$.ajax({
		url :"DetectionItem/search.action",
		dataType:"json",
		success : function(data) {
//			console.log(data);
			var option = "";
			$("#testProject").empty();
			option = '<option value="0">请选择检测项目</option>';
			for (var i=0;i<data.rows.length;i++) {
				option += '<option value="' + data.rows[i].jcxmId + '">'+ data.rows[i].jcxmName + '</option>';
			}
			$("#testProject").append(option);
			$("#testProject").selectpicker('refresh');
			$("#testProject").selectpicker('render');
		},
	});
}

function searchDetectionDevice(){//查询检测设备
	$.ajax({
		url :"DetectionDevice/search.action",
		dataType:"json",
		success : function(data) {
			console.log(data);
			var option = "";
			$("#cx_jcsb").empty();
			option = '<option value="">请选择检测设备</option>';
			for (var i=0;i<data.rows.length;i++) {
				option += '<option value="' + data.rows[i].jcsbId + '">'+ data.rows[i].jcsbName + '</option>';
			}
			$("#cx_jcsb").append(option);
			$("#cx_jcsb").selectpicker('refresh');
			$("#cx_jcsb").selectpicker('render');
		},
	});
}


function getCookie(cname){//获取cookie
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
		var c = ca[i].trim();
		if (c.indexOf(name)==0) { return c.substring(name.length,c.length); }
	}
	return "";
}
function setting_save_Add(){//控制图列表增加
	var ybrl;
	if($("#kztlx").selectpicker('val')==5){
		ybrl=1;
	}else{
		ybrl=$("#ybrl").selectpicker('val');
	}
	$.ajax({
		url:"controlChartMessage/add.action",
        type: "POST",
        dataType:"JSON",
        data:{
        	kztxxExtend3:getCookie("id"),											//用户id
        	kztxxGroupId:groupId,										//（分组id--【必传】）
        	kztxxNumber:$("#kztbh").val(),								//（控制图编号--【必传】）
        	kztxxTypeId:$("#kztlx").selectpicker('val'),								//（控制图类型id--【必传】）
        	kztxxDetectionItemId:$("#testProject").selectpicker('val'),				//（检测项目id--【必传】）
        	kztxxSpecificationUpperLimit:$("#ggsx").val(),				//（规格上限--【必传】）
        	kztxxTargetValue:$("#mbz").val(),							//（目标值--【必传】）
        	kztxxSpecificationDownLimit:$("#ggxx").val(),				//（规格下限--【必传】）
        	kztxxSampleCapacity:ybrl,						//（样本容量--【非必传】）
        	kztxxDecimalNumber:$("#xsws").selectpicker('val'),						//（小数位数--【非必传】）
        	kztxxAboveUpperControlLimit:$("#stskzx").val(),				//（上图上控制限--【非必传】）
        	kztxxAboveTargetValue:$("#stmbz").val(),					//（上图目标值--【非必传】）
        	kztxxAboveLowerControlLimit:$("#stxkzx").val(),				//（上图下控制限--【非必传】）
        	kztxxFollowGraphUpperControlLimit:$("#xtskzx").val(),		//（下图上控制限--【非必传】）
        	kztxxFollowGraphTargetValue:$("#xtmbz").val(),				//（下图目标值--【非必传】）
        	kztxxFollowGraphLowerControlLimit:$("#xtxkzx").val(),		//（下图下控制限--【非必传】）
        	kztxxControlChartDiscription:$("#kztms").val(),				//（控制图描述--【非必传】）
        	kztxxCustomizedControlChartTitle:$("#zdykztbt").val(),		//（自定义控制图标题--【非必传】）
        	kztxxDetectionDeviceId:$("#cx_jcsb").selectpicker('val'),									//（检测设备id--【非必传】）
        	//kztxxDetectionFrequency:123123,							//（自动检测频率（秒）--【非必传】）
        },
        success: function(data) {
        	if(data.success == "0000"){
        		toastr.success(data.message);
        		console.log(data);
//        		selectData1="kztxxccglControlChartId="+data.id+"&"+selectData1;
        		selectData2="kztxxccglControlChartId="+data.id+"&"+selectData2;
//        		console.log(selectData1);
        		console.log(selectData2);
//        		Kztsjd_update(selectData1);
        		
        		Kztsjd_update(selectData2);
        		
        		
//        		selectData1="";
        		selectData2="";       		
        		kzt_py_id=data.id;
        		if($("#kztlx").selectpicker('val')==5){//np没有判异规则
        			
        		}else{
        			save_YpgzList();//判异规则是否为空，后台已经判断  
        		}
        		
        		setTimeout(function(){
//        			showKztList();
           		$('#kztList').bootstrapTable('refresh');//刷新表     
        		}, 300);   
        		$(".main").show();
        		$(".setting").hide();
        		//清空新增时的输入框
        	}else{
        		toastr.error(data.message);
        	}
        },
        error: function(data) {
        	toastr.error(data.message);
        },
	});
	
}

function searchKZTSJD(row){//查询控制图层次信息以及数据点层次信息
	 $.ajax({
		 url:"controlChartHierarchicalInformationRelationship/search.action",
		 type: "GET",
		 dataType:"JSON",
		 data: {KztxxccglControlChartId :row.id},
		 success: function(data) {
		 if(data.code == "0000"){
			 console.log(data);
			 innit(data);
			 Kztsjd_str=[];
			 selectData1="";
			 selectData11="";
			 selectData2="";
			 selectData22="";
			 var x=0;
			 for(var i=0;i<9;i++){
				if(startArr[i]==1){//勾选框是否被选中
					var v=eval("data.value"+(i+1));
					Kztsjd_str[x]=$("#cc"+(i+1)).html().replace("：","")+"="+v;
					x+=1;
				}
			}
			$("#kztccxx").val(Kztsjd_str.join());
			Kztsjd_str=[];
			x=0;
			for(var i=0;i<9;i++){
				if(startArr[i]==2){
					var v=eval("data.value"+(i+1));
					Kztsjd_str[x]=$("#cc"+(i+1)).html().replace("：","")+"="+v;
					x+=1;
				}
			}
			$("#sjdccxx").val(Kztsjd_str.join());
			Kztsjd_str=[];
		 }else{
			 toastr.error(data.message);
		 }
		 },
		 error: function(data) {
			 toastr.error(data.message);
		 },
	 });
}

function innit(datax){
	startArr=[];
	var state;
	var name;
	var s;
	var bxz;
	for(var i=0;i<9;i++){
		state = eval("datax.state"+(i+1));
		name = eval("datax.name"+(i+1));
		bxz=eval("datax.id"+(i+1));
		s =i+1;
		if(name == "" ){
			$("#cb"+s).attr('disabled',true);
			$("#level"+s).attr('disabled',true);
			startArr.push(-1);
		}else{
			startArr.push(state);
		}
		valArr.push(bxz);
//		$("#level"+s).val(bxz);
		$("#cc"+s).html(name);
	}
//	 console.log(startArr);
}

function setting_kztccxx_add(){
	flagx=1;
	$("#kztccxxModal .xx").html('控制图层次信息设置');
	$("#kztccxxModal").modal("show");
	$(".in").removeAttr('disabled',true).removeAttr("checked");
	for(var i=0;i<startArr.length;i++){		
		var s = i+1;
		if(startArr[i] == 1){
			$("#cb"+s).prop("checked","checked");
			$("#level"+s).attr('disabled',false);
		}else if(startArr[i] == 2){
			$("#cb"+s).prop("checked","checked");
			$("#cb"+s).attr('disabled',true);
			$("#level"+s).attr('disabled',true);
		}else if(startArr[i] == -1){
			$("#cb"+s).attr('disabled',true);
			$("#level"+s).attr('disabled',true);
		}
	}
}

function setting_sjdccxx_add(){
	flagx=2;
	$("#kztccxxModal .xx").html('数据点层次信息设置');
	$("#kztccxxModal").modal("show");
	$(".in").removeAttr('disabled',true).removeAttr("checked");;
	for(var i=0;i<startArr.length;i++){
		var s = i+1;
		if(startArr[i] == 1){
			$("#cb"+s).prop("checked","checked");
			$("#cb"+s).attr('disabled',true);
			$("#level"+s).attr('disabled',true);
		}else if(startArr[i] == 2){
			$("#cb"+s).prop("checked","checked");
			$("#level"+s).attr('disabled',false);
		}else if(startArr[i] == -1){
			$("#cb"+s).attr('disabled',true);
			$("#level"+s).attr('disabled',true);
		}
	}
}

function save(){//控制图层次信息保存变量   弹窗按钮
	startArr=[];
	for(var i=0;i<9;i++){
		var s = i+1;
		if(flagx == 1){		
			console.log(!$("#cb"+s).prop("checked"));
			if($("#cc"+s).html()==""){
				startArr[i]=-1;
			}else if($("#cb"+s).prop("checked") && $("#cb"+s).attr('disabled')){
				startArr[i]=2;
			}else if(!$("#cb"+s).prop("checked")){
				startArr[i]=0;
			}else{
				startArr[i]=1;
			}			
		}else if(flagx == 2){		
			if($("#cc"+s).html()==""){
				startArr[i]=-1;
			}else if($("#cb"+s).prop("checked") && $("#cb"+s).attr('disabled')){
				startArr[i]=1;
			}else if(!$("#cb"+s).prop("checked")){
				startArr[i]=0;
			}else{
				startArr[i]=2;
			}			
		}		
	}
	console.log(startArr);
	var x=0;
	if(flagx==1){//如果点击了控制图层次信息修改
		for(var i=0;i<9;i++){
			if(startArr[i]==1){//勾选框是否被选中
				Kztsjd_str[x]=$("#cc"+(i+1)).html().replace("：","")+"="+$("#level"+(i+1)+" option:selected").text();
				x+=1;
			}
		}
		x=0;
		$("#kztccxx").val(Kztsjd_str.join());
		Kztsjd_str=[];
	}else{
		for(var i=0;i<9;i++){
			if(startArr[i]==2){//勾选框是否被选中
				Kztsjd_str[x]=$("#cc"+(i+1)).html().replace("：","")+"="+$("#level"+(i+1)+" option:selected").text();
				x+=1;
			}
		}
		x=0;
		$("#sjdccxx").val(Kztsjd_str.join());
		Kztsjd_str=[];
	}
	for(var i=0;i<9;i++){
		if(startArr[i]==1){
			switch(i){
				case 0:
					dataStr="kztxxccglTypeLevelOneId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelOneState=1&";
					break;
				case 1:
					dataStr="kztxxccglTypeLevelTwoId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelTwoState=1&";
				  break;
				case 2:
					dataStr="kztxxccglTypeLevelThreeId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelThreeState=1&";
				  break;
				case 3:
					dataStr="kztxxccglTypeLevelFourId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelFourState=1&";
				  break;
				case 4:
					dataStr="kztxxccglTypeLevelFiveId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelFiveState=1&";
				  break;
				case 5:
					dataStr="kztxxccglTypeLevelSixId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelSixState=1&";
				  break;
				case 6:
					dataStr="kztxxccglTypeLevelSevenId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelSevenState=1&";
				  break;
				case 7:
					dataStr="kztxxccglTypeLevelEightId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelEightState=1&";
				  break;
				case 8:
					dataStr="kztxxccglTypeLevelNineId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelNineState=1&";
				  break;
				}
			selectData=dataStr+selectData;
		}else if(startArr[i]==2){
			switch(i){
				case 0:
					dataStr="kztxxccglTypeLevelOneId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelOneState=2&";
					break;
				case 1:
					dataStr="kztxxccglTypeLevelTwoId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelTwoState=2&";
				  break;
				case 2:
					dataStr="kztxxccglTypeLevelThreeId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelThreeState=2&";
				  break;
				case 3:
					dataStr="kztxxccglTypeLevelFourId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelFourState=2&";
				  break;
				case 4:
					dataStr="kztxxccglTypeLevelFiveId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelFiveState=2&";
				  break;
				case 5:
					dataStr="kztxxccglTypeLevelSixId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelSixState=2&";
				  break;
				case 6:
					dataStr="kztxxccglTypeLevelSevenId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelSevenState=2&";
				  break;
				case 7:
					dataStr="kztxxccglTypeLevelEightId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelEightState=2&";
				  break;
				case 8:
					dataStr="kztxxccglTypeLevelNineId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelNineState=2&";
				  break;
				}
			selectData=dataStr+selectData;
		}else{
			switch(i){
			case 0:
				dataStr="kztxxccglTypeLevelOneId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelOneState=0&";
				break;
			case 1:
				dataStr="kztxxccglTypeLevelTwoId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelTwoState=0&";
			  break;
			case 2:
				dataStr="kztxxccglTypeLevelThreeId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelThreeState=0&";
			  break;
			case 3:
				dataStr="kztxxccglTypeLevelFourId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelFourState=0&";
			  break;
			case 4:
				dataStr="kztxxccglTypeLevelFiveId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelFiveState=0&";
			  break;
			case 5:
				dataStr="kztxxccglTypeLevelSixId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelSixState=0&";
			  break;
			case 6:
				dataStr="kztxxccglTypeLevelSevenId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelSevenState=0&";
			  break;
			case 7:
				dataStr="kztxxccglTypeLevelEightId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelEightState=0&";
			  break;
			case 8:
				dataStr="kztxxccglTypeLevelNineId="+$("#level"+(i+1)).val()+"&"+"kztxxccglTypeLevelNineState=0&";
			  break;
			}
		selectData=dataStr+selectData;
		}
	}
	selectData2=selectData;
	selectData22=selectData;
	console.log(selectData2);
	selectData="";
	
	$("#kztccxxModal").modal('hide');	
}

function Kztsjd_update(str){
	$.ajax({
		url:"controlChartHierarchicalInformationRelationship/update.action",
        type: "POST",
        dataType:"JSON",
        data:str,	        
        success: function(data) {
        	if(data.success == "0000"){
//        		toastr.success(data.message);
        	}else{
        		toastr.error(data.message);
        	}
        },
        error: function(data) {
        	toastr.error(data.message);
        },
	});
}



function searchSet(){//修改时，调用查询显示
	$.ajax({
		url:"controlChartMessage/searchSet.action",
        type: "GET",
        dataType:"JSON",
        data: {
				"id" : kztList_id,
			},
        success: function(data) {
        	if(data.success == "0000"){
        		console.log("全部信息",data);
        		start_pygz=data.occRule;//给初始的判异规则赋值
        		kztxxGroupId=data.rows.kztxxGroupId;								//（分组id）
        		$("#pygz").val(data.occRule);//判异规则	
            	$("#kztbh").val(data.rows.kztxxNumber);										//（控制图编号）
            	$("#kztlx").selectpicker('val',data.rows.kztxxTypeId);										//（控制图类型id）
            	var lxid=$("#kztlx").selectpicker('val');//保存控制图类型id 判断上下图六个框的显示
            	console.log(lxid);
            	$("#testProject").selectpicker('val',data.rows.kztxxDetectionItemId);		//（检测项目id）	
            	if(data.rows.kztxxDetectionDeviceId!=null){
            		$("#cx_jcsb").selectpicker('val',data.rows.kztxxDetectionDeviceId);
            	}
            	
            	
            	if(lxid==5){
            		$("#ybrl").selectpicker('val', '');
            	}else{
            		$("#ybrl").selectpicker('val', data.rows.kztxxSampleCapacity);								//（样本容量）
            	}           	
            	$("#xsws").selectpicker('val', data.rows.kztxxDecimalNumber);								//（小数位数）
            	
            	var xsws=$("#xsws").selectpicker('val');
            	         	
            	var kztxxSpecificationUpperLimit=Number(data.rows.kztxxSpecificationUpperLimit);
            	var kztxxTargetValue=Number(data.rows.kztxxTargetValue);
            	var kztxxSpecificationDownLimit=Number(data.rows.kztxxSpecificationDownLimit);	
            	
            	cal_UCLX=Number(data.rows.kztxxAboveUpperControlLimit);
            	cal_CLX=Number(data.rows.kztxxAboveTargetValue);
            	cal_LCLX=Number(data.rows.kztxxAboveLowerControlLimit);  
            	cal_UCLR=Number(data.rows.kztxxFollowGraphUpperControlLimit);
            	cal_CLR=Number(data.rows.kztxxFollowGraphTargetValue);
            	cal_LCLR=Number(data.rows.kztxxFollowGraphLowerControlLimit);
            	if(lxid=="1"){
            		$("#ggsx").val(kztxxSpecificationUpperLimit.toFixed(xsws));						//（规格上限）
                	$("#mbz").val(kztxxTargetValue.toFixed(xsws));									//（目标值）
                	$("#ggxx").val(kztxxSpecificationDownLimit.toFixed(xsws));						//（规格下限）
            		$("#stskzx").val(cal_UCLX.toFixed(xsws));					//（上图上控制限--【非必传】）
                	$("#stmbz").val(cal_CLX.toFixed(xsws));							//（上图目标值--【非必传】）
                	$("#stxkzx").val(cal_LCLX.toFixed(xsws));					//（上图下控制限--【非必传】）
                	$("#xtskzx").val(cal_UCLR.toFixed(xsws));				//（下图上控制限--【非必传】）
                	$("#xtmbz").val(cal_CLR.toFixed(xsws));						//（下图目标值--【非必传】）
                	$("#xtxkzx").val(cal_LCLR.toFixed(xsws));				//（下图下控制限--【非必传】）
            	}else if(lxid=="4"){
            		$("#ggsx").val('');						//（规格上限）
                	$("#mbz").val('');									//（目标值）
                	$("#ggxx").val('');						//（规格下限）
            		$("#stskzx").val('');					//（上图上控制限--【非必传】）
                	$("#stmbz").val(cal_CLX.toFixed(xsws));							//（上图目标值--【非必传】）
                	$("#stxkzx").val('');					//（上图下控制限--【非必传】）
                	$("#xtskzx").val('');				//（下图上控制限--【非必传】）
                	$("#xtmbz").val('');						//（下图目标值--【非必传】）
                	$("#xtxkzx").val('');				//（下图下控制限--【非必传】）
            	}else if(lxid=="5"){
            		$("#ggsx").val('');						//（规格上限）
                	$("#mbz").val('');									//（目标值）
                	$("#ggxx").val('');						//（规格下限）
            		$("#stskzx").val('');					//（上图上控制限--【非必传】）
                	$("#stmbz").val('');							//（上图目标值--【非必传】）
                	$("#stxkzx").val('');					//（上图下控制限--【非必传】）
                	$("#xtskzx").val('');				//（下图上控制限--【非必传】）
                	$("#xtmbz").val('');						//（下图目标值--【非必传】）
                	$("#xtxkzx").val('');				//（下图下控制限--【非必传】）
            	} 	
            	$("#kztms").val(data.rows.kztxxControlChartDiscription);					//（控制图描述--【非必传】）
            	$("#zdykztbt").val(data.rows.kztxxCustomizedControlChartTitle);				//（自定义控制图标题--【非必传】）
            	//kztxxDetectionDeviceId:1,													//（检测设备id--【非必传】）
            	//kztxxDetectionFrequency:123123,											//（自动检测频率（秒）--【非必传】）          	
            	for(var i=0;i<9;i++){
        			var x=i+1;
        			switch(i){
        				case 0:
        					$("#level"+x).val(data.cclx.kztxxccglTypeLevelOneId);
        					break;
        				case 1:
        					$("#level"+x).val(data.cclx.kztxxccglTypeLevelTwoId);
        				  break;
        				case 2:
        					$("#level"+x).val(data.cclx.kztxxccglTypeLevelThreeId);
        				  break;
        				case 3:
        					$("#level"+x).val(data.cclx.kztxxccglTypeLevelFourId);
        					 break;
        				case 4:
        					$("#level"+x).val(data.cclx.kztxxccglTypeLevelFiveId);
        					 break;
        				case 5:
        					$("#level"+x).val(data.cclx.kztxxccglTypeLevelSixId);
        					 break;
        				case 6:
        					$("#level"+x).val(data.cclx.kztxxccglTypeLevelSevenId);
        					break;
        				case 7:
        					$("#level"+x).val(data.cclx.kztxxccglTypeLevelEightId);
        				    break;
        				case 8:
        					$("#level"+x).val(data.cclx.kztxxccglTypeLevelNineId);
        					break;
         				}
        			if($("#level"+x).val()==null){
        				$("#level"+x+" option:first").prop("selected","selected");
        			}
        		}
        	}else{
        		toastr.error(data.message);
        	}
        },
        error: function(data) {
        	toastr.error(data.message);
        },
	});
}


/**
 * 操作点击事件
*/

function operate2(value, row, index) {
	var str = "";
	if(row.zt==1){
		str = '<div style="width:11px;height:11px;background:#22b7b5;border-radius:11px;margin:auto;"></div>';
	}else if(row.zt==2){
		str = '<div style="width:11px;height:11px;background:#f23333;border-radius:11px;margin:auto;"></div>';
	}else if(row.zt==3){
		str = '<div style="width:11px;height:11px;background:#f5b834;border-radius:11px;margin:auto;"></div>';
	}
    
	return str;
}

function formatYbrl(value, row, index) {
	if(row.tlId==5){
		return "-";
	}else{
		return value;
	}
}
function formatGgsx(value, row, index) {
	if(row.tlId==5){
		return "-";
	}else{
		return value;
	}
}
function formatMbz(value, row, index) {
	if(row.tlId==5){
		return "-";
	}else{
		return value;
	}
}
function formatGgxx(value, row, index) {
	if(row.tlId==5){
		return "-";
	}else{
		return value;
	}
}
function formatPygz(value, row, index) {
	if(row.tlId==5){
		return "-";
	}else{
		return value;
	}
}




function showKztList() {
	$('#kztList').bootstrapTable({
//	$('#kztList').bootstrapTable('destroy').bootstrapTable({
		url : "controlChartMessage/search.action",
		queryParams : function(params){
				var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的  
						limit : params.limit,// 每页记录数
						offset : params.offset,// 从第几条记录开始
						groupId:groupId
					};				
				if($('.main_center').css("display")=="block"){
					// 0619新增
					
					if (
            $("#cx_status")
              .val()
              != ""
          ) {
            temp.datasState = $("#cx_status")
              .val()
              .trim();
					}
					if (
            $("#cx_cpxh")
              .val()
              .trim() != ""
          ) {
            temp.typeOneId = $("#cx_cpxh")
              .val()
              .trim();
					}
					if (
            $("#cx_cpmc")
              .val()
              .trim() != ""
          ) {
            temp.typeTwoId = $("#cx_cpmc")
              .val()
              .trim();
          }
					
					if (
            $("#cx_chanxian")
              .val()
              .trim() != ""
          ) {
            temp.typeThreeId = $("#cx_chanxian")
              .val()
              .trim();
					}
					
					if (
            $("#cx_bc")
              .val()
              .trim() != ""
          ) {
            temp.typeFourId = $("#cx_bc")
              .val()
              .trim();
					}	
					
					if (
            $("#cx_gys")
              .val()
              .trim() != ""
          ) {
            temp.typeSixId = $("#cx_gys")
              .val()
              .trim();
          }
					// ===

					if($("#cx_bh").val().trim()!=""){
						temp.Number = $("#cx_bh").val().trim();
					}					
					if($("#cx_jcx").val()!=""){
						temp.detectionItemId = $("#cx_jcx").val();
					}					
					if($("#cx_tl").val()!=""){
						temp.typeId = Number($("#cx_tl").val());
					}					
					if($("#cx_tl").val()== "5"){						
//						temp.sampleCapacity = 1;					
					}else{						
						if($("#bh_ybrl").val().length>0){
							temp.sampleCapacity = $("#bh_ybrl").val().trim();
						}
					}					
				}					
				console.log(temp);				
			return temp;
	},//传递参数（*） 
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		striped : false, // 是否显示行间隔色
		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		sortOrder : "asc", // 排序方式
//      toolbar:"#toolbar",
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
		strictSearch : true,
		resizable: true,
		clickToSelect : true, // 是否启用点击选中行
		height : $(".main_right").height()-120, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		columns : shuzu,
		onLoadSuccess : function(data) {
			console.log(5555555555);
			console.log(data);
			kztListData = data.rows;	
			$('#kztList td').contextify(optionsXBt);
			
			setTimeout(function(){
				
				if(checkedFlag!=0){
//						$('#kztList').bootstrapTable('check', btIndex);
					$('#kztList').bootstrapTable('checkBy',btIndexTrue);
				}
			}, 300);
				
		},
		onDblClickRow:function(row,e){
//			console.log(e[0].dataset.index);
			publicParam=row;
			if(row.tl=="XR"||row.tl=="XS"||row.tl=="IMR"){
				btIndexTrue.values = [];
				btIndexTrue.values.push(row.id);
				showJLTCheck();
			}else{
				$.ajax({
					url:"poorDefinition/searchRelationship.action",
					type: "get",
					async:false, 
					dataType:"JSON",
					data:{
						id:row.id
					},
					success: function(data) {
						if(data.success=="0008"){
							toastr.warning("未绑定不良定义，请进行绑定");
							$("#bldy_kzt_bh").val(row.bh); 
							main_bldyTwo(row.id);
						}else{
							btIndexTrue.values = [];
							btIndexTrue.values.push(row.id);
							showJSTCheck();
						}
					},
					error: function(request) {
//						console.log(request);
					}
				});
			}
		},
		onPageChange:function(){
			checkedFlag = 0;
		}
	});
};

publicParamTable = function showKztList() {
	$('#kztList').bootstrapTable('refresh');
//	setTimeout(function(){
//		alert(btIndex);
//		$('#kztList').bootstrapTable('check', btIndex);
//	}, 5000)
	
}
function main_add(){
	pygz_update_state=1;
	clearInput();
	clear_readonly();
	searchTestProject();//调用查询检测项目方法
	searchDetectionDevice();//调用查询检测设备方法
	showBXZ();
	flag=1;//增加模式
	startArr=[];//清除勾选
	for(var i=0;i<9;i++){
		$("#cb"+(i+1)).removeAttr("disabled");
	}
	$("#zdsc").show();
	$(".setting").show();
	$(".main").hide();
	$(".allDiv.container").css("overflow","auto");	
}




var btIndex;//从右键获取bootstrap table下标
var btIndexTrue = {
		field:'id',
		values:[]
};

function getTabIdFormatter(param) {
	btIndex=$(param.currentTarget).parent().index();
//	$('#kztList').bootstrapTable('uncheckAll');
	$('#kztList').bootstrapTable('check', btIndex);
	
};



var pygz_state;//用于根据控制图类型来查询判异规则列表
var pygz_CheckArr=[];//用于存放判异规则勾选情况 用于调用接口时
var pygz_bhstr=[];//用于存放判异规则代码 在输入框内显示
var pygz_str="";//用于存放全部的判异规则内容 id 描述。。。。。  
var pygz_data=[];//用于存放全部的判异规则内容 id 描述。。。。。  由pygz_str组成
var pygz_strr="";//有上面的数组join而来  传给接口
var publicParam={};//选中数据，由子页面获取
var publicParamTable;
var pygz_update_state;//判异规则修改时使用

var publicMoreParam=[]; //多项目输入 & 多图查看--选中数据，
var publicMoreParam_tl = -1; //多图查看--选中图例，
var publicMoreId=[];  //多图查看--选中id，
//var publicMoreYb=[];  //多项目输入--选中样本容量，
var publicMultiParameter=[]; //多参数对比--选中数据，


var cal_UCLX,cal_CLX,cal_LCLX,cal_UCLR,cal_CLR,cal_LCLR;//保存计算结果


var public_limit = 50;   //一页显示几条

/**********************跳转子页面********/
function showJLT(){
	//MainMeteragePageID
	$("#MainChangePageID").show().attr('src','MainMeteragePage.action');
	$(".allDiv").hide();
	$("html,body").css("overflow","hidden");
}
function showJST(){
	$("#MainChangePageID").show().attr('src','MainCountPage.action');
	$(".allDiv").hide();
	$("html,body").css("overflow","hidden");
}

function showJLTCheck(){
	//MainMeteragePageID
	$("#MainChangePageID").show().attr('src','MainMeteragePageCheck.action');
	$(".allDiv").hide();
	$("html,body").css("overflow","hidden");
}

function showJSTCheck(){
	$("#MainChangePageID").show().attr('src','MainCountPageCheck.action');
	$(".allDiv").hide();
	$("html,body").css("overflow","hidden");
	console.log("11111");
}

function showMoreInput(){
	$("#MainChangePageID").show().attr('src','MultiProject.action');
	$(".allDiv").hide();
	$("html,body").css("overflow","hidden");
	console.log("11111");
}

function showMoreTu(){
	$("#MainChangePageID").show().attr('src','viewComparison.action');
	$(".allDiv").hide();
	$("html,body").css("overflow","hidden");
	console.log("11111");
}

function showMultiParameter(){
	$("#MainChangePageID").show().attr('src','MultiParameter.action');
	$(".allDiv").hide();
	$("html,body").css("overflow","hidden");
	console.log("11111");
}

/*********************控制图过滤************************/
/**
 * 显示控制图过滤
 */
$("#kztcxBtn").click(function(){
	searchTestProjectT();
	$(".main_right").css("width","65%");
	$(".main_center").show();
})

/**
 * 
 * @method 
 * @desc  关闭控制图过滤
 * @author zjm
 * @date 2018年11月21日
 */
function closeCx(){
	cx_reset();
	$(".main_right").css("width","80%");
	$(".main_center").hide();
}

/**
 * 
 * @method 
 * @desc  查询检查项
 * @author zjm
 * @date 2018年11月21日
 */
function searchTestProjectT(){//查询检测项
	$.ajax({
		url :"DetectionItem/search.action",
		dataType:"json",
		success : function(data) {
//			console.log(data);
			var option = "";
			$("#cx_jcx").empty();
			option = '<option value="">请选择检测项目</option>';
			for (var i=0;i<data.rows.length;i++) {
				option += '<option value="' + data.rows[i].jcxmId + '">'+ data.rows[i].jcxmName + '</option>';
			}
			$("#cx_jcx").append(option);
			$("#cx_jcx").selectpicker('refresh');
			$("#cx_jcx").selectpicker('render');
		},
	});
}



// 0613新增 查询状态
function searchTestStatus() {
	$.ajax({
    url: "DetectionItem/search.action",
    dataType: "json",
    success: function(data) {
			var option = "";
			$("#cx_status").empty();
			for(var i=0;i<data.rows.length;i++){
				option +=
          '<option>' +
          data.rows[i].datasState +
          '</option>';
			}
			$("#cx_status").append(option);
			$("#cx_status").selectpicker('refresh');
			$("#cx_status").selectpicker('render');
		}
  });
}



/**
 * 
 * @method 
 * @returns {Boolean}
 * @desc  点击查询控制图
 * @author zjm
 * @date 2018年11月21日
 */
function cx_kzt(){	
	if($("#cx_tl").val()!= "5" ){
		if($("#bh_ybrl").val().length>0){
			if(!iszzs($("#bh_ybrl").val().trim())){
				toastr.warning("样本容量请输入整数");
				return false;
			}
		}
	}
	$('#kztList').bootstrapTable('destroy');
	showKztList();
}





/**
 * 
 * @method 
 * @desc  重置
 * @author zjm
 * @date 2018年11月21日
 */
function cx_reset(){
	$("#cx_bh,#bh_ybrl").val("");
	$("#cx_jcx").selectpicker('val',"");
	$("#cx_tl").selectpicker('val',"");
}

/**
 * 
 * @method 
 * @param num
 * @returns {Boolean}
 * @desc  判断是否为整数
 * @author zjm
 * @date 2018年11月21日
 */
function iszzs(num){ 
	var reg=/^[0-9]+[0-9]*]*$/; //判断字符串是否为数字 ，判断正整数用/^[1-9]+[0-9]*]*$/		
	if(reg.test(num)){
    	return true;
    }else{
		return false;
	}
}

/****************************点击修改后不可选中的框*************************/

function update_readonly(row){	
	if(row.tl=="XR"){//XR
		$("#testProject").attr("disabled","disabled");//检测项目不可选
		$("#kztlx").attr("disabled","disabled");//控制图类型不可选
		$("#kztlx").selectpicker('refresh');
	}else if(row.tl=="P"){//p
		$("#kztlx").css("background","rgba(194, 194, 194,0.3)");	
		$("#testProject").css("background","rgba(194, 194, 194,0.3)");	
		$("#ybrl").css("background","rgba(194, 194, 194,0.3)");
		$("#xsws").css("background","rgba(194, 194, 194,0.3)");
		$("#ggsx").val('');
		$("#ggsx").css("background","rgba(194, 194, 194,0.3)");
		$("#mbz").val('');
		$("#mbz").css("background","rgba(194, 194, 194,0.3)");
		$("#ggxx").val('');
		$("#ggxx").css("background","rgba(194, 194, 194,0.3)");		
		$("#stskzx").val('');
		$("#stskzx").css("background","rgba(194, 194, 194,0.3)");
		$("#stxkzx").val('');
		$("#stxkzx").css("background","rgba(194, 194, 194,0.3)");
		$("#xtskzx").val('');
		$("#xtskzx").css("background","rgba(194, 194, 194,0.3)");
		$("#xtmbz").val('');
		$("#xtmbz").css("background","rgba(194, 194, 194,0.3)");
		$("#xtxkzx").val('');
		$("#xtxkzx").css("background","rgba(194, 194, 194,0.3)");
		$("#kztlx").attr("disabled","disabled");
		$("#kztlx").selectpicker('refresh');
		$("#testProject").attr("disabled","disabled");
		$("#ybrl").attr("disabled","disabled");
		$("#ybrl").selectpicker('refresh');
		$("#xsws").attr("disabled","disabled");	
		$("#xsws").selectpicker('refresh');
		$("#ggsx").attr("readonly","readonly");
		$("#mbz").attr("readonly","readonly");
		$("#ggxx").attr("readonly","readonly");
		$("#stskzx").attr("readonly","readonly");
		$("#stxkzx").attr("readonly","readonly");
		$("#xtskzx").attr("readonly","readonly");
		$("#xtmbz").attr("readonly","readonly");
		$("#xtxkzx").attr("readonly","readonly");
	}else if(row.tl=="nP"){//nP 没有判异 样本 计算
		$("#kztlx").css("background","rgba(194, 194, 194,0.3)");	
		$("#testProject").css("background","rgba(194, 194, 194,0.3)");	
		$("#ybrl").css("background","rgba(194, 194, 194,0.3)");
		$("#xsws").css("background","rgba(194, 194, 194,0.3)");	
		$("#ggsx").css("background","rgba(194, 194, 194,0.3)");	
		$("#mbz").css("background","rgba(194, 194, 194,0.3)");	
		$("#ggxx").css("background","rgba(194, 194, 194,0.3)");			
		$("#stskzx").css("background","rgba(194, 194, 194,0.3)");		
		$("#stmbz").css("background","rgba(194, 194, 194,0.3)");		
		$("#stxkzx").css("background","rgba(194, 194, 194,0.3)");				
		$("#xtskzx").css("background","rgba(194, 194, 194,0.3)");		
		$("#xtmbz").css("background","rgba(194, 194, 194,0.3)");		
		$("#xtxkzx").css("background","rgba(194, 194, 194,0.3)");		
		$("#kztlx").attr("disabled","disabled");
		$("#testProject").attr("disabled","disabled");
		$("#ybrl").attr("disabled","disabled");	
		$("#ybrl").val('');
		$("#xsws").attr("disabled","disabled");	
		$("#ggsx").val('');
		$("#ggsx").attr("readonly","readonly");
		$("#mbz").val('');
		$("#mbz").attr("readonly","readonly");
		$("#ggxx").val('');
		$("#ggxx").attr("readonly","readonly");		
		$("#stskzx").val('');
		$("#stskzx").attr("readonly","readonly");
		$("#stmbz").val('');
		$("#stmbz").attr("readonly","readonly");
		$("#stxkzx").val('');
		$("#stxkzx").attr("readonly","readonly");		
		$("#xtskzx").val('');
		$("#xtskzx").attr("readonly","readonly");
		$("#xtmbz").val('');
		$("#xtmbz").attr("readonly","readonly");
		$("#xtxkzx").val('');
		$("#xtxkzx").attr("readonly","readonly");
		$("#pygz").val("");	
		$("#pygz_set").attr("disabled","disabled");	
		$("#jisuan").attr("disabled","disabled");	
		$("#qingchu").attr("disabled","disabled");
		$("#kztlx").selectpicker('refresh');
		$("#ybrl").selectpicker('refresh');
		$("#xsws").selectpicker('refresh');
	}	
}
/****************************点击添加后不可选中的框*************************/
function add_readonly(){
	clear_readonly();
	$("#stskzx").css("background","rgba(194, 194, 194,0.3)");
	$("#stskzx").attr("readonly","readonly");	
	$("#stmbz").css("background","rgba(194, 194, 194,0.3)");
	$("#stmbz").attr("readonly","readonly");
	$("#stxkzx").css("background","rgba(194, 194, 194,0.3)");
	$("#stxkzx").attr("readonly","readonly");		
	$("#xtskzx").css("background","rgba(194, 194, 194,0.3)");
	$("#xtskzx").attr("readonly","readonly");		
	$("#xtmbz").css("background","rgba(194, 194, 194,0.3)");
	$("#xtmbz").attr("readonly","readonly");		
	$("#xtxkzx").css("background","rgba(194, 194, 194,0.3)");
	$("#xtxkzx").attr("readonly","readonly");	
	$("#jisuan").attr("disabled","disabled");	
	$("#qingchu").attr("disabled","disabled");
	for(var i=0;i<9;i++){		//清除勾选框勾选和不可选状态
		var s = i+1;
		$("#cb"+s).prop("checked", false);
		$("#cb"+s).removeAttr('disabled');
	}
	if($("#kztlx").selectpicker('val')==1){//XR
		$("#ybrl").selectpicker('val', 5);
		$("#xsws").selectpicker('val', 3);
		$("#pygz").val("R0,R1-1-3");
		pygz_strr="1;2,1,3";
		pygz_CheckArr=[1,2];
		pygz_state=1;
		showYpgzList_insert();//查询计量图判异
	}else if($("#kztlx").selectpicker('val')==4){//p
		
		$("#ybrl").attr("disabled","disabled");
		$("#ybrl").css("background","rgba(194, 194, 194,0.3)");
		
		$("#xsws").attr("disabled","disabled");	
		$("#xsws").css("background","rgba(194, 194, 194,0.3)");		
		$("#ggsx").css("background","rgba(194, 194, 194,0.3)");
		$("#ggsx").attr("readonly","readonly");		
		$("#mbz").css("background","rgba(194, 194, 194,0.3)");
		$("#mbz").attr("readonly","readonly");		
		$("#ggxx").css("background","rgba(194, 194, 194,0.3)");
		$("#ggxx").attr("readonly","readonly");					
		$("#pygz").val("R1-1-3");
		$("#kztlx").selectpicker('refresh');
		$("#ybrl").selectpicker('refresh');
		$("#xsws").selectpicker('refresh');
		$("#ybrl").selectpicker('val', 5);
		$("#xsws").selectpicker('val', 3);
		pygz_strr="2,1,3";
		pygz_CheckArr=[2];
		pygz_state=2;
		showYpgzList_insert();//查询计数图判异
	}else if($("#kztlx").selectpicker('val')==5){//nP
		
		$("#ybrl").attr("disabled","disabled");
		$("#ybrl").css("background","rgba(194, 194, 194,0.3)");
		
		$("#xsws").attr("disabled","disabled");	
		$("#xsws").css("background","rgba(194, 194, 194,0.3)");	
		$("#ggsx").css("background","rgba(194, 194, 194,0.3)");
		$("#ggsx").attr("readonly","readonly");		
		$("#mbz").css("background","rgba(194, 194, 194,0.3)");
		$("#mbz").attr("readonly","readonly");		
		$("#ggxx").css("background","rgba(194, 194, 194,0.3)");
		$("#ggxx").attr("readonly","readonly");		
		$("#pygz").val("");	
		$("#pygz_set").attr("disabled","disabled");
		$("#kztlx").selectpicker('refresh');
		$("#ybrl").selectpicker('refresh');
		$("#xsws").selectpicker('refresh');
		$("#xsws").selectpicker('val', 3);
		$("#ybrl").selectpicker('val', '');
//		$("#pygz").val("R1-1-3");	
//		pygz_strr="";
//		pygz_CheckArr=[2];
//		pygz_state=2;
//		showYpgzList_insert();//查询计数图判异
	}else{
		
	}
	console.log(pygz_strr);
}

function clear_readonly(){
	$("#kztlx").removeAttr("disabled");
	$("#kztlx").css("background","white");
	$("#testProject").css("background","white");
	$("#testProject").removeAttr("disabled");
	
	$("#ybrl").removeAttr("disabled");
	$("#ybrl").css("background","white");
	$("#xsws").removeAttr("disabled");
	$("#xsws").css("background","white");
	
	$("#ggsx").css("background","white");
	$("#ggsx").val('');
	$("#ggsx").removeAttr("readonly");
	
	$("#mbz").css("background","white");
	$("#mbz").val('');
	$("#mbz").removeAttr("readonly");
	
	$("#ggxx").css("background","white");
	$("#ggxx").val('');
	$("#ggxx").removeAttr("readonly");
	
	$("#stskzx").css("background","white");
	$("#stskzx").val('');
	$("#stskzx").removeAttr("readonly");
	
	$("#stmbz").css("background","white");
	$("#stmbz").val('');
	$("#stmbz").removeAttr("readonly");
	
	$("#stxkzx").css("background","white");
	$("#stxkzx").val('');
	$("#stxkzx").removeAttr("readonly");
	
	$("#xtskzx").css("background","white");
	$("#xtskzx").val('');
	$("#xtskzx").removeAttr("readonly");
	
	$("#xtmbz").css("background","white");
	$("#xtmbz").val('');
	$("#xtmbz").removeAttr("readonly");
	
	$("#xtxkzx").css("background","white");
	$("#xtxkzx").val('');
	$("#xtxkzx").removeAttr("readonly");
	
	$("#jisuan").removeAttr("disabled");	
	$("#qingchu").removeAttr("disabled");
	
	$("#pygz").val("");
	$("#pygz_set").removeAttr("disabled","disabled");
	$("#kztlx").selectpicker('refresh');
	$("#ybrl").selectpicker('refresh');
	$("#xsws").selectpicker('refresh');
	$("#ybrl").selectpicker('val', '');
	$("#xsws").selectpicker('val', '');
}

/**************************************判异规则弹出框*********************************/
function show_ypgz_update(){//修改按钮   弹出修改n m k的小弹窗
	console.log($("#ypgzList").bootstrapTable('getSelections'));
	clear_show();
	if($("#ypgzList").bootstrapTable('getSelections').length==0){//没选中
		toastr.warning("请您先选择要修改的判异规则");	
	}else if($("#ypgzList").bootstrapTable('getSelections').length==1){//选中一个
		var s;
		s=$("#ypgzList").bootstrapTable('getSelections')[0];
		if(flag==1){//新增时
			if(s.pygzRuleExtend3!=null){
				$("#ypgz_update").show();
				$("#pygz_modal-body").css("height","400px !important");
				var extend3="";
				extend3=s.pygzRuleExtend3;
				var extarr=[];
				extarr=extend3.split(",");
				if(extarr.length==1){
					$("#ntd").show();
					$("#n").show();
					$("#n").val(extarr[0]);
				}else if(extarr.length==2){
					$("#ntd").show();
					$("#mtd").show();
					$("#n").show();
					$("#n").val(extarr[0]);
					$("#m").show();
					$("#m").val(extarr[1]);
				}else if(extarr.length==3){
					$("#ntd").show();
					$("#mtd").show();
					$("#ktd").show();
					$("#n").show();
					$("#n").val(extarr[0]);
					$("#m").show();
					$("#m").val(extarr[1]);
					$("#k").show();
					$("#k").val(extarr[2]);
				}
			}else{
				toastr.warning("当前判异规则内没有变量");
			}
		}else if(flag==2){//修改时
			if(s.pygzRuleExtend3!=null){
				var sarr=s.pygzRuleExtend3.split(",");			
				$("#ypgz_update").show();
				if(sarr.length==1){
					$("#ntd").show();
					$("#n").show();
					$("#n_text").val(sarr[0]);
				}else if(sarr.length==2){
					$("#ntd").show();
					$("#mtd").show();
					$("#n").show();
					$("#n_text").val(sarr[0]);
					$("#m").show();
					$("#m_text").val(sarr[1]);
				}else if(sarr.length==3){
					$("#ntd").show();
					$("#mtd").show();
					$("#ktd").show();
					$("#n").show();
					$("#n_text").val(sarr[0]);
					$("#m").show();
					$("#m_text").val(sarr[1]);
					$("#k").show();
					$("#k_text").val(sarr[2]);
				}
			}else{
				toastr.warning("当前判异规则内没有变量");
			}
		}		
	}else{
		toastr.warning("只能选择一条要修改的判异规则");
	}
}

function hide_ypgz_update(){
	clear_show();
	$("#ypgz_update").hide();
}

function update_sure(){//确认修改   小弹窗
	console.log($("#ypgzList").bootstrapTable('getSelections')[0]);
	var nmk_value="";
	if(flag==1){
		var extend3="";
		extend3=$("#ypgzList").bootstrapTable('getSelections')[0].pygzRuleExtend3;
		var extarr=[];
		extarr=extend3.split(",");
		if(extarr.length==1){
			nmk_value=$("#n_text").val();
		}else if(extarr.length==2){
			nmk_value=$("#n_text").val()+","+$("#m_text").val();
		}else{
			nmk_value=$("#n_text").val()+","+$("#m_text").val()+","+$("#k_text").val();
		}
		$("#ypgzList").bootstrapTable('getSelections')[0].pygzRuleExtend3=nmk_value;
		var row=$("#ypgzList").bootstrapTable('getSelections')[0];
		$("#ypgzList").bootstrapTable('updateByUniqueId', {id: row.pygzRuleId, row: row});
	}else if(flag==2){
		var extend3="";
		extend3=$("#ypgzList").bootstrapTable('getSelections')[0];
		var sarr=extend3.pygzRuleExtend3.split(",");
		if(sarr.length==1){
			sarr[0]=$("#n_text").val();
		}else if(sarr.length==2){
			sarr[0]=$("#n_text").val();
			sarr[1]=$("#m_text").val();
		}else if(sarr.length==3){
			sarr[0]=$("#n_text").val();
			sarr[1]=$("#m_text").val();
			sarr[2]=$("#k_text").val();
		}
		$("#ypgzList").bootstrapTable('getSelections')[0].pygzRuleExtend3=sarr.join();
		var row=$("#ypgzList").bootstrapTable('getSelections')[0];
		$("#ypgzList").bootstrapTable('updateByUniqueId', {id: row.pygzRuleId, row: row});
	}
	$("#ypgz_update").hide();
}

function formatTableUnit(value,row) {
	var color="#999";
	return {
		css: {
			"color": color
		}
	}
}

function showYpgzList_ajax(){
	$.ajax({
		 url:"OCCRule/search.action",
		 type: "GET",
		 dataType:"JSON",
		 data: {id :publicParam.id},
		 success: function(data) {
		 if(data.success == "0000"){
//			 console.log(data);	
			 for(var i=0;i<data.rows.length;i++){	
//				 console.log(data.rows[i].id);
				 for(var i=0;i<data.rows.length;i++){
					 $('#ypgzList').bootstrapTable('checkBy', {field:'pygzRuleId', values:[data.rows[i].id]});
				 }
				 for(var i=0;i<data.rows.length;i++){
					 var row=$("#ypgzList").bootstrapTable('getSelections');
//					 console.log(row);
					 if(data.rows[i].n==undefined){
						 row[i].pygzRuleExtend3=null;
					 }else if(data.rows[i].n!=undefined&&data.rows[i].m==undefined){
						 row[i].pygzRuleExtend3=data.rows[i].n.toString();
					 }else if(data.rows[i].n!=undefined&&data.rows[i].m!=undefined&&data.rows[i].k==undefined){
						 row[i].pygzRuleExtend3=data.rows[i].n.toString()+","+data.rows[i].m.toString();
					 }else{
						 row[i].pygzRuleExtend3=data.rows[i].n.toString()+","+data.rows[i].m.toString()+","+data.rows[i].k.toString();
					 } 		
//					 console.log(row);
					 $('#ypgzList').bootstrapTable('updateByUniqueId', {uniqueId: 3, row:row});
				 }
				 pygzchecked(); 
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

function pygzchecked(){
	console.log($("#ypgzList").bootstrapTable('getSelections'));	
	pygz_data=[];
	pygz_str="";
	pygz_strr=[];
	var l=$("#ypgzList").bootstrapTable('getSelections');
	if(flag==1){
		for(var i=0;i<l.length;i++){
			if(l[i].pygzRuleExtend3==null){
				pygz_str=l[i].pygzRuleId;
			}else{
				var lx=l[i].pygzRuleExtend3.split(",");
				if(lx.length==1){
					pygz_str=l[i].pygzRuleId+","+lx[0];
				}else if(lx.length==2){
					pygz_str=l[i].pygzRuleId+","+lx[0]+","+lx[1];
				}else if(lx.length==3){
					pygz_str=l[i].pygzRuleId+","+lx[0]+","+lx[1]+","+lx[2];
				}
			}
			pygz_data[i]=pygz_str;
		}
	}else{
		for(var i=0;i<l.length;i++){
			if(l[i].pygzRuleExtend3==null){
				pygz_str=l[i].pygzRuleId;
			}else{
				var lx=l[i].pygzRuleExtend3.split(",");
				if(lx.length==1){
					pygz_str=l[i].pygzRuleId+","+lx[0];
				}else if(lx.length==2){
					pygz_str=l[i].pygzRuleId+","+lx[0]+","+lx[1];
				}else if(lx.length==3){
					pygz_str=l[i].pygzRuleId+","+lx[0]+","+lx[1]+","+lx[2];
				}
			}
			pygz_data[i]=pygz_str;
		}
	}	
	console.log(pygz_data);
	pygz_strr=pygz_data.join(";");
	console.log(pygz_strr);
}

function setting_ypgz_add(){
	if(flag==1){//新增模式
		if($("#kztlx").selectpicker('val')!=""){	
			
			$("#ypgzModal").modal('show');			
		}else {
			toastr.warning("请您先选择控制图类型");
		}
	}else if(flag==2){//修改模式
		$("#ypgzModal").modal('show');
	}	
}

function ypgz_save(){//保存选择判异规则的变量
	console.log($("#ypgzList").bootstrapTable('getSelections'));
	pygz_CheckArr=[];
	pygz_bhstr=[];
	if(flag==1){
		for(var i=0;i<$("#ypgzList").bootstrapTable('getSelections').length;i++){
			if($("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleExtend3!=null){
				var ext=$("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleExtend3;
				var extArr=ext.split(",");
				if(extArr.length==1){
					pygz_bhstr[i]=$("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleExtend1+"-"+extArr[0];
				}else if(extArr.length==2){
					pygz_bhstr[i]=$("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleExtend1+"-"+extArr[0]+"-"+extArr[1];
				}else if(extArr.length==3){
					pygz_bhstr[i]=$("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleExtend1+"-"+extArr[0]+"-"+extArr[1]+"-"+extArr[2];
				}
			}else{
				pygz_bhstr[i]=$("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleExtend1;
			}			
			pygz_CheckArr[i]=$("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleId;
		}
		$("#pygz").val(pygz_bhstr.join());
	}else if(flag==2){
		for(var i=0;i<$("#ypgzList").bootstrapTable('getSelections').length;i++){
			if($("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleExtend3!=null){
				var ext=$("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleExtend3;
				var extArr=ext.split(",");
				if(extArr.length==1){
					pygz_bhstr[i]=$("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleExtend1+"-"+extArr[0];
				}else if(extArr.length==2){
					pygz_bhstr[i]=$("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleExtend1+"-"+extArr[0]+"-"+extArr[1];
				}else if(extArr.length==3){
					pygz_bhstr[i]=$("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleExtend1+"-"+extArr[0]+"-"+extArr[1]+"-"+extArr[2];
				}
			}else{
				pygz_bhstr[i]=$("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleExtend1;
			}
			pygz_CheckArr[i]=$("#ypgzList").bootstrapTable('getSelections')[i].pygzRuleId;
		}
		$("#pygz").val(pygz_bhstr.join());
	}
	console.log(pygz_CheckArr);
	pygzchecked();
	$("#ypgzModal").modal('hide');
}

function clear_show(){
	$("#n_text").val('');
	$("#m_text").val('');
	$("#k_text").val('');
	$("#ntd").hide();
	$("#mtd").hide();
	$("#ktd").hide();
	$("#n").hide();
	$("#m").hide();
	$("#k").hide();
//	$("#py_update_button").hide();
}

function rule_operate_insert(value,row){//增加时
	var des=row.pygzRuleDescribe;//给des赋值
	if(row.pygzRuleExtend3!=null){	
		var extend3="";
		extend3=row.pygzRuleExtend3;
		var extarr=[];
		extarr=extend3.split(",");
		if(des.indexOf("[n]")!=-1){//如果文本中有n
			des=des.replace("[n]","&nbsp;"+extarr[0]+"&nbsp;");
		}
		if(des.indexOf("[m]")!=-1){//如果文本中有m
			des=des.replace("[m]","&nbsp;"+extarr[1]+"&nbsp;");
		}
		if(des.indexOf("[k]")!=-1){//如果文本中有k
			des=des.replace("[k]","&nbsp;"+extarr[2]+"&nbsp;");
		}
	}
	return des;
}

function showYpgzList_insert(){
	$('#ypgzList').bootstrapTable('destroy').bootstrapTable({
		striped : false, // 是否显示行间隔色
		pagination : false, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		sortOrder : "asc", // 排序方式
//      toolbar:"#toolbar",
        uniqueId:'pygzRuleId',
		pageNumber : 1, // 初始化加载第一页，默认第一页
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
		strictSearch : true,
		clickToSelect : true, // 是否启用点击选中行
		height : 350, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		columns : [ 
		{checkbox:true,valign:'middle',width:'50px'},
       {field : 'pygzRuleExtend1',title : '规则编号',align:"center",valign:'middle',width:'100px'},
       {field : 'pygzRuleDescribe2',title : '现行规则',align:"center",valign:'middle',width:'300px',formatter : rule_operate_insert,},
       {field : 'pygzRuleDescribe',title : '规则依据',align:"center",valign:'middle',width:'300px',cellStyle: formatTableUnit}, 
		],
		onLoadSuccess : function(data) {		
		}
	});
	$.ajax({
		url:"OCCRule/insertSearch.action",
        type: "GET",
        dataType:"JSON",
        data:{
        	state:pygz_state
        },
        success: function(data) {
        	if(data.success == "0000"){
        		console.log(data);//输出控制图id和判异规则数据
        		$('#ypgzList').bootstrapTable('load',data.rows);
        		console.log("获取数据",data);
        		console.log("获取勾选数据",pygz_CheckArr);
        		pygz_bhstr=[];		
        		if(flag==1){//新增时    			
        			$('#ypgzList').bootstrapTable('checkBy', {field:'pygzRuleId', values:pygz_CheckArr});
        			pygzchecked();
//        			if(pygz_state==1){
//        				$('#ypgzList').bootstrapTable('checkBy', {field:'pygzRuleId', values:pygz_CheckArr});
//        			}else{
//        				$('#ypgzList').bootstrapTable('checkBy', {field:'pygzRuleId', values:[pygz_CheckArr[1]]});
//        			}
        		}else if(flag==2){//修改时
        			showYpgzList_ajax();
//        			if(pygz_state==1){
//        				if($("#pygz").val()!=""){
//        					showYpgzList_ajax();
//        				}else{
//        					$('#ypgzList').bootstrapTable('checkBy', {field:'pygzRuleId', values:pygz_CheckArr});
//        					showYpgzList_ajax();
//        				}					
//        			}else{
//        				if($("#pygz").val()!=""){
//        					showYpgzList_ajax();
//        				}else{
//        					$('#ypgzList').bootstrapTable('checkBy', {field:'pygzRuleId', values:[pygz_CheckArr[1]]});
//        					showYpgzList_ajax();
//        				}	
//        			}
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

function save_YpgzList(){
	console.log(pygz_strr);
	console.log(pygz_update_state);
	$.ajax({
		url:"OCCRule/update.action",
        type: "POST",
        dataType:"JSON",
        data:{
        	id:kzt_py_id,//（控制图信息id--【必传】）
        	str:pygz_strr,//（判异规则相关数据--【必传】）
        	state:pygz_update_state
        },
        success: function(data) {
        	if(data.success == "0000"){
//        		toastr.success("数据重新判异完成");
        		console.log(kzt_py_id,pygz_strr);//输出控制图id和判异规则数据
        		$('#kztList').bootstrapTable('refresh');//刷新表
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

function xsws_update(){//改变小数位数事件
	var ggsx=$("#ggsx").val();
	var mbz=$("#mbz").val();
	var ggxx=$("#ggxx").val();
	ggsx=Number(ggsx).toFixed($("#xsws").selectpicker('val'));
	mbz=Number(mbz).toFixed($("#xsws").selectpicker('val'));
	ggxx=Number(ggxx).toFixed($("#xsws").selectpicker('val'));
	$("#ggsx").val(ggsx);
	$("#mbz").val(mbz);
	$("#ggxx").val(ggxx);
}

function calculate(){
	console.log($("#kztlx").selectpicker('val'));
	$.ajax({
		url:"kzt/calculation.action",
        type: "POST",
        dataType:"JSON",
        data:{
        	controlChartId:kztList_id,//（控制图信息id--【必传】）
        	typeId:$("#kztlx").selectpicker('val'),//（判异规则相关数据--【必传】）
        	sampleSize:$("#ybrl").selectpicker('val')
        },
        success: function(data) {
//        	console.log($("#kztlx").val());
        	console.log(data);//输出控制图id和判异规则数据
        	var xsws=$("#xsws").selectpicker('val');
        	cal_UCLX=Number(data.UCLX);
        	cal_CLX=Number(data.CLX);
        	cal_LCLX=Number(data.LCLX);
        	cal_UCLR=Number(data.UCLR);
        	cal_CLR=Number(data.CLR);
        	cal_LCLR=Number(data.LCLR);
        	if($("#kztlx").selectpicker('val')==1){
        		$("#stskzx").val(cal_UCLX.toFixed(xsws));			//（上图上控制限）
            	$("#stmbz").val(cal_CLX.toFixed(xsws));			//（上图目标值）
            	$("#stxkzx").val(cal_LCLX.toFixed(xsws));			//（上图下控制限）
            	$("#xtskzx").val(cal_UCLR.toFixed(xsws));			//（下图上控制限）
            	$("#xtmbz").val(cal_CLR.toFixed(xsws));			//（下图目标值）
            	$("#xtxkzx").val(cal_LCLR.toFixed(xsws));			//（下图下控制限）
        	}else if($("#kztlx").selectpicker('val')==4){
        		$("#stskzx").val('');			//（上图上控制限）
            	$("#stmbz").val(Number(data.CLX).toFixed(xsws));			//（上图目标值）
            	$("#stxkzx").val('');			//（上图下控制限）
            	$("#xtskzx").val('');			//（下图上控制限）
            	$("#xtmbz").val('');			//（下图目标值）
            	$("#xtxkzx").val('');			//（下图下控制限）
        	}else if($("#kztlx").selectpicker('val')==5){
        		$("#stskzx").val('');			//（上图上控制限）
            	$("#stmbz").val('');			//（上图目标值）
            	$("#stxkzx").val('');			//（上图下控制限）
            	$("#xtskzx").val('');			//（下图上控制限）
            	$("#xtmbz").val('');			//（下图目标值）
            	$("#xtxkzx").val('');			//（下图下控制限）
        	}
        	
        },
        error: function(data) {
        	toastr.error(data.message);
        },
	});
}

function clear_Calculate(){
	$("#stskzx").val('');			//（上图上控制限）
	$("#stmbz").val('');			//（上图目标值）
	$("#stxkzx").val('');			//（上图下控制限）
	$("#xtskzx").val('');			//（下图上控制限）
	$("#xtmbz").val('');			//（下图目标值）
	$("#xtxkzx").val('');			//（下图下控制限）
}

function show_other(){//其他信息按钮	
	$("#other_xx").modal('show');	
}

/*************************失控处理审核***************************/

function zt(value,row){
	var des=row.state;//给des赋值       失控未处理0/审核1/未审核2
	var destate;
	if(des==0){
		destate="失控未处理";
	}else if(des==1){
		destate="已审核";
	}else if(des==2){
		destate="未审核";
	}
	return destate;
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
function showDealLookLook() {
	$("#dispose").modal('show');
	showDisposeList();
	setTimeout(function(){
		$('#disposeList').bootstrapTable('refresh');
	}, 100)
	
}
var detectionDataId=0;
var start_url="outOfControlRecord/displayAll.action";
function show_item(){
	row_state=[];
	if($("#skclsh_select").val()==1){
		start_url="outOfControlRecord/displayAll.action";//全部
	}else if($("#skclsh_select").val()==2){
		start_url="outOfControlRecord/displayAudited.action";//已审核
	}else if($("#skclsh_select").val()==3){
		start_url="outOfControlRecord/displayUnaudited.action";//未审核
	}else if($("#skclsh_select").val()==4){
		start_url="outOfControlRecord/displayUnprocessed.action";//失控
	}
	showDisposeList();
}
function showDisposeList() {//审核信息表	
	$('#disposeList').bootstrapTable('destroy').bootstrapTable({
		url : start_url, //请求后台的URL（*）
		queryParams : function(params){
			var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的  
					limit : params.limit,// 每页记录数
					offset : params.offset,// 从第几条记录开始
					controlChartId : publicParam.id//控制图id
				};			
			return temp;
	},//传递参数（*） 
		sidePagination : "server", //分页方式：client客户端分页，server服务端分页（*）
		striped : false, // 是否显示行间隔色
//		pagination : true, // 是否显示分页（*）
		sortable : false, // 是否启用排序
		sortOrder : "asc", // 排序方式
//		toolbar : "#toolbar",
//		pageNumber : 1, // 初始化加载第一页，默认第一页
//		pageSize : 10, // 每页的记录行数（*）
//		pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
//		paginationDetailHAlign:"right",
		strictSearch : true,
		clickToSelect : true, // 是否启用点击选中行
		height : 416, // 行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		columns : [ {
			field : 'state',
			title : '状态',
			align : "center",
			valign : 'middle',
			width:100,
			formatter : zt		
		}, {
			field : 'controlChartNumber',
			title : '编号',
			width:100,
			align : "center",
			valign : 'middle'
		}, {
			field : 'detectionItemName',
			title : '检测项目',
			width:100,
			align : "center",
			valign : 'middle'
		}, {
			field : 'controlChartTypeName',
			title : '图类',
			width:100,
			align : "center",
			valign : 'middle'
		},
//		{
//			field : 'detectionDataNo',
//			title : '序号',
//			align : "center",
//			valign : 'middle'
//		}, 
		],
		onClickRow:function(data){//鼠标点击bootstraptable事件
			console.log(data);
			detectionDataId=data.detectionDataId;
			clear_undo();
			if(data.state==0){//失控点 禁用右边全部
				$("#skclsh_skyy").css("background","rgba(194, 194, 194,0.3)");
				$("#skclsh_clcs").css("background","rgba(194, 194, 194,0.3)");
				$("#shms").css("background","rgba(194, 194, 194,0.3)");
				$("#shms").attr("readonly","readonly");
				$("input[name='sex']").eq(0).attr("disabled",true);
				$("input[name='sex']").eq(1).attr("disabled",true);
				$("input[name='sex']").eq(0).removeAttr("checked");
				$("input[name='sex']").eq(1).removeAttr("checked");
				$("#clr").text("");
				$("#shr").text("");
				$("#sh_clsj").text("2018-09-06 17:38:21");//时间
				$("#sh_shsj").text("2018-09-06 17:38:21");	
				$("#sh_btn").attr("disabled",true);//按钮不可用
			}else if(data.state==1){//已审核 根据结果选中同意拒绝
				search_ysh(detectionDataId);
			}else if(data.state==2){//未审核 默认拒绝
				$("input[name='sex']").eq(1).click();
				search_wsh(detectionDataId);
			}		
		},
		onLoadSuccess : function(data) {			
			clear_undo();
			$("#skclsh_skyy").css("background","rgba(194, 194, 194,0.3)");
			$("#skclsh_clcs").css("background","rgba(194, 194, 194,0.3)");
			$("#shms").css("background","rgba(194, 194, 194,0.3)");
			$("#shms").attr("readonly","readonly");
			$("input[name='sex']").eq(0).attr("disabled",true);
			$("input[name='sex']").eq(1).attr("disabled",true);
			$("input[name='sex']").eq(0).removeAttr("checked");
			$("input[name='sex']").eq(1).removeAttr("checked");
			$("#clr").text("");
			$("#shr").text("");
			$("#shms").val("");
			$("#sh_clsj").text("2018-09-06 17:38:21");//时间
			$("#sh_shsj").text("2018-09-06 17:38:21");	
			$("#sh_btn").attr("disabled",true);//按钮不可用
			console.log(data);
		}
	});	
};
function clear_undo(){//清除不可用状态	
	$("#sh_btn").removeAttr("disabled");
	$("#skclsh_skyy").css("background","white");
	$("#skclsh_skyy").text('');
	$("#skclsh_clcs").css("background","white");
	$("#skclsh_clcs").text('');
	$("#shms").css("background","white");
	$("#shms").val("");
	$("#shms").removeAttr("readonly","readonly");
	$("input[name='sex']").eq(0).removeAttr("disabled",true);
	$("input[name='sex']").eq(1).removeAttr("disabled",true);
	$("input[name='sex']").eq(0).removeAttr("checked");
	$("input[name='sex']").eq(1).attr("checked");
	$("#sh_clsj").text("2018-09-06 17:38:21");//时间
	$("#sh_shsj").text("2018-09-06 17:38:21");
	$("#clr").text("admin");
	$("#shr").text("admin");
}

function sh_pass(){//审核通过
	if(detectionDataId==0){
		toastr.warning("请选择记录");
	}else{
		if($("input[name='sex']:checked").val()==1){
			shjg="同意";
		}else if($("input[name='sex']:checked").val()==2){
			shjg="不同意";
		}
		$.ajax({
			url:"outOfControlRecord/updateAudit.action",
	        type: "POST",
	        dataType:"JSON",
	        data:{
	        	skjlDetectionDataId:detectionDataId,   //（对应检测数据id-- 【必传】）
	        	skjlAuditResult:shjg,//（对应审核结果-- 【必传】）
	        	skjlAuditDescribe:$("#shms").val(),//（对应审核描述- 【必传】）
	        	skjlAuditPersionId:getCookie("id"),//（对应审核人id-- 【必传】）
	        },
	        success: function(data) {
	        	if(data.code == "0000"){
	        		toastr.success("审核成功");
	        	}else{
	        		toastr.error(data.message);
	        	}
	        },
	        error: function(data) {
	        	toastr.error(data.message);
	        },
		});
	}
	
}
function getNowFormatDate() {//时间
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var strHour=date.getHours();
    var strMinute=date.getMinutes();
    var strSecond=date.getSeconds();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (strHour >= 0 && strHour <= 9) {
    	strHour = "0" + strHour;
    }
    if (strMinute >= 0 && strMinute <= 9) {
    	strMinute = "0" + strMinute;
    }
    if (strSecond >= 0 && strSecond <= 9) {
    	strSecond = "0" + strSecond;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + strHour + seperator2 + strMinute
            + seperator2 + strSecond;
//    console.log(currentdate);
    $("#sh_shsj").text(currentdate);
//    setTimeout(getNowFormatDate, 1000);
}

function search_wsh(id){//根据检测数据id查询  未审核
	$.ajax({
		url:"outOfControlRecord/searchDeal.action",
	    type: "GET",
	    dataType:"JSON",
	    data:{
	    	detectionDataId:id   //（对应检测数据id-- 【必传】）
	    },
	    success: function(data) {
	    	if(data.code == "0000"){
	     		console.log("未审核",data);
	     		getNowFormatDate();//实时显示审核时间
	     		$("#skclsh_skyy").text(data.result.skjlReason);
	     		$("#skclsh_clcs").text(data.result.skjlStep);
	     		$("#sh_clsj").text(data.result.manageTime);//时间
	     		$("#clr").text(data.result.managePersonName);
	     		$("#shr").text('');
	     		$("#shr").text(getCookie("username"));
	    	}else{
	    		toastr.error(data.message);
	    	}
	    },
	    error: function(data) {
	    	toastr.error(data.message);
	    },
	});
}

function search_ysh(id){//根据检测数据id查询  已审核
	$.ajax({
		url:"outOfControlRecord/searchDeal.action",
	    type: "GET",
	    dataType:"JSON",
	    data:{
	    	detectionDataId:id   //（对应检测数据id-- 【必传】）
	    },
	    success: function(data) {
	    	if(data.code == "0000"){
	     		console.log("已经审核",data);
	     		$("#skclsh_skyy").text(data.result.skjlReason);
	     		$("#skclsh_clcs").text(data.result.skjlStep);
	     		$("#sh_clsj").text(data.result.manageTime);//时间
	     		$("#clr").text(data.result.managePersonName);	     		
	    	}else{
	    		toastr.error(data.message);
	    	}
	    },
	    error: function(data) {
	    	toastr.error(data.message);
	    },
	});
	$.ajax({
		url:"outOfControlRecord/searchAudit.action",
	    type: "GET",
	    dataType:"JSON",
	    data:{
	    	detectionDataId:id,   //（对应检测数据id-- 【必传】）
	    },
	    success: function(data) {
	    	console.log("已经审核",data);
	    	if(data.code=="0000"){
	    		$("#shr").text(data.result.auditorName);
	    		$("#sh_shsj").text(data.result.auditTime);
	    		$("#shms").val(data.result.skjlAuditDescribe);
	    		if(data.result.skjlAuditResult=="同意"){
	    			$("input[name='sex']").eq(0).attr("checked","checked");
	    		    $("input[name='sex']").eq(1).removeAttr("checked");
	    		    $("input[name='sex']").eq(0).click();
	    		}else{
	    			$("input[name='sex']").eq(1).attr("checked","checked");
	    		    $("input[name='sex']").eq(0).removeAttr("checked");
	    		    $("input[name='sex']").eq(1).click();
	    		}
	    	}
	//    	else{
	//    		toastr.error(data.message);
	//    	}
	    },
	    error: function(data) {
	    	toastr.error(data.message);
    },
});
}

function onblus(val,id){//鼠标离开文本框事件
	if($("#kztlx").val()==1){
		console.log($("#xsws").val());
		console.log(id);
		if($("#"+id).val()!=""){
			$("#"+id).val(Number(val).toFixed($("#xsws").val()));
		}
	}else{
		return false;
	}
}
//function showYpgzListRuleCode_ajax(){
//	$.ajax({
//		 url:"OCCRule/search.action",
//		 type: "GET",
//		 dataType:"JSON",
//		 data: {id :publicParam.id},
//		 success: function(data) {
//		 if(data.success == "0000"){
//			 console.log(data);		 
//			 var ruleCode_arr=[];
//			 var rulestr_arr=[];
//			 for(var i=0;i<data.rows.length;i++){
//				 ruleCode_arr[i]=data.rows[i].ruleCode;
//				 if(data.rows[i].n==undefined){
//					 rulestr_arr[i]=data.rows[i].id;
//				 }else if(data.rows[i].n!=undefined&&data.rows[i].m==undefined){
//					 rulestr_arr[i]=data.rows[i].id+","+data.rows[i].n;
//				 }else if(data.rows[i].n!=undefined&&data.rows[i].m!=undefined&&data.rows[i].k==undefined){
//					rulestr_arr[i]=data.rows[i].id+","+data.rows[i].n+","+data.rows[i].m;
//				}else{
//					rulestr_arr[i]=data.rows[i].id+","+data.rows[i].n+","+data.rows[i].m+","+data.rows[i].k;
//				}	 
//			 }
//			 console.log(rulestr_arr);
//			 pygz_strr=rulestr_arr.join(";");
//			 $("#pygz").val(ruleCode_arr.join());
//
//		 }else{
//			 toastr.error(data.message);
//		 }
//		 },
//		 error: function(data) {
//			 toastr.error(data.message);
//		 },
//	 });
//}

// 时间查询
var flagFirstLoad=0;

timeSearch(2);

function timeSearch(paramx){

	if(paramx==2){
		public_offset=0;
	}

	if($("#startTime").val()=="" && $("#endTime").val()==""){
		startTime=0;
		endTime=new Date().getTime();
	}else{
		if(!new Date($("#startTime").val()).getTime()){
			startTime=0;
		}else{
			startTime=new Date($("#startTime").val()).getTime();
		}
		
		if(!new Date($("#endTime").val()).getTime()){
			endTime=new Date().getTime();
		}else{
			endTime=new Date($("#endTime").val()).getTime();
		}
	}
	
	$.ajax({
		url : "controlChartData/search.action",
		type : "GET",
		dataType : "JSON",
		async:true,
		data : {
			ids:parent.publicParam.id,
			state:$("#timeType").val(),
			startTime:startTime,
			endTime:endTime,
			condition:"",
			limit:public_limit,
			offset:public_offset
		},
		success : function(data) {	
			
			
			console.log('查询数据666',data,public_offset);
			
			$("#countPage").html(data.count);
			
			
		},
		error : function(data) {
			toastr.error(data.message);
		},
	});
}




function timeSearch() {
  $.ajax({
    url: "controlChartData/search.action",
    type: "GET",
    dataType: "JSON",
    async: true,
    data: {
      ids: parent.publicParam.id,
      state: 1,
      startTime: 0,
      endTime: new Date().getTime(),
      condition: "",
      limit: public_limit,
      offset: public_offset
    },
    success: function(data) {
      console.log("查询数据999", data, public_offset);
      
    },
    error: function(data) {
      toastr.error(data.message);
    }
  });
}



//初始化datepicker
// $("#searchTime .input-daterange").datepicker({
//   format: "yyyy-mm-dd",
//   maxViewMode: 2,
//   todayBtn: "linked",
//   language: "zh-CN",
//   orientation: "auto left",
//   autoclose: true,
//   todayHighlight: true,
//   toggleActive: true
// });

//初始化单个按钮上传组件,组件名、controller层url
/**
 *新增和修改格式化
*/

function operate1(value, row, index) {
	var str = "";
    str += '<a class="btn btn-xs update btnYe" style="background-color: #f5b834;color:white;width:60px;height:25px;line-height:22.5px;">添加备注</a>&nbsp;&nbsp;&nbsp;';
	return str;
}
var flag = 0; //判断保存时是新增还是删除
var cId; //保存id，用于修改

/**
 * @method
 * @desc  显示新增窗口
 * @author gj
 * @date 2018年10月14日
 */
function showAdd() {
  $("#addInfo").val("");
  flag = 1;
  $("#myModal").modal("show");
  $("#myModalLabel").html("添加备注");
}

/**
 * 提交新增和修改
 */
function save() {
  //新增，修改
  
    //新增
    if ($("#addInfo").val() == "") {
      toastr.warning("备注不得为空!");
      return false;
    }
    
    $.ajax({
      url: "detectionData/findDetectionDataByTime.action",
      type: "POST",
      dataType: "JSON",
      //添加后保存数据传入后端
      data: {
        remark: $("#addInfo").val()
      },
      success: function(data) {
        if (data.code == "0000") {
          toastr.success(data.message);
          $("#myModal").modal("hide");
          showTable();
        } else {
          toastr.error(data.message);
        }
      },
      error: function(request) {
        console.log(request);
      }
    });
    $("#addInfo").val("");
} 

        //初始化bootstraptable
        showTable();

        function showTable() {
            $("#table")
              .bootstrapTable('destroy')
              .bootstrapTable({
                method: "post",
                url: "detectionData/findTodayDetectionData.action",
                exportTypes: ["excel"], //导出文件类型
                //每页显示20条数据
                pageSize: 20,
                pageList: [10, 20, 30],
                height: 750,
                columns: [
                  {
                    //区别于表格中表格中！的唯一的id
                    field: "jcsjnumber",
                    title: "序号",
                    align: "center",
                    valign: "middle",
                    width: 300
                  },
                  {
                    field: "inputTime",
                    title: "时间",
                    align: "center",
                    valign: "middle",
                    width: 300
                  },
                  {
                    field: "jcsjnumData",
                    title: "检测值",
                    align: "center",
                    valign: "middle",
                    width: 300
                  },
                  {
                    field: "jcsjRemark",
                    title: "备注",
                    align: "center",
                    valign: "middle",
                    width: 300
                  },
                  {
                    field: "",
                    title: "操作",
                    align: "center",
                    valign: "middle",
                    width: 300,
                    formatter: operate1,
                    events: operateEvents
                  }
                ],

                
              });
			}
// $(function() {
//   $("#side-menu").metisMenu(); // ul.nav#side-menu
// });


// 动态加载产品和检测项到metisMenu
$(function () {
    $('#side-menu').metisMenu();
    urlchange()
    $.ajax({
        type: "get",
        url: 'detection/finAll.action',
        success: function (data) {
            console.log(data)
            var sedlevel = '';
            var nav = '';
            for (var i = 0; i < data.rows.length; i++) {
                var sedlevel = '';
                if (data.rows[i].detections) {
                    for (var j = 0; j < data.rows[i].detections.length; j++) {
												sedlevel += `<li>
                                        <a href="${
                                          data.rows[i].detections[j].jcxmId
                                            .url
                                        }"><i class="fa fa-arrow-right"></i>${
																data.rows[i].detections[j].jcxmName
															}</a>
												</li>`;
												
                    }
                }
 
                nav += `<li>
                    <a href="#"><i class="fa fa-cloud"></i> <span class="nav-label">
                      ${data.rows[i].productName}
                    </span><span class="fa arrow"></span>
										</a>
										<ul class="nav nav-second-level">
                      ${sedlevel}
                    </ul>
                  </li>`;
 
            }
            $('#side-menu').metisMenu('dispose'); 
            $('#side-menu').append(nav);
            $('#side-menu').metisMenu();
            urlchange()
        }
    })
 
 
})
 
//侧边栏点击导航切换右侧页面，根据自己的html结构来修改
function urlchange() {  
    var url = window.location;
    var element = $('ul.nav a').filter(function () {
        return this.href == url || url.href.indexOf(this.href) == 0;
    }).addClass('active').parent().parent().addClass('in show').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
}

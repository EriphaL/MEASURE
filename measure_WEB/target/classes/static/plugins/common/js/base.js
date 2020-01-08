var ctx=$("#ctx").val();

function pathUrl(url) {
	var ind = ctx.indexOf(';jsessionid=');
	if(ind != -1){
		ctx = ctx.substring(0,ind);
	}
    return ctx+url;
}

function getData(type,parm) {
	return $.map($('#'+type).bootstrapTable('getData'), function (row) {
		return eval('row.'+parm) == undefined ? "无":eval('row.'+parm);
	});
}

function loadData(obj,HBox) {
	var key, value, tagName, type, arr;
	for (x in obj) {
		key = x;
		value = obj[x];
		$(HBox+" [name='" + key + "'],"+HBox+" [name='" + key + "[]']").each(function() {
			tagName = $(this)[0].tagName;
			type = $(this).attr('type');
			if (tagName == 'INPUT') {
				if (type == 'radio' || type == 'checkbox') {
					$(this).prop('checked', $(this).val() == value);
				}/* else if (type == 'checkbox') {
					arr = value.split(',');
					for ( var i = 0; i < arr.length; i++) {
						if ($(this).val() == arr[i]) {
							$(this).attr('checked', true);
							break;
						}
					}
				} */else {
					$(this).val(value);
				}
			} else if (tagName == 'SELECT' || tagName == 'TEXTAREA') {
				$(this).val(value);
			}

		});
	}
}

/*$.validator.addMethod("string", function(value, element) {
	return this.optional(element) || /^[u0391-uFFE5w]+$/.test(value);
	}, "不允许包含特殊符号!");*/

//对Date的扩展，将 Date 转化为指定格式的String   
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
//例子：   
//(new Date()).format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
//(new Date()).format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
Date.prototype.format = function(fmt)   
{ //author: meizz   
	var o = {   
			"M+" : this.getMonth()+1,                 //月份   
			"d+" : this.getDate(),                    //日   
			"h+" : this.getHours(),                   //小时   
			"m+" : this.getMinutes(),                 //分   
			"s+" : this.getSeconds(),                 //秒   
			"q+" : Math.floor((this.getMonth()+3)/3), //季度   
			"S"  : this.getMilliseconds()             //毫秒   
	};   
	if(/(y+)/.test(fmt))   
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	for(var k in o)   
		if(new RegExp("("+ k +")").test(fmt))   
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	return fmt;   
};
/*
 * 表单序列化（能序列化radio和checkbox是空）
 * bootstrap-select不能序列化要另外加上去
 * 添加示例：'&' + $.param({'rIds': ids})
 */
$.fn.serializeObject = function () {
    var a = this.serializeArray();
    var $radio = $('input[type=radio],input[type=checkbox]', this);
    var temp = {};
    $.each($radio, function () {
        if (!temp.hasOwnProperty(this.name)) {
            if ($("input[name='" + this.name + "']:checked").length == 0) {
                temp[this.name] = "";
                a.push({name: this.name, value: "0"});//value根据实际情况来写
            }
        }
    });
    return jQuery.param(a);
};

/*
 * @Description: 转UTF-8
 * @param :
 */
function toUtf8(str) {   
    var out, i, len, c;   
    out = "";   
    len = str.length;   
    for(i = 0; i < len; i++) {   
    	c = str.charCodeAt(i);   
    	if ((c >= 0x0001) && (c <= 0x007F)) {   
        	out += str.charAt(i);   
    	} else if (c > 0x07FF) {   
        	out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));   
        	out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));   
        	out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));   
    	} else {   
        	out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));   
        	out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));   
    	}   
    }   
    return out;   
}

/*
 * @Description: 单击子页面--右键页面消失
 * @param :
 */
$('body').click(function() {
	$(".dropdown-menuX", parent.document).hide();
})

/*
 * @Description: sweetAlert弹框(用于保存确认和删除确认)
 * @param :	title		标题
 * @param :	text		内容
 * @param :	type		类型('success','error','warning','info','question')	
 * @param :	confirmButtonText	确定按钮名称	
 * @param :	method		方法	(如:
 * 	function(result){//isConfirm 点击确定按钮，参数true/false
		if(result.value){
			$("#newProcessTable").bootstrapTable('removeRow',index);
		}
	})
 */
function showSwal(title,text,confirmButtonText,method){
	swal({
		title : title,
		text : text,
		type : 'question',
		confirmButtonText : confirmButtonText,
		cancelButtonText : "取消",
		showCancelButton: true,
		closeOnConfirm: false, 
		closeOnCancel: false
	}).then(method);
}
/*
 * @Description: sweetAlert弹框(用于提示,显示1秒后自动关闭)
 * @param :	title	标题
 * @param :	type	类型('success','error','warning','info','question'),默认值：success
 */
function swal_Tips_Short(title,type){
	if(type == undefined){
		type = "success";
	}
	swal({
		title : title,
		text : '该提示框将在1秒后自动关闭！',
		type : type,
		timer : 1000
	});
}

/*
 * @Description: sweetAlert弹框(用于提示，不会自动关闭)
 * @param :	title	标题
 * @param :	text	内容
 * @param :	type	类型('success','error','warning','info','question'),默认值:error
 */
function swal_Tips(title,text,type){
	if(type == undefined){
		type = "error";
	}
	if(text == undefined){
		text = "";
	}
	swal({
		title : title,
		text : text,
		type : type
	});
}

//时间
function formatDTime(time) {
	var Rime = "";
	if(time != 0){
		var date = new Date(time);
		var y = date.getFullYear();
		var M = date.getMonth()<9 ? '0'+(date.getMonth()+1) : (date.getMonth()+1);
		var d = date.getDate()<10 ? '0'+date.getDate() : date.getDate();
		var h = date.getHours()<10 ? '0'+date.getHours() : date.getHours();
		var m = date.getMinutes()<10 ? '0'+date.getMinutes() : date.getMinutes();
		var s = date.getSeconds()<10 ? '0'+date.getSeconds() : date.getSeconds();
		Rime = y+"-"+M+"-"+d+" "+h+":"+m+":"+s;
	}
    return Rime;
}